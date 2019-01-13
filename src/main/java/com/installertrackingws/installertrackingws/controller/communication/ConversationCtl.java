package com.installertrackingws.installertrackingws.controller.communication;

import com.installertrackingws.installertrackingws.bean.communication.ConversationBn;
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
    public Response getByConversationId(@RequestBody ConversationBn conversationBn){

        return new ConversationUtl().getByConversationId(entityManagerFactory,conversationBn.getConversationId());

    }

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest,@RequestBody ConversationBn conversationBn){

        return new ConversationUtl().save(httpServletRequest,entityManagerFactory,conversationBn);

    }

    @PostMapping("/seen-the-unseen")
    public Response seenTheUnseen(@RequestBody ConversationBn conversationBn){

        return new ConversationUtl().seenTheUnseen(entityManagerFactory,conversationBn);

    }

}
