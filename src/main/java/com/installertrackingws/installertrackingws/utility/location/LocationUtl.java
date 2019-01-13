package com.installertrackingws.installertrackingws.utility.location;

import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.model.Location.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
}
