package com.installertrackingws.installertrackingws.controller.social;

import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.social.facebook.FaceBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/fb")
public class FaceBookCtl {

    @Autowired
    private FaceBookService faceBookService;

    @GetMapping("/login")
    public Response login(){

        Response response = new Response();
        String url = faceBookService.login();

        if (url.isEmpty()){

            response.setMsg("Not url found for facebook login");
            response.setCode(400);

        }else {

            response.setMsg("Found facebook login url");
            response.setCode(200);
            response.setFacebookLoginUrl(url);
        }

        return response;

    }

    @GetMapping("/get-access-token")
    public RedirectView getAccessToken(@RequestParam("code") String code){

        RedirectView redirectView = new RedirectView();
        String accessToken = faceBookService.getAccessToken(code);
        String url = "http://localhost:3307/fb/get-data/"+accessToken;
        System.out.println("AccessToken = "+url);
        redirectView.setUrl(url);
        return redirectView;

    }

    @GetMapping("/get-data/{accessToken}")
    public User getData(@PathVariable String accessToken){

        User user = faceBookService.getData(accessToken);
        System.out.println("FirstName = "+user.getFirstName());

        return user;

    }

}
