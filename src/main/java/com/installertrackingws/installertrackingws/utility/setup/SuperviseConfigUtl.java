package com.installertrackingws.installertrackingws.utility.setup;

import com.google.gson.Gson;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.setup.SuperviseConfigBn;
import com.installertrackingws.installertrackingws.model.setup.SuperviseConfig;
import com.installertrackingws.installertrackingws.model.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SuperviseConfigUtl {

    public Response response;
    public Gson gson;
    public Session session = null;
    public Transaction tx = null;

    public SuperviseConfigUtl() {

        this.gson = new Gson();
        this.response = new Response();

    }

    public Response getInitData(EntityManagerFactory entityManagerFactory) {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<SuperviseConfigBn> superviseConfigBnList = new ArrayList<>();

        Query scQuery = session.createNativeQuery("SELECT sc.id, sc.supervisor, sc.installer, s.user_email AS supervisorEmail, s.user_name AS supervisorName, i.user_email AS installerEmail, i.user_name AS installerName FROM it.supervise_config AS sc INNER JOIN it.user AS i ON i.id = sc.installer INNER JOIN it.user AS s ON s.id = sc.supervisor");

        List<Object[]> resultList = scQuery.getResultList();

        List<User> userList = session.createNativeQuery("SELECT * FROM user",User.class).getResultList();

        for (Object[] r : resultList) {

            SuperviseConfigBn superviseConfigBn = new SuperviseConfigBn();
            superviseConfigBn.setId((Integer) r[0]);
            superviseConfigBn.setSupervisor((Integer) r[1]);
            superviseConfigBn.setInstaller((Integer) r[2]);
            superviseConfigBn.setSupervisorEmail((String) r[3]);
            superviseConfigBn.setSupervisorName((String) r[4]);
            superviseConfigBn.setInstallerEmail((String) r[5]);
            superviseConfigBn.setInstallerName((String) r[6]);
            superviseConfigBnList.add(superviseConfigBn);
        }

        session.getTransaction().commit();
        session.close();

        if (superviseConfigBnList.size()>0){
            response.setCode(200);
            response.setMsg("Supervise config list fetch successful !");
            response.setSuperviseConfigBnList(superviseConfigBnList);
            response.setUserList(userList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Supervise config list empty !");
            response.setUserList(userList);
            return response;
        }

    }


    public Response create(HttpServletRequest httpServletRequest,EntityManagerFactory entityManagerFactory, Request request) {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            SuperviseConfig superviseConfig = new SuperviseConfig();
            superviseConfig.setInstaller(request.getSuperviseConfigBn().getInstaller());
            superviseConfig.setSupervisor(request.getSuperviseConfigBn().getSupervisor());
            superviseConfig.setModifiedBy(request.getSuperviseConfigBn().getModifiedBy());
            superviseConfig.setIp(httpServletRequest.getRemoteAddr());

            session.save(superviseConfig);

            response.setMsg("Supervise config save successfully !");
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
