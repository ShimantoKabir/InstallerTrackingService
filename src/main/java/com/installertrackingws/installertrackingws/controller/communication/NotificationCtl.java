package com.installertrackingws.installertrackingws.controller.communication;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.communication.NotificationUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/notification")
public class NotificationCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/get-by-receiver")
    public Response getNotificationByReceiver(@RequestBody Request request){

        return new NotificationUtl().getNotificationByReceiver(entityManagerFactory,request);

    }

    @PostMapping("/seen")
    public Response seenNotification(@RequestBody Request request){

        return new NotificationUtl().seenNotification(entityManagerFactory,request);

    }

    @PostMapping("/create")
    public Response create(HttpServletRequest httpServletRequest,@RequestBody Request request){

        return new NotificationUtl().create(httpServletRequest,entityManagerFactory,request);

    }

}
