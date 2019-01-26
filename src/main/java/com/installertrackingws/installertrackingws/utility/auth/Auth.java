package com.installertrackingws.installertrackingws.utility.auth;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.model.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class Auth {

    public static boolean isValid(EntityManagerFactory entityManagerFactory, Request request){

        UserBn userBn = request.getUserBn();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from user WHERE user_email = :userEmail and id = :id and session_id = :sessionId",User.class);
        query.setParameter("userEmail",userBn.getUserEmail());
        query.setParameter("id",userBn.getId());
        query.setParameter("sessionId",userBn.getSessionId());
        List<User> userList = query.getResultList();

        session.getTransaction().commit();
        session.close();

        if (userList.size()>0){
            return true;
        }else {
            return false;
        }

    }

    public static Response response(){
        Response response = new Response();
        response.setCode(400);
        response.setMsg("User authentication not successful !");
        return response;
    }

}
