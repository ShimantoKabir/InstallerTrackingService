package com.installertrackingws.installertrackingws.controller.material;

import com.installertrackingws.installertrackingws.bean.material.SiteBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.material.SiteUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/site")
public class SiteCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody SiteBn siteBn){

        return new SiteUtl().save(httpServletRequest,entityManagerFactory,siteBn);

    }

    @GetMapping("/get")
    public Response getAllSite(){

        return new SiteUtl().getAllSite(entityManagerFactory);

    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody SiteBn siteBn){

        return new SiteUtl().update(httpServletRequest,entityManagerFactory,siteBn);

    }

}
