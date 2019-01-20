package com.installertrackingws.installertrackingws.utility.location;

import com.installertrackingws.installertrackingws.bean.location.LocationBn;
import com.installertrackingws.installertrackingws.bean.location.Position;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.model.location.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LocationUtl {

    public Response trackByUser(EntityManagerFactory entityManagerFactory, UserBn userBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from location WHERE user_id = :userId and CAST(created_date AS DATE) = :createdDate",Location.class);
        query.setParameter("userId",userBn.getId());
        query.setParameter("createdDate",userBn.getCreatedDate());

        List<Location> locations = query.getResultList();

        if (locations.size()>0){
            response.setMsg("Found location !");
            response.setCode(200);
            response.setList(locations);
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

            Query locationQuery = session.createNativeQuery("SELECT * FROM location WHERE user_id = :userId AND wo_id = :woId AND CAST(created_date AS DATE) = :createdDate",Location.class);
            locationQuery.setParameter("userId",distinctUserIdQuery.getResultList().get(i));
            locationQuery.setParameter("woId",request.getWorkOrderBn().getId());
            locationQuery.setParameter("createdDate",request.getLocationBn().getCreatedDate());

            List<Location> locationList = locationQuery.getResultList();
            List<LocationBn> locationBnList = new ArrayList<>();

            for (int j = 0; j < locationList.size(); j++) {

                LocationBn locationBn = new LocationBn();
                locationBn.setLat(locationList.get(j).getLat());
                locationBn.setLng(locationList.get(j).getLon());
                locationBn.setPosition(new Position(locationList.get(j).getLat(),locationList.get(j).getLon()));
                locationBnList.add(locationBn);

            }

            userBn.setLocationList(locationBnList);
            userBnList.add(userBn);

        }

        if (userBnList.size()>0){
            response.setMsg("Found worker for this work order !");
            response.setCode(200);
            response.setList(userBnList);
        }else {
            response.setMsg("No worker found for this work order !");
            response.setCode(400);
        }

        session.getTransaction().commit();
        session.close();

        return response;

    }
}
