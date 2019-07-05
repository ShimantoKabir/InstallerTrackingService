package com.installertrackingws.installertrackingws.controller.workorder;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.email.Email;
import com.installertrackingws.installertrackingws.utility.workorder.WoAssignUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wo-assign")
public class WoAssignCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/get-init-data")
    public Response getInitData(){
        return WoAssignUtl.getInitData(entityManagerFactory);
    }

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody Request request){

        Response response = new Response();

        Response saveRes = WoAssignUtl.save(httpServletRequest,entityManagerFactory,request);

        if (saveRes.getCode()==200){

            Email email = new Email();
            Response emailResponse = email.sendWoAssignMailToUser(javaMailSender,request);

            if (emailResponse.getCode()==200){

                response.setNotificationBn(saveRes.getNotificationBn());
                response.setCode(200);
                response.setMsg("A mail has been sent to "+request.getWoAssignBn().getAssignUserMail()+" !");

            }else {

                response.setCode(400);
                response.setMsg("No mail has been sent to "+request.getWoAssignBn().getAssignUserMail()+" !");

            }

        }else {

            response.setCode(400);
            response.setMsg("Mail send unsuccessful !");

        }

        return response;

    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody Request request){
        return WoAssignUtl.update(httpServletRequest,entityManagerFactory,request);
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody Request request){
        return WoAssignUtl.delete(entityManagerFactory,request);
    }

}
