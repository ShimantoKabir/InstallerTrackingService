package com.installertrackingws.installertrackingws.controller.mobile;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.mobile.DatabaseUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("/database")
public class DatabaseCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/get")
    public Response get(@RequestBody Request request){

        return new DatabaseUtl().get(entityManagerFactory);

    }

}
