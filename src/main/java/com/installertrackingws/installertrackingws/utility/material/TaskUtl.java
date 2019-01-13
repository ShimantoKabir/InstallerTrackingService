package com.installertrackingws.installertrackingws.utility.material;

import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.Material.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class TaskUtl {

    public Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, TaskBn taskBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Task task = new Task();
            task.setName(taskBn.getName());
            task.setCost(taskBn.getCost());
            task.setDuration(taskBn.getDuration());
            task.setTaskSpecialist(taskBn.getTaskSpecialist());
            task.setIp(httpServletRequest.getRemoteAddr());
            task.setModifiedBy(taskBn.getModifiedBy());
            session.save(task);

            response.setMsg("Task save successfully !");
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

    public Response getAllTask(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT task.id, task.name, task.cost, task.duration, task.task_specialist, user.user_email, user.user_name FROM task INNER JOIN USER ON task.task_specialist=user.id");
        List<Object[]> results = query.getResultList();

        List<TaskBn> taskList = new ArrayList<>();

        int count = 0;
        for (Object[] result : results) {
            count++;
            TaskBn taskBn = new TaskBn();
            taskBn.setId((Integer) result[0]);
            taskBn.setName((String) result[1]);
            taskBn.setCost((Double) result[2]);
            taskBn.setDuration((Double) result[3]);
            taskBn.setTaskSpecialist((Integer) result[4]);
            taskBn.setSequenceNumber(count);
            taskBn.setUserEmail((String) result[5]);
            taskBn.setUserName((String) result[6]);
            taskBn.setChecked(false);
            taskList.add(taskBn);
        }

        session.getTransaction().commit();
        session.close();


        if (taskList.size()>0){
            response.setCode(200);
            response.setMsg("Task list fetch successful !");
            response.setList(taskList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Task list empty !");
            return response;
        }

    }

    public Response update(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, TaskBn taskBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE task SET name = :name, cost = :cost, duration = :duration, task_specialist = :taskSpecialist, modified_by = :modifiedBy, ip = :ip WHERE id  = :id");
            query.setParameter("name",taskBn.getName());
            query.setParameter("cost",taskBn.getCost());
            query.setParameter("duration",taskBn.getDuration());
            query.setParameter("taskSpecialist",taskBn.getTaskSpecialist());
            query.setParameter("modifiedBy",taskBn.getModifiedBy());
            query.setParameter("ip",httpServletRequest.getRemoteAddr());
            query.setParameter("id",taskBn.getId());
            query.executeUpdate();

            response.setMsg("Task updated successfully !");
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
