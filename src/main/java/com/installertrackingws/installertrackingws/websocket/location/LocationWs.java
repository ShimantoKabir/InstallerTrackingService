package com.installertrackingws.installertrackingws.websocket.location;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.installertrackingws.installertrackingws.bean.communication.NotificationBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.utility.communication.NotificationUtl;
import com.installertrackingws.installertrackingws.utility.location.LocationUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Type;

@Controller
public class LocationWs {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @MessageMapping("/get-location-by-work-order")
    @SendTo("/ws-response/location-by-work-order")
    public String getLocationByWorkOrder(String receiver) {
        Gson gson = new GsonBuilder().create();
        Request request = gson.fromJson(receiver,Request.class);
        return gson.toJson(new LocationUtl().trackByWorkOrder(entityManagerFactory,request));
    }

    @MessageMapping("/get-location-by-user")
    @SendTo("/ws-response/location-by-user")
    public String getLocationByUser(String receiver) {
        Gson gson = new GsonBuilder().create();
        Request request = gson.fromJson(receiver,Request.class);
        return gson.toJson(new LocationUtl().trackByUser(entityManagerFactory,request));
    }

}
