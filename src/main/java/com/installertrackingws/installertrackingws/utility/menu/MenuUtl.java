package com.installertrackingws.installertrackingws.utility.menu;

import com.installertrackingws.installertrackingws.bean.menu.MenuBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.menu.Menu;
import com.installertrackingws.installertrackingws.model.menu.MenuPermission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MenuUtl {

    public Response getMenuByDepartment(EntityManagerFactory entityManagerFactory, Request request){

        Response response = new Response();
        MenuBn root = new MenuBn();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query rootMenuQry = session.createNativeQuery("SELECT * FROM menu WHERE parent_id=0 AND rank=1 AND srl=1 and o_id=1 ORDER BY srl ASC",Menu.class);
        Menu rootMenu = (Menu) rootMenuQry.getSingleResult();

        root.setId(rootMenu.getId());
        root.setoId(rootMenu.getoId());
        root.setIcon(rootMenu.getIcon());
        root.setLink(rootMenu.getLink());
        root.setText(rootMenu.getText());
        root.setRank(rootMenu.getRank());
        root.setSrl(rootMenu.getSrl());
        root.setParentId(rootMenu.getParentId());

        // first child ...
        Query childOneMenuQry = session.createNativeQuery("SELECT * FROM menu WHERE parent_id = :pId AND rank = 2 ORDER BY srl ASC",Menu.class);
        childOneMenuQry.setParameter("pId",rootMenu.getoId());

        List<Menu> childOneMenus = childOneMenuQry.getResultList();

        ArrayList<MenuBn> menuBns = new ArrayList<>();

        for (int i = 0; i < childOneMenus.size(); i++) {

            MenuBn child = new MenuBn();

            // second child ...
            Query childTwoMenuQry = session.createNativeQuery("SELECT * FROM menu INNER JOIN menu_permission ON menu_permission.menu_oid=menu.o_id WHERE menu.rank = 3 AND menu_permission.dept_id = :deptId AND menu.parent_id = :pId",Menu.class);
            childTwoMenuQry.setParameter("pId",childOneMenus.get(i).getoId());
            childTwoMenuQry.setParameter("deptId",request.getUserBn().getDeptId());

            List<Menu> childTwoMenus = childTwoMenuQry.getResultList();

            child.setId(childOneMenus.get(i).getId());
            child.setoId(childOneMenus.get(i).getoId());
            child.setIcon(childOneMenus.get(i).getIcon());
            child.setText(childOneMenus.get(i).getText());
            child.setLink(childOneMenus.get(i).getLink());
            child.setRank(childOneMenus.get(i).getRank());
            child.setSrl(childOneMenus.get(i).getSrl());
            child.setParentId(childOneMenus.get(i).getParentId());

            ArrayList<MenuBn> menuBnsTwo = new ArrayList<>();

            for (int j = 0; j < childTwoMenus.size(); j++) {

                Query departmentQuery = session.createNativeQuery("SELECT * FROM menu_permission WHERE menu_oid=:mOid ",MenuPermission.class);
                departmentQuery.setParameter("mOid",childTwoMenus.get(j).getoId());

                MenuBn childTwo = new MenuBn();
                childTwo.setId(childTwoMenus.get(j).getId());
                childTwo.setoId(childTwoMenus.get(j).getoId());
                childTwo.setIcon(childTwoMenus.get(j).getIcon());
                childTwo.setLink(childTwoMenus.get(j).getLink());
                childTwo.setText(childTwoMenus.get(j).getText());
                childTwo.setRank(childTwoMenus.get(j).getRank());
                childTwo.setSrl(childTwoMenus.get(j).getSrl());
                childTwo.setParentId(childTwoMenus.get(j).getParentId());
                childTwo.setMenuPermissionList(departmentQuery.getResultList());

                menuBnsTwo.add(childTwo);

            }

            child.setChildren(menuBnsTwo);
            menuBns.add(child);

        }

        root.setChildren(menuBns);
        session.getTransaction().commit();
        session.close();

        List<MenuBn> rootMenuList = new ArrayList<>();
        rootMenuList.add(root);

        if (rootMenuList.size()>0){
            response.setCode(200);
            response.setMsg("Menu list fetch successful");
            response.setList(rootMenuList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Menu list empty");
            return response;
        }

    }

    public List<MenuBn> getAllMenu(EntityManagerFactory entityManagerFactory){

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        MenuBn root = new MenuBn();

        Query rootMenuQry = session.createNativeQuery("SELECT * FROM menu WHERE parent_id=0 AND rank=1 AND srl=1 and o_id=1 ORDER BY srl ASC",Menu.class);

        Menu rootMenu = (Menu) rootMenuQry.getSingleResult();

        root.setId(rootMenu.getId());
        root.setoId(rootMenu.getoId());
        root.setIcon(rootMenu.getIcon());
        root.setLink(rootMenu.getLink());
        root.setText(rootMenu.getText());
        root.setRank(rootMenu.getRank());
        root.setSrl(rootMenu.getSrl());
        root.setParentId(rootMenu.getParentId());

        // first child ...
        Query childOneMenuQry = session.createNativeQuery("SELECT * FROM menu WHERE parent_id = :pId AND rank = 2 ORDER BY srl ASC",Menu.class);
        childOneMenuQry.setParameter("pId",rootMenu.getoId());

        List<Menu> childOneMenus = childOneMenuQry.getResultList();

        ArrayList<MenuBn> menuBeans = new ArrayList<>();

        for (int i = 0; i < childOneMenus.size(); i++) {

            MenuBn child = new MenuBn();

            // second child ...
            Query childTwoMenuQry = session.createNativeQuery("SELECT * FROM menu WHERE parent_id = :pId AND rank = 3 ORDER BY srl ASC",Menu.class);
            childTwoMenuQry.setParameter("pId",childOneMenus.get(i).getoId());

            List<Menu> childTwoMenus = childTwoMenuQry.getResultList();

            child.setId(childOneMenus.get(i).getId());
            child.setoId(childOneMenus.get(i).getoId());
            child.setIcon(childOneMenus.get(i).getIcon());
            child.setText(childOneMenus.get(i).getText());
            child.setLink(childOneMenus.get(i).getLink());
            child.setRank(childOneMenus.get(i).getRank());
            child.setSrl(childOneMenus.get(i).getSrl());
            child.setParentId(childOneMenus.get(i).getParentId());

            ArrayList<MenuBn> menuBeansTwo = new ArrayList<>();

            for (int j = 0; j < childTwoMenus.size(); j++) {

                Query departmentQuery = session.createNativeQuery("SELECT * FROM menu_permission WHERE menu_oid=:mOid ",MenuPermission.class);
                departmentQuery.setParameter("mOid",childTwoMenus.get(j).getoId());

                MenuBn childTwo = new MenuBn();
                childTwo.setId(childTwoMenus.get(j).getId());
                childTwo.setoId(childTwoMenus.get(j).getoId());
                childTwo.setIcon(childTwoMenus.get(j).getIcon());
                childTwo.setLink(childTwoMenus.get(j).getLink());
                childTwo.setText(childTwoMenus.get(j).getText());
                childTwo.setSrl(childTwoMenus.get(j).getSrl());
                childTwo.setRank(childTwoMenus.get(j).getRank());
                childTwo.setParentId(childTwoMenus.get(j).getParentId());
                childTwo.setMenuPermissionList(departmentQuery.getResultList());

                menuBeansTwo.add(childTwo);

            }

            child.setChildren(menuBeansTwo);
            menuBeans.add(child);

        }

        root.setChildren(menuBeans);
        session.getTransaction().commit();
        session.close();

        List<MenuBn> rootMenuList = new ArrayList<>();
        rootMenuList.add(root);

        return rootMenuList;
    }

    public Response saveMenu(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory,Request request){

        Response saveResponse = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try {

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("SELECT MAX(o_id) FROM menu");

            session.createNativeQuery("DELETE FROM menu_permission WHERE dept_id=101").executeUpdate();

            int rootOid = request.getMenuBnList().get(0).getoId();
            int maxOid = (int) query.getResultList().get(0);

            for (int i = rootOid+1; i <= maxOid; i++) {
                session.createNativeQuery("DELETE FROM menu WHERE o_id=:oId").setParameter("oId",i).executeUpdate();
            }

            if (request.getMenuBnList().get(0).getChildren().size()>0){

                for (int i = 0; i < request.getMenuBnList().get(0).getChildren().size(); i++) {

                    List<MenuBn> menuList1 = request.getMenuBnList().get(0).getChildren();
                    int parentId = request.getMenuBnList().get(0).getoId();

                    BigInteger oId1 = (BigInteger) session.createNativeQuery("SELECT IFNULL(MAX(o_id),0)+1 AS o_id FROM menu").getResultList().get(0);

                    Menu menu1 = new Menu();
                    menu1.setoId(oId1.intValue());
                    menu1.setParentId(parentId);
                    menu1.setText(menuList1.get(i).getText());
                    menu1.setRank(2);
                    menu1.setLink(menuList1.get(i).getLink());
                    menu1.setIcon(menuList1.get(i).getIcon());
                    menu1.setSrl(i+1);
                    menu1.setIp(httpServletRequest.getRemoteAddr());
                    menu1.setModifiedBy(request.getUserBn().getId());
                    session.save(menu1);

                    if (menuList1.get(i).getChildren().size()>0){

                        for (int j = 0; j < menuList1.get(i).getChildren().size(); j++) {

                            List<MenuBn> menuList2 = menuList1.get(i).getChildren();

                            BigInteger oId2 = (BigInteger) session.createNativeQuery("SELECT IFNULL(MAX(o_id),0)+1 AS o_id FROM menu").getResultList().get(0);

                            Menu menu2 = new Menu();
                            menu2.setoId(oId2.intValue());
                            menu2.setParentId(oId1.intValue());
                            menu2.setRank(3);
                            menu2.setText(menuList2.get(j).getText());
                            menu2.setLink(menuList2.get(j).getLink());
                            menu2.setIcon(menuList2.get(j).getIcon());
                            menu2.setSrl(j+1);
                            menu2.setIp(httpServletRequest.getRemoteAddr());
                            menu2.setModifiedBy(request.getUserBn().getId());
                            session.save(menu2);

                            MenuPermission menuPermission = new MenuPermission();
                            menuPermission.setDeptId(101);
                            menuPermission.setMenuOid(oId2.intValue());
                            menuPermission.setIp(httpServletRequest.getRemoteAddr());
                            menuPermission.setModifiedBy(request.getUserBn().getId());
                            session.save(menuPermission);

                        }

                    }


                }
            }

            saveResponse.setMsg("Menu save successfully !");
            saveResponse.setCode(200);

            tx.commit();

        } catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            saveResponse.setMsg("Exception occurred !");
            saveResponse.setCode(400);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return saveResponse;
    }

    public Response setMenuPermission(HttpServletRequest httpServletRequest,EntityManagerFactory entityManagerFactory,Request request){

        Response saveResponse = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try {

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("DELETE FROM menu_permission WHERE dept_id=:deptId");
            query.setParameter("deptId",request.getUserBn().getDeptId());
            query.executeUpdate();

            for (int i = 0; i < request.getIntegerList().size(); i++) {
                MenuPermission menuPermission = new MenuPermission();
                menuPermission.setIp(httpServletRequest.getRemoteAddr());
                menuPermission.setDeptId(request.getUserBn().getDeptId());
                menuPermission.setModifiedBy(request.getUserBn().getId());
                menuPermission.setMenuOid(request.getIntegerList().get(i));
                session.save(menuPermission);
            }

            saveResponse.setMsg("Menu auth save successfully !");
            saveResponse.setCode(200);

            tx.commit();

        } catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            saveResponse.setMsg("Exception occurred !");
            saveResponse.setCode(200);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return saveResponse;

    }

    public void checkMenuPermission(){

    }

}
