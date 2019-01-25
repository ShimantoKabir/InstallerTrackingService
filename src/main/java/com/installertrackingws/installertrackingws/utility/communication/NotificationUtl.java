package com.installertrackingws.installertrackingws.utility.communication;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.communication.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class NotificationUtl {

    public Response getNotificationByReceiver(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from notification WHERE receiver = :receiver",Notification.class);
        query.setParameter("receiver",request.getNotificationBn().getReceiver());

        List<Notification> notifications = query.getResultList();

        if (notifications.size()>0){
            response.setMsg("Found notification !");
            response.setCode(200);
            response.setObject(request.getNotificationBn());
            response.setList(notifications);
        }else {
            response.setMsg("Didn't found any notification !");
            response.setCode(400);
        }

        session.getTransaction().commit();
        session.close();

        return response;

    }

    public Response seenNotification(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query areFriendQuery = session.createNativeQuery("UPDATE notification SET  is_seen= 1 WHERE id = :id");
            areFriendQuery.setParameter("id",request.getNotificationBn().getId());
            areFriendQuery.executeUpdate();

            response.setMsg("Notification seen successfully !");
            response.setCode(200);

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg("Exception occurred !");
            response.setCode(400);

        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;

    }
}
