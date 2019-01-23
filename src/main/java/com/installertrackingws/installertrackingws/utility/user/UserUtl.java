package com.installertrackingws.installertrackingws.utility.user;

import com.google.gson.Gson;
import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.helper.PasswordEncryptionManager;
import com.installertrackingws.installertrackingws.model.department.Department;
import com.installertrackingws.installertrackingws.model.user.User;
import com.installertrackingws.installertrackingws.utility.department.DepartmentUtl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserUtl {

    public Response getAllUser(EntityManagerFactory entityManagerFactory){

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT user.id,user.user_name,user.user_email,user.is_user_active,user.is_user_approved,department.name AS dept_name,department.o_id AS dept_id,user.is_online,user.last_presence_date,user.is_typing,user.for_who FROM user LEFT JOIN department ON department.o_id=user.dept_id");
        List<Object[]> results = query.getResultList();

        List<UserBn> userList = new ArrayList<>();
        for (Object[] result : results) {
            UserBn userBn = new UserBn();
            userBn.setId((Integer) result[0]);
            userBn.setUserName((String) result[1]);
            userBn.setUserEmail((String) result[2]);
            userBn.setIsUserActive((Integer) result[3]);
            userBn.setIsUserApproved((Integer) result[4]);
            userBn.setDeptName((String) result[5]);
            userBn.setDeptId((Integer) result[6]);
            userBn.setIsOnline((Integer) result[7]);
            userBn.setLastPresenceDate((Date) result[8]);
            userBn.setIsTyping((Integer) result[9]);
            userBn.setForWho((Integer) result[10]);
            userList.add(userBn);
        }

        session.getTransaction().commit();
        session.close();

        if (userList.size()>0){
            response.setCode(200);
            response.setMsg("User list fetch successful !");
            response.setList(userList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("User list fetch unsuccessful !");
            return response;
        }

    }

    public Response manageUser(EntityManagerFactory entityManagerFactory, Request request){

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE USER SET is_user_active = :isUserActive,is_user_approved=:isUserApproved,dept_id=:deptId WHERE id = :id");
            query.setParameter("isUserActive",request.getManageUserBn().getIsUserActive());
            query.setParameter("isUserApproved",request.getManageUserBn().getIsUserApproved());
            query.setParameter("deptId",request.getManageUserBn().getDeptId());
            query.setParameter("id",request.getManageUserBn().getId());
            query.executeUpdate();

            tx.commit();

            response.setCode(200);
            response.setMsg("User manage successful !");

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

    public Response activeUser(EntityManagerFactory entityManagerFactory, String token) throws ParseException {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT created_date FROM user WHERE token = :token");
            checkQuery.setParameter("token",token);

            if (checkQuery.getResultList().size()>0){

                Date modifiedDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(checkQuery.getResultList().get(0).toString());

                long diff = new Date().getTime() - modifiedDate.getTime();

                long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

                if (minutes > 2){

                    response.setMsg("Token is expired !");
                    response.setCode(400);

                }else {

                    Query query = session.createNativeQuery("UPDATE user SET is_user_active=1 WHERE token = :token ");
                    query.setParameter("token",token);

                    int result = query.executeUpdate();

                    if (result>0){
                        response.setCode(200);
                        response.setMsg("User active successful !");
                    }else {
                        response.setCode(400);
                        response.setMsg("User active unsuccessful !");
                    }

                }

            }else {

                response.setCode(400);
                response.setMsg("This token did not match any account !");

            }

            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg("Exception occurred !");
            response.setCode(200);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;

    }

    public Response registrationUser(HttpServletRequest httpServletRequest,EntityManagerFactory entityManagerFactory,UserBn userBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("SELECT count(id) FROM user WHERE user_email = :userEmail");
            query.setParameter("userEmail",userBn.getUserEmail());
            System.out.println(query.getResultList().get(0).toString());

            if (query.getResultList().get(0).toString().equals("0")){

                User user = new User();
                user.setIp(httpServletRequest.getRemoteAddr());
                user.setIsUserActive(0);
                user.setIsUserApproved(0);
                user.setPassword(PasswordEncryptionManager.encryptPassword(userBn.getPassword()));
                user.setUserEmail(userBn.getUserEmail());
                user.setToken(userBn.getToken());
                user.setModifiedBy(userBn.getModifiedBy());
                user.setCreatedDate(new Date());
                session.save(user);

                response.setCode(200);
                response.setMsg("Registration successful !");

            }else {
                response.setCode(400);
                response.setMsg("User already exist !");
            }

            tx.commit();
        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg("Exception occurred !");
            response.setCode(200);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;
    }

    public Response checkUserLogin(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * FROM USER WHERE user_email = :userEmail AND password = :password ", User.class);
        query.setParameter("userEmail", request.getUserBn().getUserEmail());
        query.setParameter("password", PasswordEncryptionManager.encryptPassword(request.getUserBn().getPassword()));

        List<User> userList = query.getResultList();

        if (userList.size() == 0) {
            response.setMsg("Your email and password did not match any account !");
            response.setCode(400);
        } else {

            int isUserApproved = userList.get(0).getIsUserApproved();
            int isUserActive = userList.get(0).getIsUserActive();

            if (isUserActive == 0) {
                response.setMsg("Your account did not active yet, please check you mail and click the link and active your account !");
                response.setCode(400);
            } else if (isUserApproved == 0) {
                response.setMsg("Your account did not approved yet, when admin will approved your account then you can login !");
                response.setCode(400);
            } else {

                Query deptNameQuery = session.createNativeQuery("SELECT * FROM department WHERE o_id = :oId",Department.class);
                deptNameQuery.setParameter("oId",userList.get(0).getDeptId());
                Department department = (Department) deptNameQuery.getSingleResult();

                UserBn resUserBn = new UserBn();
                resUserBn.setId(userList.get(0).getId());
                resUserBn.setUserEmail(userList.get(0).getUserEmail());
                resUserBn.setUserName(userList.get(0).getUserName());
//                resUserBn.setIsUserActive(userList.get(0).getIsUserActive());
//                resUserBn.setIsUserApproved(userList.get(0).getIsUserApproved());
                resUserBn.setDeptId(userList.get(0).getDeptId());
                resUserBn.setDeptName(department.getName());

                response.setObject(resUserBn);
                response.setMsg("Login successful !");
                response.setCode(200);

            }


        }

        session.getTransaction().commit();
        session.close();

        return response;

    }

    public Response saveProfile(EntityManagerFactory entityManagerFactory, UserBn userBeen) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE user SET user_name = :userName WHERE id = :id");
            query.setParameter("id",userBeen.getId());
            query.setParameter("userName",userBeen.getUserName());
            query.executeUpdate();

            response.setMsg("Profile save successfully!");
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

    public Response changePassword(EntityManagerFactory entityManagerFactory, UserBn userBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT count(id) FROM user WHERE password = :password");
            checkQuery.setParameter("password",PasswordEncryptionManager.encryptPassword(userBn.getPassword()));

            if (checkQuery.getResultList().get(0).toString().equals("0")){

                response.setMsg("Old password did not match !");
                response.setCode(400);

            }else{

                Query query = session.createNativeQuery("UPDATE user SET password = :newPassword WHERE id = :id");
                query.setParameter("id",userBn.getId());
                query.setParameter("newPassword",PasswordEncryptionManager.encryptPassword(userBn.getNewPassword()));
                query.executeUpdate();

                response.setMsg("Password changed successfully!");
                response.setCode(200);

            }

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

    public Response getForgotPasswordLink(EntityManagerFactory entityManagerFactory, UserBn userBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT count(id) FROM user WHERE user_email = :userEmail");
            checkQuery.setParameter("userEmail",userBn.getUserEmail());

            if (checkQuery.getResultList().get(0).toString().equals("0")){

                response.setMsg("This email address did not match any user account !");
                response.setCode(400);

            }else{

                Query query = session.createNativeQuery("UPDATE user SET token = :token,modify_date = :modifyDate WHERE user_email = :userEmail");
                query.setParameter("token",userBn.getToken());
                query.setParameter("userEmail",userBn.getUserEmail());
                query.setParameter("modifyDate",new Date());
                query.executeUpdate();

                response.setCode(200);
                response.setMsg("Success !");

            }

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

    public Response verifyForgotPasswordToken(EntityManagerFactory entityManagerFactory, UserBn userBn) throws ParseException {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT modify_date FROM user WHERE token = :token");
            checkQuery.setParameter("token",userBn.getToken());

            if (checkQuery.getResultList().size()>0){

                Date modifiedDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(checkQuery.getResultList().get(0).toString());

                long diff = new Date().getTime() - modifiedDate.getTime();

                long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

                if (minutes > 2){
                    response.setMsg("Token is expired !");
                    response.setCode(400);
                }else {


                    Query query = session.createNativeQuery("UPDATE user SET password = :password WHERE modify_date = :modifyDate");
                    query.setParameter("password",PasswordEncryptionManager.encryptPassword(userBn.getNewPassword()));
                    query.setParameter("modifyDate",modifiedDate);
                    query.executeUpdate();


                    response.setMsg("Password change successfully !");
                    response.setCode(200);

                }


            }else {
                response.setMsg("This token did not match any account !");
                response.setCode(400);
            }

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

    public Response updateUserPresence(HttpServletRequest httpServletRequest,EntityManagerFactory entityManagerFactory, UserBn userBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE user SET last_presence_date = :lastPresenceDate WHERE id = :id");
            query.setParameter("lastPresenceDate",new Date());
            query.setParameter("id",userBn.getId());
            query.executeUpdate();

            response.setMsg("User presence update successfully !");
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

    public void updateUserOnlineStatus(EntityManagerFactory entityManagerFactory) throws ParseException {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT * FROM user",User.class);
            List<User> userList = checkQuery.getResultList();
            for (int i = 0; i < userList.size(); i++) {

                // System.out.println(userList.get(i).getUserEmail()+" ==== "+ userList.get(i).getIsOnline().toString());

                Date lastPresenceDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userList.get(i).getLastPresenceDate().toString());
                long diff = new Date().getTime() - lastPresenceDate.getTime();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);

                if (seconds > 10){

                    Query onlineStatusQuery = session.createNativeQuery("UPDATE user SET is_online = 0,is_typing = 0 WHERE id = :id");
                    onlineStatusQuery.setParameter("id",userList.get(i).getId());
                    onlineStatusQuery.executeUpdate();

                }else {

                    Query onlineStatusQuery = session.createNativeQuery("UPDATE user SET is_online = 1 WHERE id = :id");
                    onlineStatusQuery.setParameter("id",userList.get(i).getId());
                    onlineStatusQuery.executeUpdate();

                }

            }

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }

        }finally{
            if(session!=null){
                session.close();
            }
        }

    }

    public Response changeTypingStatus(EntityManagerFactory entityManagerFactory, UserBn userBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE user SET is_typing = 0 WHERE id = :id");
            query.setParameter("id",userBn.getId());
            query.executeUpdate();

            response.setMsg("Typing status change successfully !");
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

    public Response getByDepartment(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * FROM user WHERE dept_id = :deptId",User.class);
        query.setParameter("deptId",request.getDepartmentBn().getoId());

        List<User> userList = query.getResultList();

        session.getTransaction().commit();
        session.close();

        if (userList.size()>0){
            response.setCode(200);
            response.setMsg("User list get by department fetch successful !");
            response.setList(userList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("User list get by department empty !");
            return response;
        }

    }

    public Response getManageInitData(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        Response userRes = this.getAllUser(entityManagerFactory);
        Response deptRes = new DepartmentUtl().getDepartmentByUser(entityManagerFactory,request);

        if (userRes.getCode()==200 && deptRes.getCode()==200){

            response.setUserList(userRes.getList());
            response.setDepartmentBnList(deptRes.getList());
            response.setCode(200);
            response.setMsg("Initial data getting successful !");
        }else {
            response.setCode(400);
            response.setMsg("Initial data getting error !");
        }

        return response;
    }
}
