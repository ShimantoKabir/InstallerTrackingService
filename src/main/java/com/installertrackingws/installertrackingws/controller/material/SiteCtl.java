package com.installertrackingws.installertrackingws.controller.material;

import com.installertrackingws.installertrackingws.bean.network.Request;
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
    public Response save(HttpServletRequest httpServletRequest, @RequestBody Request request){

        return new SiteUtl().save(httpServletRequest,entityManagerFactory,request);

    }

    @PostMapping("/get")
    public Response getAllSite(@RequestBody Request request){

        return new SiteUtl().getAllSite(entityManagerFactory);

    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody Request request){

        return new SiteUtl().update(httpServletRequest,entityManagerFactory,request);

    }

}
