package com.installertrackingws.installertrackingws.controller.setup;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.setup.SuperviseConfigUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/supervise-config")
public class SuperviseConfigCtl {

    @Autowired
    public EntityManagerFactory entityManagerFactory;
    public SuperviseConfigUtl superviseConfigUtl = new SuperviseConfigUtl();

    @PostMapping("/get-init-data")
    public Response get(@RequestBody Request request){

        return superviseConfigUtl.getInitData(entityManagerFactory);

    }

    @PostMapping("/create")
    public Response create(HttpServletRequest httpServletRequest,@RequestBody Request request){

        return superviseConfigUtl.create(httpServletRequest,entityManagerFactory,request);

    }

}
