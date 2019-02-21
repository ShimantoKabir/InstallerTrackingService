package com.installertrackingws.installertrackingws.controller.router;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.router.RouterUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("/router")
public class RouterCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/get-by-department")
    public Response getRouterByDepartment(@RequestBody Request request){

        return new RouterUtl().getRouterByDepartment(entityManagerFactory,request);

    }

}
