package com.installertrackingws.installertrackingws.utility.workOrder;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.WorkOrder.WoAssign;
import com.installertrackingws.installertrackingws.model.WorkOrder.WoAssignDetail;
import com.installertrackingws.installertrackingws.model.WorkOrder.WorkOrderDetail;
import com.installertrackingws.installertrackingws.utility.department.DepartmentUtl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

public class WoAssignUtl {

    public static Response getInitData(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        Response workOrderRes = WorkOrderUtl.getAllWorkOder(entityManagerFactory);
        List<DepartmentBn> departmentBnList = new DepartmentUtl().getDepartmentList(entityManagerFactory);

        response.setWorkOrderList(workOrderRes.getList());
        response.setDepartmentBnList(departmentBnList);

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

            for (int i = 0; i < request.getWoAssignBn().getWoAssignDetailList().size(); i++) {

                WoAssignDetail woAssignDetail = new WoAssignDetail();
                woAssignDetail.setBreakDown(request.getWoAssignBn().getWoAssignDetailList().get(i).getBreakDown());
                woAssignDetail.setCost(request.getWoAssignBn().getWoAssignDetailList().get(i).getCost());
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
}
