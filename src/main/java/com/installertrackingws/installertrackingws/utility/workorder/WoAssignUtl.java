package com.installertrackingws.installertrackingws.utility.workorder;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.workorder.WoAssignBn;
import com.installertrackingws.installertrackingws.bean.workorder.WoAssignDetailBn;
import com.installertrackingws.installertrackingws.model.workorder.WoAssign;
import com.installertrackingws.installertrackingws.model.workorder.WoAssignDetail;
import com.installertrackingws.installertrackingws.utility.accounts.CostBreakDownUtl;
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
        Response cbdRes = CostBreakDownUtl.getAllCostBreakDown(entityManagerFactory);

        response.setWorkOrderList(workOrderRes.getList());
        response.setDepartmentBnList(departmentBnList);
        response.setWoAssignBnList(woAssignRes.getList());
        response.setCostBreakDownList(cbdRes.getList());

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
            woAssign.setDeptOid(request.getWoAssignBn().getDeptOid());
            woAssign.setIp(httpServletRequest.getRemoteAddr());
            woAssign.setModifiedBy(request.getWoAssignBn().getModifiedBy());
            woAssign.setRemark(request.getWoAssignBn().getRemark());
            woAssign.setScope(request.getWoAssignBn().getScope());
            woAssign.setStatusOid(101);
            woAssign.setoId(maxOid.intValue());
            session.save(woAssign);

            for (int i = 0; i < request.getWoAssignDetailBnList().size(); i++) {

                WoAssignDetail woAssignDetail = new WoAssignDetail();
                woAssignDetail.setCbdId(request.getWoAssignDetailBnList().get(i).getId());
                woAssignDetail.setCost(request.getWoAssignDetailBnList().get(i).getCost());
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
                "user.user_name AS assign_user_name,\n" +
                "work_order.name AS work_order_name,\n" +
                "status.name AS status_name,\n" +
                "wo_assign.dept_oid,\n" +
                "department.name AS dept_name\n" +
                "FROM wo_assign\n" +
                "INNER JOIN user ON user.id=wo_assign.assign_to\n" +
                "INNER JOIN work_order ON work_order.id=wo_assign.wo_id\n" +
                "INNER JOIN status ON status.o_id=wo_assign.status_oid\n" +
                "INNER JOIN department ON department.o_id=wo_assign.dept_oid\n");


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
            woAssignBn.setDeptOid((Integer) result[12]);
            woAssignBn.setDeptName((String) result[13]);

            Query woAssignDetailQuery = session.createNativeQuery("SELECT \n" +
                    "wo_assign_detail.id,\n" +
                    "wo_assign_detail.cost,\n" +
                    "cost_break_down.name,\n" +
                    "wo_assign_detail.wo_assign_oid,\n" +
                    "wo_assign_detail.cbd_id\n" +
                    "FROM wo_assign_detail\n" +
                    "INNER JOIN cost_break_down ON cost_break_down.id=wo_assign_detail.cbd_id\n" +
                    "WHERE wo_assign_detail.wo_assign_oid = :woAssignOid");

            woAssignDetailQuery.setParameter("woAssignOid",result[1]);

            List<Object[]> wadResults = woAssignDetailQuery.getResultList();

            System.out.println(wadResults.size());

            List<WoAssignDetailBn> woAssignDetailBnList = new ArrayList<>();

            for (Object[] wadResult : wadResults) {

                WoAssignDetailBn woAssignDetailBn = new WoAssignDetailBn();
                woAssignDetailBn.setId((Integer) wadResult[0]);
                woAssignDetailBn.setCost((String) wadResult[1]);
                woAssignDetailBn.setName((String) wadResult[2]);
                woAssignDetailBn.setWoAssignOid((Integer) wadResult[3]);
                woAssignDetailBn.setCbdId((Integer) wadResult[4]);
                woAssignDetailBnList.add(woAssignDetailBn);

            }

            woAssignBn.setWoAssignDetailBnList(woAssignDetailBnList);

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

    public static Response update(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query checkQuery = session.createNativeQuery("SELECT * FROM wo_assign WHERE id=:id",WoAssign.class);
            checkQuery.setParameter("id",request.getWoAssignBn().getId());

            WoAssign woAssign = (WoAssign) checkQuery.getSingleResult();

            if (woAssign.getStatusOid()==101){

                Query deleteQuery = session.createNativeQuery("DELETE FROM wo_assign_detail WHERE wo_assign_oid=:woAssignOid");
                deleteQuery.setParameter("woAssignOid",woAssign.getoId());
                deleteQuery.executeUpdate();

                Query updateQuery = session.createNativeQuery("UPDATE\n" +
                        "wo_assign\n" +
                        "SET wo_id=:woId,\n" +
                        "dept_oid=:deptOid,\n" +
                        "assign_to=:assignTo,\n" +
                        "assign_date=:assignDate,\n" +
                        "assign_time=:assignTime,\n" +
                        "remark=:remark, \n" +
                        "scope=:scope,\n" +
                        "ip=:ip,\n" +
                        "modified_by=:modifiedBy \n" +
                        "WHERE id = :id");

                updateQuery.setParameter("woId",request.getWoAssignBn().getWoId());
                updateQuery.setParameter("deptOid",request.getWoAssignBn().getDeptOid());
                updateQuery.setParameter("assignTo",request.getWoAssignBn().getAssignTo());
                updateQuery.setParameter("assignDate",request.getWoAssignBn().getAssignDate());
                updateQuery.setParameter("assignTime",request.getWoAssignBn().getAssignTime());
                updateQuery.setParameter("remark",request.getWoAssignBn().getRemark());
                updateQuery.setParameter("scope",request.getWoAssignBn().getScope());
                updateQuery.setParameter("ip",httpServletRequest.getRemoteAddr());
                updateQuery.setParameter("modifiedBy",request.getWoAssignBn().getModifiedBy());
                updateQuery.setParameter("id",request.getWoAssignBn().getId());
                updateQuery.executeUpdate();

                for (int i = 0; i < request.getWoAssignDetailBnList().size(); i++) {

                    WoAssignDetail woAssignDetail = new WoAssignDetail();

                    woAssignDetail.setCbdId(request.getWoAssignDetailBnList().get(i).getCbdId());
                    woAssignDetail.setCost(request.getWoAssignDetailBnList().get(i).getCost());
                    woAssignDetail.setWoAssignOid(woAssign.getoId());
                    woAssignDetail.setModifiedBy(request.getWoAssignBn().getModifiedBy());
                    woAssignDetail.setIp(httpServletRequest.getRemoteAddr());
                    session.save(woAssignDetail);

                }

                response.setMsg("Work order assign update successfully !");
                response.setCode(200);

            }else {

                response.setMsg("You can't update this assign work order, cause some is working on this work order !");
                response.setCode(400);

            }

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg(e.getMessage());
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

            Query checkQuery = session.createNativeQuery("SELECT * FROM wo_assign WHERE id=:id",WoAssign.class);
            checkQuery.setParameter("id",request.getWoAssignBn().getId());

            WoAssign woAssign = (WoAssign) checkQuery.getSingleResult();

            if (woAssign.getStatusOid()==101){

                Query deleteWoQuery = session.createNativeQuery("DELETE FROM wo_assign WHERE id=:id");
                deleteWoQuery.setParameter("id",woAssign.getId());
                deleteWoQuery.executeUpdate();

                Query deleteWoaQuery = session.createNativeQuery("DELETE FROM wo_assign_detail WHERE wo_assign_oid=:woAssignOid");
                deleteWoaQuery.setParameter("woAssignOid",woAssign.getoId());
                deleteWoaQuery.executeUpdate();

                response.setMsg("Assign work order delete successful !");
                response.setCode(200);

            }else {

                response.setMsg("You can't delete this assign work order, cause some is working on this work order !");
                response.setCode(400);

            }

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg(e.getMessage());
            response.setCode(400);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;

    }
}
