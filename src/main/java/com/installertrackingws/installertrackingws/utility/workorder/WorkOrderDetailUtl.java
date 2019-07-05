package com.installertrackingws.installertrackingws.utility.workorder;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.workorder.WorkOrderDetailBn;
import com.installertrackingws.installertrackingws.model.workorder.WorkOrderDetail;
import com.installertrackingws.installertrackingws.utility.material.TemplateUtl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkOrderDetailUtl {

    public static Response getInitData(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        Response templateRes = new  TemplateUtl().getTemplateWithTask(entityManagerFactory);
        Response wordOrderRes = WorkOrderUtl.getAllWorkOder(entityManagerFactory);
        Response workOrderDetailRes = WorkOrderDetailUtl.getAllWorkOderDetail(entityManagerFactory);

        System.out.println("Temp list = "+templateRes.getTemplateBnList());

        response.setCode(200);
        response.setMsg("Init data getting successful !");
        response.setTemplateBnList(templateRes.getTemplateBnList());
        response.setWorkOrderBnList(wordOrderRes.getWorkOrderBnList());
        response.setWorkOrderDetailBnList(workOrderDetailRes.getWorkOrderDetailBnList());

        return response;

    }

    public static Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) throws ParseException {


        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT * FROM work_order_detail WHERE wo_id=:woId and template_oid=:templateOid",WorkOrderDetail.class);
            checkQuery.setParameter("woId",request.getWorkOrderDetailBn().getWoId());
            checkQuery.setParameter("templateOid",request.getWorkOrderDetailBn().getTemplateOid());

            if (checkQuery.getResultList().size()>0){

                response.setMsg("This work order and template already been saved !");
                response.setCode(400);

            }else {

                for (int i = 0; i < request.getTaskBnList().size(); i++) {

                    WorkOrderDetail workOrderDetail = new WorkOrderDetail();
                    workOrderDetail.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getTaskBnList().get(i).getEndDate()));
                    workOrderDetail.setIp(httpServletRequest.getRemoteAddr());
                    workOrderDetail.setModifiedBy(request.getWorkOrderDetailBn().getModifiedBy());
                    workOrderDetail.setPhotoQuantity(request.getTaskBnList().get(0).getPhotoQuantity());
                    workOrderDetail.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getTaskBnList().get(i).getStartDate()));
                    workOrderDetail.setWoId(request.getWorkOrderDetailBn().getWoId());
                    workOrderDetail.setTemplateOid(request.getWorkOrderDetailBn().getTemplateOid());
                    workOrderDetail.setTaskId(request.getTaskBnList().get(i).getId());
                    workOrderDetail.setTaskSqNo(request.getTaskBnList().get(i).getSequenceNumber());
                    workOrderDetail.setStatusOid(101);
                    session.save(workOrderDetail);

                }

                response.setMsg("Work order details save successfully !");
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

    public static Response getAllWorkOderDetail(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query distinctWodQuery = session.createNativeQuery("SELECT DISTINCT \n" +
                "work_order.id AS wo_id,\n" +
                "work_order.name wo_name,\n" +
                "template.o_id AS template_oid,\n" +
                "template.name AS template_name\n" +
                "FROM work_order_detail\n" +
                "INNER JOIN work_order ON work_order.id=work_order_detail.wo_id\n" +
                "INNER JOIN template ON template.o_id=work_order_detail.template_oid");

        List<WorkOrderDetailBn> workOrderDetailBnList = new ArrayList<>();
        List<Object[]> wodResults = distinctWodQuery.getResultList();

        for (Object[] wodResult : wodResults) {

            WorkOrderDetailBn workOrderDetailBn = new WorkOrderDetailBn();
            workOrderDetailBn.setWoId((Integer) wodResult[0]);
            workOrderDetailBn.setWoName((String) wodResult[1]);
            workOrderDetailBn.setTemplateOid((Integer) wodResult[2]);
            workOrderDetailBn.setTemplateName((String) wodResult[3]);

            Query wodTaskQuery = session.createNativeQuery("SELECT \n" +
                    "work_order_detail.id,\n" +
                    "work_order_detail.task_id AS task_id,\n" +
                    "task.name AS task_name,\n" +
                    "CAST(work_order_detail.start_date AS DATE) AS start_date, \n" +
                    "CAST(work_order_detail.end_date AS DATE) AS end_date,\n" +
                    "work_order_detail.done_by,\n" +
                    "CAST(work_order_detail.done_date AS DATE) AS done_date,\n" +
                    "work_order_detail.done_lat,\n" +
                    "work_order_detail.done_lon,\n" +
                    "work_order_detail.remark,\n" +
                    "work_order_detail.status_oid,\n" +
                    "status.name AS status_name,\n" +
                    "user.user_name,\n" +
                    "work_order_detail.photo_quantity\n" +
                    "FROM work_order_detail \n" +
                    "INNER JOIN task ON task.id=work_order_detail.task_id\n" +
                    "INNER JOIN status ON status.o_id=work_order_detail.status_oid\n" +
                    "LEFT JOIN user ON user.id=work_order_detail.done_by\n" +
                    "WHERE wo_id = :woId AND template_oid = :templateOid ");

            wodTaskQuery.setParameter("woId",wodResult[0]);
            wodTaskQuery.setParameter("templateOid",wodResult[2]);

            List<WorkOrderDetailBn> wodWithTaskList = new ArrayList<>();
            List<Object[]> wodTaskResults = wodTaskQuery.getResultList();

            for (Object[] wodTaskResult : wodTaskResults) {

                WorkOrderDetailBn wodWithTaskBn = new WorkOrderDetailBn();

                wodWithTaskBn.setId((Integer) wodTaskResult[0]);
                wodWithTaskBn.setTaskId((Integer) wodTaskResult[1]);
                wodWithTaskBn.setTaskName((String) wodTaskResult[2]);
                wodWithTaskBn.setStartDate((Date) wodTaskResult[3]);
                wodWithTaskBn.setEndDate((Date) wodTaskResult[4]);
                wodWithTaskBn.setDoneBy((Integer) wodTaskResult[5]);
                wodWithTaskBn.setDoneDate((Date) wodTaskResult[6]);
                wodWithTaskBn.setDoneLat((Double) wodTaskResult[7]);
                wodWithTaskBn.setDoneLon((Double) wodTaskResult[8]);
                wodWithTaskBn.setRemark((String) wodTaskResult[9]);
                wodWithTaskBn.setStatusOid((Integer) wodTaskResult[10]);
                wodWithTaskBn.setStatusName((String) wodTaskResult[11]);
                wodWithTaskBn.setUserName((String) wodTaskResult[12]);
                wodWithTaskBn.setPhotoQuantity((Integer) wodTaskResult[13]);

                wodWithTaskList.add(wodWithTaskBn);

            }

            workOrderDetailBn.setTaskList(wodWithTaskList);

            workOrderDetailBnList.add(workOrderDetailBn);

        }

        session.getTransaction().commit();
        session.close();

        if (workOrderDetailBnList.size()>0){
            response.setCode(200);
            response.setMsg("Work order detail list fetch successful !");
            response.setWorkOrderDetailBnList(workOrderDetailBnList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Template list empty !");
            return response;
        }

    }

    public static Response update(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            for (int i = 0; i < request.getTaskBnList().size(); i++) {

                Query query = session.createNativeQuery("UPDATE work_order_detail SET start_date = :startDate, end_date=:endDate, photo_quantity=:photoQuantity,modified_by=:modifiedBy WHERE id = :id");
                query.setParameter("startDate",request.getTaskBnList().get(i).getStartDate());
                query.setParameter("endDate",request.getTaskBnList().get(i).getEndDate());
                query.setParameter("photoQuantity",request.getTaskBnList().get(i).getPhotoQuantity());
                query.setParameter("id",request.getTaskBnList().get(i).getId());
                query.setParameter("modifiedBy",request.getWorkOrderDetailBn().getModifiedBy());
                query.executeUpdate();

            }

            response.setMsg("Work order detail update successful !");
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

    public static Response delete(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT * FROM work_order_detail WHERE wo_id=:woId and template_oid=:templateOid",WorkOrderDetail.class);
            checkQuery.setParameter("woId",request.getWorkOrderDetailBn().getWoId());
            checkQuery.setParameter("templateOid",request.getWorkOrderDetailBn().getTemplateOid());

            boolean canDelete = true;
            List<WorkOrderDetail> workOrderDetailList = checkQuery.getResultList();
            for (int i = 0; i < workOrderDetailList.size(); i++) {
                if (workOrderDetailList.get(i).getStatusOid()!=101){
                    canDelete = false;
                    break;
                }
            }

            if (canDelete){

                Query deleteQuery = session.createNativeQuery("DELETE FROM work_order_detail WHERE wo_id=:woId AND template_oid=:templateOid");
                deleteQuery.setParameter("woId",request.getWorkOrderDetailBn().getWoId());
                deleteQuery.setParameter("templateOid",request.getWorkOrderDetailBn().getTemplateOid());
                deleteQuery.executeUpdate();

                response.setMsg("Work order detail delete successful !");
                response.setCode(200);

            }else {
                response.setMsg("You can not delete this work order details cause this work order details is not in new mood !");
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
}
