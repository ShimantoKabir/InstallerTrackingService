package com.installertrackingws.installertrackingws.websocket.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.installertrackingws.installertrackingws.bean.communication.NotificationBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.utility.communication.NotificationUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManagerFactory;

@Controller
public class NotificationWs {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @MessageMapping("/get-notification-by-receiver")
    @SendTo("/ws-response/notifications")
    public String getNotificationByReceiver(String receiver) {
        Gson gson = new GsonBuilder().create();
        NotificationBn notificationBn = new NotificationBn();
        notificationBn.setReceiver(Integer.parseInt(receiver));
        Request request = new Request();
        return gson.toJson(new NotificationUtl().getNotificationByReceiver(entityManagerFactory,request));
    }

}
