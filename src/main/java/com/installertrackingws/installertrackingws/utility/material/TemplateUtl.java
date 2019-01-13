package com.installertrackingws.installertrackingws.utility.material;

import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.material.TemplateBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.Material.Template;
import com.installertrackingws.installertrackingws.model.Material.TemplateDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TemplateUtl {

    public Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, TemplateBn templateBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            BigInteger maxOid = (BigInteger) session.createNativeQuery("SELECT IFNULL(MAX(o_id),100)+1 AS o_id FROM template").getResultList().get(0);

            Template template = new Template();
            template.setoId(maxOid.intValue());
            template.setName(templateBn.getName());
            template.setIp(httpServletRequest.getRemoteAddr());
            template.setModifiedBy(templateBn.getModifiedBy());
            session.save(template);

            for (int i = 0; i < templateBn.getTaskList().size(); i++) {

                TemplateDetail templateDetail = new TemplateDetail();
                templateDetail.setIp(httpServletRequest.getRemoteAddr());
                templateDetail.setModifiedBy(templateBn.getModifiedBy());
                templateDetail.setTaskOid(templateBn.getTaskList().get(i).getId());
                templateDetail.setTemplateOid(maxOid.intValue());
                templateDetail.setSequenceNumber(templateBn.getTaskList().get(i).getSequenceNumber());
                session.save(templateDetail);

            }

            response.setMsg("Template save successfully !");
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

    public Response getTemplateWithTask(EntityManagerFactory entityManagerFactory){


        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<TemplateBn> templateBnList = new ArrayList<>();

        Query templateQuery = session.createNativeQuery("SELECT * FROM template",Template.class);
        List<Template> templateList = templateQuery.getResultList();

        for (int i = 0; i < templateList.size(); i++) {

            List<TaskBn> taskList = new ArrayList<>();

            Query taskQuery = session.createNativeQuery("SELECT task.id,task.name,task.cost,task.duration,template_detail.sequence_number FROM template_detail INNER JOIN task ON task.id=template_detail.task_oid WHERE template_detail.template_oid = :templateOid ORDER BY sequence_number");
            taskQuery.setParameter("templateOid",templateList.get(i).getoId());

            List<Object[]> results = taskQuery.getResultList();

            for (Object[] result : results) {

                TaskBn taskBn = new TaskBn();
                taskBn.setId((Integer) result[0]);
                taskBn.setName((String) result[1]);
                taskBn.setCost((Double) result[2]);
                taskBn.setDuration((Double) result[3]);
                taskBn.setSequenceNumber((Integer) result[4]);
                taskBn.setStartDate("");
                taskBn.setEndDate("");
                taskBn.setPhotoQuantity(0);
                taskBn.setRemark("");
                taskList.add(taskBn);

            }

            TemplateBn templateBn = new TemplateBn();
            templateBn.setId(templateList.get(i).getId());
            templateBn.setoId(templateList.get(i).getoId());
            templateBn.setCreatedDate(templateList.get(i).getCreatedDate());
            templateBn.setIp(templateList.get(i).getIp());
            templateBn.setModifiedBy(templateList.get(i).getModifiedBy());
            templateBn.setName(templateList.get(i).getName());
            templateBn.setTaskList(taskList);
            templateBnList.add(templateBn);

        }

        session.getTransaction().commit();
        session.close();

        if (templateBnList.size()>0){
            response.setCode(200);
            response.setMsg("Template list fetch successful !");
            response.setList(templateBnList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Template list empty !");
            return response;
        }

    }

    public Response update(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, TemplateBn templateBn) {


        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query templateDeleteQuery = session.createNativeQuery("DELETE FROM template WHERE o_id = :oId");
            templateDeleteQuery.setParameter("oId",templateBn.getoId());
            templateDeleteQuery.executeUpdate();

            Query taskDeleteQuery = session.createNativeQuery("DELETE FROM template_detail WHERE template_oid = :templateOid");
            taskDeleteQuery.setParameter("templateOid",templateBn.getoId());
            taskDeleteQuery.executeUpdate();

            Template template = new Template();
            template.setoId(templateBn.getoId());
            template.setName(templateBn.getName());
            template.setIp(httpServletRequest.getRemoteAddr());
            template.setModifiedBy(templateBn.getModifiedBy());
            session.save(template);

            for (int i = 0; i < templateBn.getTaskList().size(); i++) {

                if (templateBn.getTaskList().get(i).isChecked()){

                    TemplateDetail templateDetail = new TemplateDetail();
                    templateDetail.setIp(httpServletRequest.getRemoteAddr());
                    templateDetail.setModifiedBy(templateBn.getModifiedBy());
                    templateDetail.setTaskOid(templateBn.getTaskList().get(i).getId());
                    templateDetail.setTemplateOid(templateBn.getoId());
                    templateDetail.setSequenceNumber(templateBn.getTaskList().get(i).getSequenceNumber());
                    session.save(templateDetail);

                }

            }

            response.setMsg("Template update successful !");
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
