package com.installertrackingws.installertrackingws.controller.social;

import com.installertrackingws.installertrackingws.utility.social.vk.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vk")
public class VkCtl {

    @Autowired
    public VkService vkService;

    @GetMapping("/get-access-token")
    public void getAccessToken(@RequestParam("code") String code){

        System.out.println("VK code = "+code);
        vkService.getAccessToken(code);

    }

}
