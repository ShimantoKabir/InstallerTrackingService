package com.installertrackingws.installertrackingws.controller.etc;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.etc.CompanyUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("/company")
public class CompanyCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/get")
    public Response get(@RequestBody Request request){

        return new CompanyUtl().get(entityManagerFactory,request);

    }

}
