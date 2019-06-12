package com.installertrackingws.installertrackingws.controller.social;

import com.installertrackingws.installertrackingws.utility.social.FaceBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/fb")
public class FaceBookCtl {

    @Autowired private FaceBookService faceBookService;

    @GetMapping("/facebooklogin")
    public RedirectView facebookLogin(){

        RedirectView redirectView = new RedirectView();
        String url = faceBookService.facebookLogin();
        System.out.print(url);
        redirectView.setUrl(url);
        return redirectView;

    }

    @GetMapping("/facebook")
    public String facebook(@RequestParam("code") String code){

        String accessToken = faceBookService.getFacebookAccessToken(code);
        return "redirect:/facebookprofiledata"+accessToken;

    }

    @GetMapping("/facebookprofiledata/{accessToken:.+}")
    public String getFaceBookProfileData(@PathVariable String accessToken, Model model){

        User user = faceBookService.getFacebookUserProfile(accessToken);
        System.out.print(user.getFirstName());

        return "view/userProfile";

    }

}
