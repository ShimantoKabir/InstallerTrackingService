package com.installertrackingws.installertrackingws.utility.workOrder;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.workOrder.WoAssignBn;
import com.installertrackingws.installertrackingws.model.WorkOrder.WoAssign;
import com.installertrackingws.installertrackingws.model.WorkOrder.WoAssignDetail;
import com.installertrackingws.installertrackingws.utility.department.DepartmentUtl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WoAssignUtl {

    public static Response getInitData(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        Response workOrderRes = WorkOrderUtl.getAllWorkOder(entityManagerFactory);
        List<DepartmentBn> departmentBnList = new DepartmentUtl().getDepartmentList(entityManagerFactory);
        Response woAssignRes = getALLAssignWorkOrder(entityManagerFactory);

        response.setWorkOrderList(workOrderRes.getList());
        response.setDepartmentBnList(departmentBnList);
        response.setWoAssignBnList(woAssignRes.getList());

        response.setMsg("Init data fetch successful !");
        response.setCode(200);

        return response;

    }

    public static Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            BigInteger maxOid = (BigInteger) session.createNativeQuery("SELECT IFNULL(MAX(o_id),100)+1 AS o_id FROM wo_assign").getResultList().get(0);

            WoAssign woAssign = new WoAssign();
            woAssign.setWoId(request.getWoAssignBn().getWoId());
            woAssign.setAssignDate(request.getWoAssignBn().getAssignDate());
            woAssign.setAssignTime(request.getWoAssignBn().getAssignTime());
            woAssign.setAssignTo(request.getWoAssignBn().getAssignTo());
            woAssign.setIp(httpServletRequest.getRemoteAddr());
            woAssign.setModifiedBy(request.getWoAssignBn().getModifiedBy());
            woAssign.setRemark(request.getWoAssignBn().getRemark());
            woAssign.setScope(request.getWoAssignBn().getScope());
            woAssign.setStatusOid(101);
            woAssign.setoId(maxOid.intValue());
            session.save(woAssign);

            for (int i = 0; i < request.getWoAssignBn().getWoAssignDetailBnList().size(); i++) {

                WoAssignDetail woAssignDetail = new WoAssignDetail();
                woAssignDetail.setBreakDown(request.getWoAssignBn().getWoAssignDetailBnList().get(i).getBreakDown());
                woAssignDetail.setCost(request.getWoAssignBn().getWoAssignDetailBnList().get(i).getCost());
                woAssignDetail.setWoAssignOid(maxOid.intValue());
                woAssignDetail.setModifiedBy(request.getWoAssignBn().getModifiedBy());
                woAssignDetail.setIp(httpServletRequest.getRemoteAddr());
                session.save(woAssignDetail);

            }

            response.setMsg("Work order assigned successfully !");
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

    public static Response getALLAssignWorkOrder(EntityManagerFactory entityManagerFactory){

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query woAssignQuery = session.createNativeQuery("SELECT \n" +
                "wo_assign.id,\n" +
                "wo_assign.o_id, \n" +
                "wo_assign.wo_id,\n" +
                "wo_assign.assign_to,\n" +
                "CAST(wo_assign.assign_date AS DATE) AS assign_date,\n" +
                "wo_assign.assign_time,\n" +
                "wo_assign.status_oid,\n" +
                "wo_assign.scope,\n" +
                "wo_assign.remark,\n" +
                "user.user_name as assign_user_name,\n" +
                "work_order.name as work_order_name,\n" +
                "status.name as status_name\n" +
                "FROM wo_assign\n" +
                "INNER JOIN user ON user.id=wo_assign.assign_to\n" +
                "INNER JOIN work_order ON work_order.id=wo_assign.wo_id\n" +
                "INNER JOIN status ON status.o_id=wo_assign.status_oid\n");


        List<Object[]> results = woAssignQuery.getResultList();
        List<WoAssignBn> woAssignBnList = new ArrayList<>();

        for (Object[] result : results) {

            WoAssignBn woAssignBn = new WoAssignBn();
            woAssignBn.setId((Integer) result[0]);
            woAssignBn.setoId((Integer) result[1]);
            woAssignBn.setWoId((Integer) result[2]);
            woAssignBn.setAssignTo((Integer) result[3]);
            woAssignBn.setAssignDate((Date) result[4]);
            woAssignBn.setAssignTime((String) result[5]);
            woAssignBn.setStatusOid((Integer) result[6]);
            woAssignBn.setScope((String) result[7]);
            woAssignBn.setRemark((String) result[8]);
            woAssignBn.setAssignUserName((String) result[9]);
            woAssignBn.setWorkOrderName((String) result[10]);
            woAssignBn.setStatusName((String) result[11]);

            Query woAssignDetailQuery = session.createNativeQuery("select * from wo_assign_detail where wo_assign_oid=:woAssignOid",WoAssignDetail.class);
            woAssignDetailQuery.setParameter("woAssignOid",result[1]);

            List<WoAssignDetail> woAssignDetailList = woAssignDetailQuery.getResultList();
            woAssignBn.setWoAssignDetailList(woAssignDetailList);

            woAssignBnList.add(woAssignBn);

        }

        session.getTransaction().commit();
        session.close();

        if (woAssignBnList.size()>0){
            response.setCode(200);
            response.setMsg("Work order assign list fetch successful !");
            response.setList(woAssignBnList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Work order assign list empty !");
            return response;
        }

    }

}
