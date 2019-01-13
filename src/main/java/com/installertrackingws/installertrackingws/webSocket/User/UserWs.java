package com.installertrackingws.installertrackingws.webSocket.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.installertrackingws.installertrackingws.utility.user.UserUtl;
import org.springframework.beans.factory.annotation.Autowired;
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

}
