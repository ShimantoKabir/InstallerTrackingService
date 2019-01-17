package com.installertrackingws.installertrackingws.controller.workOrder;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.workOrder.WorkOrderBn;
import com.installertrackingws.installertrackingws.utility.workOrder.WoAssignUtl;
import com.installertrackingws.installertrackingws.utility.workOrder.WorkOrderUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wo-assign")
public class WoAssignCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/init")
    public Response getInitData(){
        return WoAssignUtl.getInitData(entityManagerFactory);
    }

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody Request request){
        return WoAssignUtl.save(httpServletRequest,entityManagerFactory,request);
    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody Request request){
        return WoAssignUtl.update(httpServletRequest,entityManagerFactory,request);
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody Request request){
        return WoAssignUtl.delete(entityManagerFactory,request);
    }

}
