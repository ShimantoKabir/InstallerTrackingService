package com.installertrackingws.installertrackingws.utility.material;

import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.material.TemplateBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.material.Template;
import com.installertrackingws.installertrackingws.model.material.TemplateDetail;
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

    public Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) {

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
            template.setName(request.getTemplateBn().getName());
            template.setIp(httpServletRequest.getRemoteAddr());
            template.setModifiedBy(request.getTemplateBn().getModifiedBy());
            session.save(template);

            for (int i = 0; i < request.getTaskBnList().size(); i++) {

                if (request.getTaskBnList().get(i).isChecked()){

                    TemplateDetail templateDetail = new TemplateDetail();
                    templateDetail.setIp(httpServletRequest.getRemoteAddr());
                    templateDetail.setModifiedBy(request.getTemplateBn().getModifiedBy());
                    templateDetail.setTaskOid(request.getTaskBnList().get(i).getId());
                    templateDetail.setTemplateOid(maxOid.intValue());
                    templateDetail.setSequenceNumber(request.getTaskBnList().get(i).getSequenceNumber());
                    session.save(templateDetail);

                }

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
            response.setTemplateBnList(templateBnList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Template list empty !");
            return response;
        }

    }

    public Response update(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) {


        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query templateDeleteQuery = session.createNativeQuery("DELETE FROM template WHERE o_id = :oId");
            templateDeleteQuery.setParameter("oId",request.getTemplateBn().getoId());
            templateDeleteQuery.executeUpdate();

            Query taskDeleteQuery = session.createNativeQuery("DELETE FROM template_detail WHERE template_oid = :templateOid");
            taskDeleteQuery.setParameter("templateOid",request.getTemplateBn().getoId());
            taskDeleteQuery.executeUpdate();

            Template template = new Template();
            template.setoId(request.getTemplateBn().getoId());
            template.setName(request.getTemplateBn().getName());
            template.setIp(httpServletRequest.getRemoteAddr());
            template.setModifiedBy(request.getTemplateBn().getModifiedBy());
            session.save(template);

            for (int i = 0; i < request.getTaskBnList().size(); i++) {

                if (request.getTaskBnList().get(i).isChecked()){

                    TemplateDetail templateDetail = new TemplateDetail();
                    templateDetail.setIp(httpServletRequest.getRemoteAddr());
                    templateDetail.setModifiedBy(request.getTemplateBn().getModifiedBy());
                    templateDetail.setTaskOid(request.getTaskBnList().get(i).getId());
                    templateDetail.setTemplateOid(request.getTemplateBn().getoId());
                    templateDetail.setSequenceNumber(request.getTaskBnList().get(i).getSequenceNumber());
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

    public Response getInitData(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();
        Response taskRes = new TaskUtl().getAllTask(entityManagerFactory);
        Response templateRes = this.getTemplateWithTask(entityManagerFactory);

        response.setTaskResponse(taskRes);
        response.setTemplateResponse(templateRes);
        response.setCode(200);
        response.setMsg("Init data getting successful !");

        return response;


    }
}
