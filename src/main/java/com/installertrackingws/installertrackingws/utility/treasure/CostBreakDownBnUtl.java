package com.installertrackingws.installertrackingws.utility.treasure;

import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.treasure.CostBreakDownBn;
import com.installertrackingws.installertrackingws.model.treasure.CostBreakDown;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

public class CostBreakDownBnUtl {


    public static Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, CostBreakDownBn costBreakDownBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            CostBreakDown costBreakDown = new CostBreakDown();
            costBreakDown.setName(costBreakDownBn.getName());
            costBreakDown.setIp(httpServletRequest.getRemoteAddr());
            costBreakDown.setModifiedBy(costBreakDownBn.getModifiedBy());
            session.save(costBreakDown);

            response.setMsg("Cost break down save successfully !");
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
