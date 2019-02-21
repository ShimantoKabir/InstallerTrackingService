package com.installertrackingws.installertrackingws.websocket.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.utility.user.UserUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManagerFactory;

@Controller
public class UserWs {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @Scheduled(fixedDelay=1000)
    public void getAllUser(){

        Gson gson = new GsonBuilder().create();
        this.messageTemplate.convertAndSend("/ws-response/user/get",gson.toJson(new UserUtl().getAllUser(entityManagerFactory)));

    }

    @MessageMapping("/update-user-presence")
    @SendTo("/ws-response/presence-status")
    public String updateUserPresence(String receiver) {
        Gson gson = new GsonBuilder().create();
        Request request = gson.fromJson(receiver,Request.class);
        return gson.toJson(new UserUtl().updateUserPresence(entityManagerFactory,request));
    }

}
