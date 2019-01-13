package com.installertrackingws.installertrackingws.controller.workOrder;

import com.installertrackingws.installertrackingws.bean.workOrder.WorkOrderBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.workOrder.WorkOrderUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/work-order")
public class WorkOrderCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/get-init-data")
    public Response getInitData(){
        return WorkOrderUtl.getInitData(entityManagerFactory);
    }

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest,@RequestBody WorkOrderBn workOrderBn){
        return WorkOrderUtl.save(httpServletRequest,entityManagerFactory,workOrderBn);
    }

    @GetMapping("/get")
    public Response getAllWorkOder(){
        return WorkOrderUtl.getAllWorkOder(entityManagerFactory);
    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest,@RequestBody WorkOrderBn workOrderBn){
        return WorkOrderUtl.update(httpServletRequest,entityManagerFactory,workOrderBn);
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody WorkOrderBn workOrderBn){
        return WorkOrderUtl.delete(entityManagerFactory,workOrderBn);
    }

}

