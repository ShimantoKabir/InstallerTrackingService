package com.installertrackingws.installertrackingws.controller.treasure;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.treasure.CostBreakDownBn;
import com.installertrackingws.installertrackingws.utility.treasure.CostBreakDownBnUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cost-break-down")
public class
CostBreakDownBnCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody Request request){

        return CostBreakDownBnUtl.save(httpServletRequest,entityManagerFactory,request);

    }

    @GetMapping("/get")
    public Response get(){

        return CostBreakDownBnUtl.getAllCostBreakDown(entityManagerFactory);

    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody Request request){

        return CostBreakDownBnUtl.update(httpServletRequest,entityManagerFactory,request);

    }

}
