package com.installertrackingws.installertrackingws.controller.material;

import com.installertrackingws.installertrackingws.bean.material.TemplateBn;
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
    public Response save(HttpServletRequest httpServletRequest, @RequestBody TemplateBn templateBn){

        return new TemplateUtl().save(httpServletRequest,entityManagerFactory,templateBn);

    }

    @GetMapping("/get")
    public Response getTemplateWithTask(){

        return new TemplateUtl().getTemplateWithTask(entityManagerFactory);

    }


    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody TemplateBn templateBn){

        return new TemplateUtl().update(httpServletRequest,entityManagerFactory,templateBn);

    }


}
