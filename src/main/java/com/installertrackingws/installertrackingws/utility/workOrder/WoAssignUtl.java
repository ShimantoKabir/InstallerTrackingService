package com.installertrackingws.installertrackingws.utility.workOrder;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.department.DepartmentUtl;
import com.installertrackingws.installertrackingws.utility.treasure.CostBreakDownBnUtl;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class WoAssignUtl {

    public static Response getInitData(EntityManagerFactory entityManagerFactory) {

        Response response = new Response();

        Response workOrderRes = WorkOrderUtl.getAllWorkOder(entityManagerFactory);
        List<DepartmentBn> departmentBnList = new DepartmentUtl().getDepartmentList(entityManagerFactory);
        Response cbdRes = CostBreakDownBnUtl.getAllCostBreakDown(entityManagerFactory);

        response.setWorkOrderList(workOrderRes.getList());
        response.setDepartmentBnList(departmentBnList);
        response.setCbdList(cbdRes.getList());

        response.setMsg("Init data fetch successful !");
        response.setCode(200);

        return response;

    }
}
