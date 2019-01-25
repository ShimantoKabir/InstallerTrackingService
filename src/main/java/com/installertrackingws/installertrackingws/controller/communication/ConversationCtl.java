package com.installertrackingws.installertrackingws.controller.communication;

import com.installertrackingws.installertrackingws.bean.communication.ConversationBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.communication.ConversationUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/conversation")
public class ConversationCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/get-by-conversation-id")
    public Response getByConversationId(@RequestBody Request request){

        return new ConversationUtl().getByConversationId(entityManagerFactory,request.getConversationBn().getConversationId());

    }

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest,@RequestBody Request request){

        return new ConversationUtl().save(httpServletRequest,entityManagerFactory,request);

    }

    @PostMapping("/seen-the-unseen")
    public Response seenTheUnseen(@RequestBody Request request){

        return new ConversationUtl().seenTheUnseen(entityManagerFactory,request);

    }

}
