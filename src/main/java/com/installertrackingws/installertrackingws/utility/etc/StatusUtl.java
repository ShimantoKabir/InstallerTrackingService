package com.installertrackingws.installertrackingws.utility.etc;

import com.installertrackingws.installertrackingws.bean.etc.StatusBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.Etc.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class StatusUtl {

    public static Response getAllStatus(EntityManagerFactory entityManagerFactory){

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select * from status",Status.class);
        List<Status> statusList = query.getResultList();

        session.getTransaction().commit();
        session.close();

        if (statusList.size()>0){
            response.setCode(200);
            response.setMsg("Status list fetch successful !");
            response.setStatusList(statusList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Status list list empty !");
            return response;
        }

    }

}
