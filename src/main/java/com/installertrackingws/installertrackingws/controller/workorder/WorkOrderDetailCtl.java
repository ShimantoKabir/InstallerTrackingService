package com.installertrackingws.installertrackingws.controller.workorder;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.workorder.WorkOrderDetailUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("/work-order-detail")
public class WorkOrderDetailCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/get-init-data")
    public Response getInitData(){
        return WorkOrderDetailUtl.getInitData(entityManagerFactory);
    }

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody Request request) throws ParseException {
        return WorkOrderDetailUtl.save(httpServletRequest,entityManagerFactory,request);
    }

    @GetMapping("/get")
    public Response getAllWorkOderDetail(){
        return WorkOrderDetailUtl.getAllWorkOderDetail(entityManagerFactory);
    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody Request request) {
        return WorkOrderDetailUtl.update(httpServletRequest,entityManagerFactory,request);
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody Request request) {
        return WorkOrderDetailUtl.delete(entityManagerFactory,request);
    }

}
