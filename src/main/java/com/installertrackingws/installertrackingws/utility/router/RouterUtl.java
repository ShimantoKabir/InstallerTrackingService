package com.installertrackingws.installertrackingws.utility.router;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RouterUtl {

    public Response getRouterByDepartment(EntityManagerFactory entityManagerFactory, Request request){

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query routerQuery = session.createNativeQuery("SELECT link FROM menu INNER JOIN menu_permission ON menu_permission.menu_oid=menu.o_id WHERE menu.rank = 3 AND menu_permission.dept_id = :deptId");
        routerQuery.setParameter("deptId",request.getUserBn().getDeptId());

        List list = routerQuery.getResultList();

        List routerList = new ArrayList();

        routerList.addAll(list);

        routerList.add("/");
        routerList.add("/dashboard");
        routerList.add("/email-verification");
        routerList.add("/unauthorized");
        routerList.add("/profile");
        routerList.add("/change-password");

        session.getTransaction().commit();
        session.close();

        if (routerList.size()>0){
            response.setCode(200);
            response.setMsg("Route fetch successful !");
            response.setList(routerList);
        }else {
            response.setCode(400);
            response.setMsg("Route fetch unsuccessful !");
        }

        return response;

    }

}
