package com.installertrackingws.installertrackingws.controller.accounts;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.accounts.CostBreakDownUtl;
import com.installertrackingws.installertrackingws.utility.auth.Auth;
import com.installertrackingws.installertrackingws.utility.menu.MenuUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cost-break-down")
public class CostBreakDownCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody Request request){

        if (Auth.isValid(entityManagerFactory,request)){
            return CostBreakDownUtl.save(httpServletRequest,entityManagerFactory,request);
        }else {
            return  Auth.response();
        }

    }

    @GetMapping("/get")
    public Response get(){

        return CostBreakDownUtl.getAllCostBreakDown(entityManagerFactory);

    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody Request request){

        if (MenuUtl.isPermitted(entityManagerFactory,request)){
            return CostBreakDownUtl.update(httpServletRequest,entityManagerFactory,request);
        }else {
            return MenuUtl.response();
        }

    }


}
