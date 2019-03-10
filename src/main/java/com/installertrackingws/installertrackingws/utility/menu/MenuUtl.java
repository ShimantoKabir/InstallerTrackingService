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
        MenuBn menuBn = new MenuBn();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query rootMenuQry = session.createNativeQuery("SELECT * FROM menu WHERE parent_id=0 AND rank=1 AND srl=1 and o_id=1 ORDER BY srl ASC",Menu.class);
        Menu menu = (Menu) rootMenuQry.getSingleResult();

        menuBn.setId(menu.getId());
        menuBn.setoId(menu.getoId());
        menuBn.setIcon(menu.getIcon());
        menuBn.setLink(menu.getLink());
        menuBn.setText(menu.getText());
        menuBn.setRank(menu.getRank());
        menuBn.setSrl(menu.getSrl());
        menuBn.setParentId(menu.getParentId());

        // first child ...
        Query childOneMenuQry = session.createNativeQuery("SELECT * FROM menu WHERE parent_id = :pId AND rank = 2 ORDER BY srl ASC",Menu.class);
        childOneMenuQry.setParameter("pId",menu.getoId());

        List<Menu> menuList = childOneMenuQry.getResultList();
        List<MenuBn> menuBnList = new ArrayList<>();

        for (int i = 0; i < menuList.size(); i++) {

            MenuBn children = new MenuBn();

            // second child ...
            Query childTwoMenuQry = session.createNativeQuery("SELECT * FROM menu INNER JOIN menu_permission ON menu_permission.menu_oid=menu.o_id WHERE menu.rank = 3 AND menu_permission.dept_id = :deptId AND menu.parent_id = :pId",Menu.class);
            childTwoMenuQry.setParameter("pId",menuList.get(i).getoId());
            childTwoMenuQry.setParameter("deptId",request.getDepartmentBn().getId());

            List<Menu> childrenMenuList = childTwoMenuQry.getResultList();

            children.setId(menuList.get(i).getId());
            children.setoId(menuList.get(i).getoId());
            children.setIcon(menuList.get(i).getIcon());
            children.setText(menuList.get(i).getText());
            children.setLink(menuList.get(i).getLink());
            children.setRank(menuList.get(i).getRank());
            children.setSrl(menuList.get(i).getSrl());
            children.setParentId(menuList.get(i).getParentId());

            List<MenuBn> childrenMenuBnList = new ArrayList<>();

            for (int j = 0; j < childrenMenuList.size(); j++) {

                Query departmentQuery = session.createNativeQuery("SELECT * FROM menu_permission WHERE menu_oid=:mOid ",MenuPermission.class);
                departmentQuery.setParameter("mOid",childrenMenuList.get(j).getoId());

                MenuBn childTwo = new MenuBn();
                childTwo.setId(childrenMenuList.get(j).getId());
                childTwo.setoId(childrenMenuList.get(j).getoId());
                childTwo.setIcon(childrenMenuList.get(j).getIcon());
                childTwo.setLink(childrenMenuList.get(j).getLink());
                childTwo.setText(childrenMenuList.get(j).getText());
                childTwo.setRank(childrenMenuList.get(j).getRank());
                childTwo.setSrl(childrenMenuList.get(j).getSrl());
                childTwo.setParentId(childrenMenuList.get(j).getParentId());
                childTwo.setMenuPermissionBnList(departmentQuery.getResultList());

                childrenMenuBnList.add(childTwo);

            }

            children.setChildren(childrenMenuBnList);
            menuBnList.add(children);

        }

        menuBn.setChildren(menuBnList);
        session.getTransaction().commit();
        session.close();

        response.setCode(200);
        response.setMsg("Menu fetch successful !");
        response.setMenuBn(menuBn);

        return response;

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
                childTwo.setMenuPermissionBnList(departmentQuery.getResultList());

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

    public static boolean isPermitted(EntityManagerFactory entityManagerFactory,Request request){

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query qry = session.createNativeQuery("SELECT * FROM menu WHERE link = :link",Menu.class);
        qry.setParameter("link",request.getMenuBn().getLink());

        List<Menu> menuBnList = qry.getResultList();

        if (menuBnList.size()>0){

            Query permissionQry = session.createNativeQuery("SELECT * FROM menu_permission WHERE dept_id = :deptId and menu_oid = :menuOid",MenuPermission.class);
            permissionQry.setParameter("deptId",request.getUserBn().getDeptId());
            permissionQry.setParameter("menuOid",menuBnList.get(0).getoId());

            List<MenuPermission> menuPermissionList = permissionQry.getResultList();

            if (menuPermissionList.size()>0){
                return true;
            }else {
                return false;
            }

        }else {

            return false;

        }

    }

    public static Response response(){
        Response response = new Response();
        response.setCode(400);
        response.setMsg("Menu authentication fail !");
        return response;
    }

}
