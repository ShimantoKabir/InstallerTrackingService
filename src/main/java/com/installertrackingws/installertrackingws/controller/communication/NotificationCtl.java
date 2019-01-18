package com.installertrackingws.installertrackingws.controller.communication;

import com.installertrackingws.installertrackingws.bean.communication.NotificationBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.communication.NotificationUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("/notification")
public class NotificationCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/get-by-receiver")
    public Response getNotificationByReceiver(@RequestBody NotificationBn notificationBn){

        return new NotificationUtl().getNotificationByReceiver(entityManagerFactory,notificationBn);

    }

    @PostMapping("/seen")
    public Response seenNotification(@RequestBody NotificationBn notificationBn){

        return new NotificationUtl().seenNotification(entityManagerFactory,notificationBn);

    }

}
