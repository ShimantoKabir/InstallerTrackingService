package com.installertrackingws.installertrackingws.webSocket.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.installertrackingws.installertrackingws.utility.communication.ConversationUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManagerFactory;

@Controller
public class ConversationWs {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @MessageMapping("/get-conversation-by-id")
    @SendTo("/ws-response/conversations")
    public String getByConversationId(String conversationId) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(new ConversationUtl().getByConversationId(entityManagerFactory,Integer.parseInt(conversationId)));
    }

}
