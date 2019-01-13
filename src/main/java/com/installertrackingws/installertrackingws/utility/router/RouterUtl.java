package com.installertrackingws.installertrackingws.utility.router;

import com.installertrackingws.installertrackingws.bean.user.UserBn;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RouterUtl {

    public List getRouter(EntityManagerFactory entityManagerFactory, UserBn userBn){

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query routerQuery = session.createNativeQuery("SELECT link FROM menu INNER JOIN menu_permission ON menu_permission.menu_oid=menu.o_id WHERE menu.rank = 3 AND menu_permission.dept_id = :deptId");
        routerQuery.setParameter("deptId",userBn.getDeptId());

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

        return routerList;

    }

}
