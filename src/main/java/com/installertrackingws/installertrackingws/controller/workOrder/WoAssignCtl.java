package com.installertrackingws.installertrackingws.controller.workOrder;

import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.workOrder.WoAssignUtl;
import com.installertrackingws.installertrackingws.utility.workOrder.WorkOrderUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("/wo-assign")
public class WoAssignCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/init")
    public Response getInitData(){
        return WoAssignUtl.getInitData(entityManagerFactory);
    }

}
