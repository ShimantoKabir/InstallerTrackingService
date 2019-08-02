package com.installertrackingws.installertrackingws.utility.location;

import com.installertrackingws.installertrackingws.bean.location.LocationBn;
import com.installertrackingws.installertrackingws.bean.location.Position;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.model.location.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocationUtl {

    public Response trackByUser(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select location.id,location.lat,location.lon,location.user_id,location.wo_id,location.created_date,user.user_name,work_order.name from location inner join user on user.id = location.user_id inner join work_order on work_order.id = location.wo_id WHERE location.user_id = :userId AND CAST(location.created_date AS DATE) = :createdDate");
        query.setParameter("userId",request.getLocationBn().getUserId());
        query.setParameter("createdDate",request.getLocationBn().getCreatedDate());

        List<Object[]> results = query.getResultList();
        List<LocationBn> locationBnList = new ArrayList<>();

        for (Object[] result : results) {

            LocationBn locationBn = new LocationBn();
            locationBn.setLat((Double) result[1]);
            locationBn.setLng((Double) result[2]);
            locationBn.setPosition(new Position((Double) result[1],(Double) result[2]));
            locationBn.setUserId((Integer) result[3]);
            locationBn.setWoId((Integer) result[4]);
            locationBn.setCreatedDate((Date) result[5]);
            locationBn.setUserName((String) result[6]);
            locationBn.setWorkOrderName((String) result[7]);
            locationBnList.add(locationBn);

        }

        if (locationBnList.size()>0){
            response.setMsg("Found location !");
            response.setCode(200);
            response.setList(locationBnList);
        }else {
            response.setMsg("No location found !");
            response.setCode(400);
        }

        session.getTransaction().commit();
        session.close();

        return response;

    }

    public int countByUser(EntityManagerFactory entityManagerFactory, UserBn userBn) {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from location WHERE user_id = :userId and CAST(created_date AS DATE) = :createdDate",Location.class);
        query.setParameter("userId",userBn.getId());
        query.setParameter("createdDate",userBn.getCreatedDate());

        List<Location> locations = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return locations.size();

    }

    public Response getNew(EntityManagerFactory entityManagerFactory, UserBn userBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from location WHERE user_id = :userId and CAST(created_date AS DATE) = :createdDate ORDER BY id DESC LIMIT 1",Location.class);
        query.setParameter("userId",userBn.getId());
        query.setParameter("createdDate",userBn.getCreatedDate());

        List<Location> locations = query.getResultList();

        if (locations.size()>0){
            response.setMsg("Found new location !");
            response.setCode(200);
            response.setObject(locations.get(0));
        }else {
            response.setMsg("No new location found !");
            response.setCode(400);
        }

        session.getTransaction().commit();
        session.close();

        return response;

    }

    public Response trackByWorkOrder(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query distinctUserIdQuery = session.createNativeQuery("SELECT DISTINCT user_id FROM location WHERE wo_id = :woId");
        distinctUserIdQuery.setParameter("woId",request.getWorkOrderBn().getId());

        List<UserBn> userBnList = new ArrayList<>();

        for (int i = 0; i < distinctUserIdQuery.getResultList().size(); i++) {

            UserBn userBn = new UserBn();
            userBn.setId((Integer) distinctUserIdQuery.getResultList().get(i));

            Query locationQuery = session.createNativeQuery("select location.id,location.lat,location.lon,location.user_id,location.wo_id,location.created_date,user.user_name,work_order.name from location inner join user on user.id = location.user_id inner join work_order on work_order.id = location.wo_id WHERE location.user_id = :userId AND location.wo_id = :woId AND CAST(location.created_date AS DATE) = :createdDate ORDER BY id");
            locationQuery.setParameter("userId",distinctUserIdQuery.getResultList().get(i));
            locationQuery.setParameter("woId",request.getWorkOrderBn().getId());
            locationQuery.setParameter("createdDate",request.getLocationBn().getCreatedDate());

            List<Object[]> results = locationQuery.getResultList();
            List<LocationBn> locationBnList = new ArrayList<>();

            for (Object[] result : results) {

                LocationBn locationBn = new LocationBn();
                locationBn.setId((Integer) result[0]);
                locationBn.setLat((Double) result[1]);
                locationBn.setLng((Double) result[2]);
                locationBn.setPosition(new Position((Double) result[1],(Double) result[2]));
                locationBn.setUserId((Integer) result[3]);
                locationBn.setWoId((Integer) result[4]);
                locationBn.setCreatedDate((Date) result[5]);
                locationBn.setUserName((String) result[6]);
                locationBn.setWorkOrderName((String) result[7]);
                locationBnList.add(locationBn);

            }

            userBn.setLocationBnList(locationBnList);
            userBnList.add(userBn);

        }

        if (userBnList.size()>0){
            response.setMsg("Found worker for this work order !");
            response.setCode(200);
            response.setUserBnList(userBnList);
        }else {
            response.setMsg("No worker found for this work order !");
            response.setCode(400);
        }

        session.getTransaction().commit();
        session.close();

        return response;

    }

    public Response sendByUser(HttpServletRequest httpServletRequest,EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Location location = new Location();
            location.setUserId(request.getLocationBn().getUserId());
            location.setIp(httpServletRequest.getRemoteAddr());
            location.setLat(request.getLocationBn().getLat());
            location.setLon(request.getLocationBn().getLon());
            location.setWoId(request.getLocationBn().getWoId());
            session.save(location);

            response.setMsg("Location save successfully !");
            response.setCode(200);

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg(e.getMessage());
            response.setCode(400);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;

    }

    public Response trackLastByWorkOrder(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<UserBn> userBnList = new ArrayList<>();

        for (int i = 0; i < request.getUserBnList().size(); i++) {

            UserBn userBn = new UserBn();
            userBn.setId(request.getUserBnList().get(i).getId());

            Query locationQuery = session.createNativeQuery("select location.id,location.lat,location.lon,location.user_id,location.wo_id,location.created_date,user.user_name,work_order.name from location inner join user on user.id = location.user_id inner join work_order on work_order.id = location.wo_id WHERE location.user_id = :userId AND location.wo_id = :woId AND CAST(location.created_date AS DATE) = :createdDate AND location.id > :locationId ORDER BY id");
            locationQuery.setParameter("userId",request.getUserBnList().get(i).getId());
            locationQuery.setParameter("locationId",request.getUserBnList().get(i).getLocationBn().getId());
            locationQuery.setParameter("woId",request.getWoAssignBn().getId());
            locationQuery.setParameter("createdDate",request.getLocationBn().getCreatedDate());

            List<Object[]> results = locationQuery.getResultList();
            List<LocationBn> locationBnList = new ArrayList<>();

            for (Object[] result : results) {

                LocationBn locationBn = new LocationBn();
                locationBn.setId((Integer) result[0]);
                locationBn.setLat((Double) result[1]);
                locationBn.setLng((Double) result[2]);
                locationBn.setPosition(new Position((Double) result[1],(Double) result[2]));
                locationBn.setUserId((Integer) result[3]);
                locationBn.setWoId((Integer) result[4]);
                locationBn.setCreatedDate((Date) result[5]);
                locationBn.setUserName((String) result[6]);
                locationBn.setWorkOrderName((String) result[7]);
                locationBnList.add(locationBn);

            }

            userBn.setLocationBnList(locationBnList);
            userBnList.add(userBn);

        }

        if (userBnList.size()>0){
            response.setMsg("Found new location for this work order !");
            response.setCode(200);
            response.setUserBnList(userBnList);
        }else {
            response.setMsg("No new location found this work order !");
            response.setCode(400);
        }

        session.getTransaction().commit();
        session.close();

        return response;
    }
}
