package com.installertrackingws.installertrackingws.controller.location;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.utility.location.LocationUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("/location")
public class LocationCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/track-by-user")
    public Response trackByUser(@RequestBody Request request){

        return new LocationUtl().trackByUser(entityManagerFactory,request);

    }

    @PostMapping("/count-by-user")
    public int countByUser(@RequestBody UserBn userBn){
        return new LocationUtl().countByUser(entityManagerFactory,userBn);
    }

    @PostMapping("/get-new")
    public Response getNew(@RequestBody UserBn userBn){
        return new LocationUtl().getNew(entityManagerFactory,userBn);
    }

    @PostMapping("/track-by-work-order")
    public Response trackByWorkOrder(@RequestBody Request request){

        return new LocationUtl().trackByWorkOrder(entityManagerFactory,request);

    }

}
