package com.installertrackingws.installertrackingws.utility.social.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.stereotype.Service;

@Service
public class VkServiceImp implements VkService {

    public static int APP_ID = 7020734;
    public static String CLIENT_SECRET = "unk3fAa2wJY5MHoyvWeK";
    public static String REDIRECT_URI = "http://localhost:3307/vk/get-access-token";

    public VkApiClient createVkApiClient(){

        TransportClient transportClient = HttpTransportClient.getInstance();
        return new VkApiClient(transportClient);

    }

    @Override
    public String getAccessToken(String code) {

        UserAuthResponse userAuthResponse = null;

        try {

            userAuthResponse = createVkApiClient().oauth().userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code).execute();
            System.out.println("VK user email = "+userAuthResponse.getEmail());

        } catch (Exception e) {

            System.out.println("Vk auth response error = "+e.getMessage());

        }


         return "Lol";


    }


}
