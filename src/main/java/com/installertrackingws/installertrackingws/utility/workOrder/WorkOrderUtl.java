package com.installertrackingws.installertrackingws.utility.workOrder;

import com.installertrackingws.installertrackingws.bean.workOrder.WorkOrderBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.WorkOrder.WorkOrder;
import com.installertrackingws.installertrackingws.utility.etc.StatusUtl;
import com.installertrackingws.installertrackingws.utility.material.SiteUtl;
import com.installertrackingws.installertrackingws.utility.user.UserUtl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkOrderUtl {

    public static Response getInitData(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        Response userRes = new UserUtl().getAllUser(entityManagerFactory);
        Response siteRes = new SiteUtl().getAllSite(entityManagerFactory);
        Response workOrderRes = WorkOrderUtl.getAllWorkOder(entityManagerFactory);
        Response statusRes = StatusUtl.getAllStatus(entityManagerFactory);

        response.setCode(200);
        response.setMsg("Init data getting successful !");
        response.setUserList(userRes.getList());
        response.setSiteList(siteRes.getList());
        response.setStatusList(statusRes.getList());
        response.setWorkOrderList(workOrderRes.getList());

        return response;

    }

    public static Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, WorkOrderBn workOrderBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            WorkOrder workOrder = new WorkOrder();
            workOrder.setName(workOrderBn.getName());
            workOrder.setSiteId(workOrderBn.getSiteId());
            workOrder.setWoPiCh(workOrderBn.getWoPiCh());
            workOrder.setRequester(workOrderBn.getRequester());
            workOrder.setDeadLine(workOrderBn.getDeadLine());
            workOrder.setRemark(workOrderBn.getRemark());
            workOrder.setIp(httpServletRequest.getRemoteAddr());
            workOrder.setModifiedBy(workOrderBn.getModifiedBy());
            workOrder.setSitePiCt(workOrderBn.getSitePiCt());
            workOrder.setStatusOid(workOrderBn.getStatusOid());
            session.save(workOrder);

            response.setMsg("Work order save successfully !");
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

    public static Response getAllWorkOder(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT work_order.id, \n" +
                "work_order.name AS work_order_name, \n" +
                "work_order.site_id, \n" +
                "work_order.wo_pi_ch, \n" +
                "work_order.requester, \n" +
                "CAST(work_order.dead_line AS DATE) AS dead_line, \n" +
                "work_order.remark, \n" +
                "site.name AS site_name, \n" +
                "uOne.user_name AS wo_pi_ch_name, \n" +
                "uTwo.user_name AS requester_name,\n" +
                "status.name AS STATUS,\n" +
                "work_order.status_oid,\n" +
                "work_order.site_pi_ct\n" +
                "FROM work_order \n" +
                "INNER JOIN site ON site.id=work_order.site_id \n" +
                "INNER JOIN USER uOne ON uOne.id=work_order.wo_pi_ch \n" +
                "INNER JOIN USER uTwo ON uTwo.id=work_order.requester\n" +
                "INNER JOIN STATUS ON STATUS.o_id=work_order.status_oid\n");

        List<Object[]> results = query.getResultList();

        List<WorkOrderBn> workOrderList = new ArrayList<>();

        for (Object[] result : results) {
            WorkOrderBn workOrderBn = new WorkOrderBn();
            workOrderBn.setId((Integer) result[0]);
            workOrderBn.setName((String) result[1]);
            workOrderBn.setSiteId((Integer) result[2]);
            workOrderBn.setWoPiCh((Integer) result[3]);
            workOrderBn.setRequester((Integer) result[4]);
            workOrderBn.setDeadLine((Date) result[5]);
            workOrderBn.setRemark((String) result[6]);
            workOrderBn.setSiteName((String) result[7]);
            workOrderBn.setWoPiChName((String) result[8]);
            workOrderBn.setRequesterName((String) result[9]);
            workOrderBn.setStatus((String) result[10]);
            workOrderBn.setStatusOid((Integer) result[11]);
            workOrderBn.setSitePiCt((String) result[12]);
            workOrderList.add(workOrderBn);
        }

        session.getTransaction().commit();
        session.close();

        if (workOrderList.size()>0){
            response.setCode(200);
            response.setMsg("Work order list fetch successful !");
            response.setList(workOrderList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Work order list list empty !");
            return response;
        }

    }

    public static Response update(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, WorkOrderBn workOrderBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createNativeQuery("UPDATE work_order SET name=:name, site_id=:siteId, wo_pi_ch=:woPiCh, requester=:requester, dead_line=:deadLine, remark=:remark, ip=:ip, modified_by=:modifiedBy WHERE id = :id");
            query.setParameter("name",workOrderBn.getName());
            query.setParameter("siteId",workOrderBn.getSiteId());
            query.setParameter("woPiCh",workOrderBn.getWoPiCh());
            query.setParameter("requester",workOrderBn.getRequester());
            query.setParameter("deadLine",workOrderBn.getDeadLine());
            query.setParameter("remark",workOrderBn.getRemark());
            query.setParameter("ip",httpServletRequest.getRemoteAddr());
            query.setParameter("modifiedBy",workOrderBn.getModifiedBy());
            query.setParameter("id",workOrderBn.getId());
            query.executeUpdate();

            response.setMsg("Work order update successfully !");
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

    public static Response delete(EntityManagerFactory entityManagerFactory, WorkOrderBn workOrderBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT * FROM work_order_detail WHERE wo_id = :woId");
            checkQuery.setParameter("woId",workOrderBn.getId());

            if (checkQuery.getResultList().size()>0){

                response.setMsg("This work order already been exist in work order details !");
                response.setCode(400);

            }else {

                Query deleteQuery = session.createNativeQuery("DELETE FROM work_order WHERE id = :id");
                deleteQuery.setParameter("id",workOrderBn.getId());
                deleteQuery.executeUpdate();

                response.setMsg("Work order delete successful !");
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
}
