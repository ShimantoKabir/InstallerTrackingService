package com.installertrackingws.installertrackingws.controller.communication;

import com.installertrackingws.installertrackingws.bean.communication.FriendRequestBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.communication.FriendRequestUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend-request")
public class FriendRequestCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/check-friend")
    public Response checkFriend(@RequestBody Request request){

        return new FriendRequestUtl().checkFriend(entityManagerFactory,request);

    }

    @PostMapping("/manage")
    public Response manageFriendRequest(HttpServletRequest httpServletRequest,@RequestBody Request request){

        return new FriendRequestUtl().manageFriendRequest(httpServletRequest,entityManagerFactory,request);

    }

}
