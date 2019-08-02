package com.installertrackingws.installertrackingws.utility.mobile;

import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.Etc.Status;
import com.installertrackingws.installertrackingws.model.accounts.CostBreakDown;
import com.installertrackingws.installertrackingws.model.department.Department;
import com.installertrackingws.installertrackingws.model.material.Site;
import com.installertrackingws.installertrackingws.model.material.Task;
import com.installertrackingws.installertrackingws.model.material.Template;
import com.installertrackingws.installertrackingws.model.material.TemplateDetail;
import com.installertrackingws.installertrackingws.model.setup.CompanyInfo;
import com.installertrackingws.installertrackingws.model.setup.SuperviseConfig;
import com.installertrackingws.installertrackingws.model.user.User;
import com.installertrackingws.installertrackingws.model.workorder.WoAssign;
import com.installertrackingws.installertrackingws.model.workorder.WoAssignDetail;
import com.installertrackingws.installertrackingws.model.workorder.WorkOrder;
import com.installertrackingws.installertrackingws.model.workorder.WorkOrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;

public class DatabaseUtl {

    public Response get(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        response.setSiteList(session.createNativeQuery("SELECT * FROM site ",Site.class).getResultList());
        response.setCostBreakDownList(session.createNativeQuery("SELECT * FROM cost_break_down ",CostBreakDown.class).getResultList());
        response.setDepartmentList(session.createNativeQuery("SELECT * FROM department ",Department.class).getResultList());
        response.setStatusList(session.createNativeQuery("SELECT * FROM status ",Status.class).getResultList());
        response.setTaskList(session.createNativeQuery("SELECT * FROM task ",Task.class).getResultList());
        response.setTemplateList(session.createNativeQuery("SELECT * FROM template ",Template.class).getResultList());
        response.setTemplateDetailList(session.createNativeQuery("SELECT * FROM template_detail ",TemplateDetail.class).getResultList());
        response.setUserList(session.createNativeQuery("SELECT * FROM user ",User.class).getResultList());
        response.setWoAssignList(session.createNativeQuery("SELECT * FROM wo_assign ",WoAssign.class).getResultList());
        response.setWoAssignDetailList(session.createNativeQuery("SELECT * FROM wo_assign_detail ",WoAssignDetail.class).getResultList());
        response.setWorkOrderList(session.createNativeQuery("SELECT * FROM work_order ",WorkOrder.class).getResultList());
        response.setWorkOrderDetailList(session.createNativeQuery("SELECT * FROM work_order_detail ",WorkOrderDetail.class).getResultList());
        response.setSuperviseConfigList(session.createNativeQuery("SELECT * FROM supervise_config ",SuperviseConfig.class).getResultList());
        response.setCompanyInfo(session.createNativeQuery("SELECT * FROM company_info",CompanyInfo.class).getSingleResult());

        session.getTransaction().commit();
        session.close();

        return response;

    }

}
