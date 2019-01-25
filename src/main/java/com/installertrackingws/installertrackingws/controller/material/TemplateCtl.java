package com.installertrackingws.installertrackingws.controller.material;

import com.installertrackingws.installertrackingws.bean.material.TemplateBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.material.TemplateUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/template")
public class TemplateCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody Request request){

        return new TemplateUtl().save(httpServletRequest,entityManagerFactory,request);

    }

    @GetMapping("/get")
    public Response getTemplateWithTask(){

        return new TemplateUtl().getTemplateWithTask(entityManagerFactory);

    }


    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody Request request){

        return new TemplateUtl().update(httpServletRequest,entityManagerFactory,request);

    }

    @PostMapping("/init-data")
    public Response getInitData(@RequestBody Request request){

        return new TemplateUtl().getInitData(entityManagerFactory,request);

    }

}
