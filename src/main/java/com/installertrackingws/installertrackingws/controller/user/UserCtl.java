package com.installertrackingws.installertrackingws.controller.user;

import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.helper.Token;
import com.installertrackingws.installertrackingws.utility.email.Email;
import com.installertrackingws.installertrackingws.utility.user.UserUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("/user")
public class UserCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/get")
    public Response get(){
        return new UserUtl().getAllUser(entityManagerFactory);
    }


    @PostMapping("/manage")
    public Response manage(@RequestBody Request request){
        return new UserUtl().manageUser(entityManagerFactory,request);
    }

    @PostMapping("/manage-init")
    public Response getManageInitData(@RequestBody Request request){
        return new UserUtl().getManageInitData(entityManagerFactory,request);
    }

    @PostMapping("/active")
    public Response userActiveAttempt(@RequestBody Request request) throws ParseException {
        return new UserUtl().activeUser(entityManagerFactory,request);
    }

    @PostMapping("/registration")
    public Response registrationAttempt(HttpServletRequest httpServletRequest, @RequestBody Request request) {

        Response response = new Response();

        String token = Token.getToken();
        request.getUserBn().setToken(token);

        Response registrationResponse = new UserUtl().registrationUser(httpServletRequest,entityManagerFactory,request.getUserBn());

        if (registrationResponse.getCode()==200){

            Email email = new Email();
            Response emailResponse = email.sendRegistrationSuccessEmail(javaMailSender,request.getUserBn(),httpServletRequest.getRemoteAddr());

            if (emailResponse.getCode()==200){
                response.setCode(200);
                response.setMsg("A link has been sent to your email address, please check your mail !");
            }else {
                response.setCode(400);
                response.setMsg("No link sent to your email address to complete the registration !");
            }

        }else {
            response.setCode(400);
            response.setMsg("Registration unsuccessful !");
        }

        return response;

    }

    @PostMapping("/login")
    public Response loginAttempt(@RequestBody Request request){

        return new UserUtl().checkUserLogin(entityManagerFactory,request);

    }

    @PostMapping("/save/profile")
    public Response saveProfile(@RequestBody Request request){
        return new UserUtl().saveProfile(entityManagerFactory,request);
    }

    @PostMapping("/change-password")
    public Response changePassword(@RequestBody Request request){
        return new UserUtl().changePassword(entityManagerFactory,request);
    }

    @PostMapping("/get-forgot-password-link")
    public Response getForgotPasswordLink(HttpServletRequest httpServletRequest,@RequestBody Request request){

        Response response = new Response();

        String token = Token.getToken();
        request.getUserBn().setToken(token);

        Response registrationResponse = new UserUtl().getForgotPasswordLink(entityManagerFactory,request);

        if (registrationResponse.getCode()==200){

            Email email = new Email();
            Response emailResponse = email.sendForgotPasswordLinkMail(javaMailSender,request,httpServletRequest.getRemoteAddr());
            if (emailResponse.getCode()==200){

                response.setCode(200);
                response.setMsg("A link has been sent to your email address, please check your mail !");

            }else {

                response.setCode(400);
                response.setMsg("No link sent to your email address for change password !");

            }

        }else {

            response.setCode(400);
            response.setMsg("No link sent to your email address for change password !");

        }

        return response;

    }

    @PostMapping("/verify-forgot-password-token")
    public Response verifyForgotPasswordToken(@RequestBody Request request) throws ParseException {
        return new UserUtl().verifyForgotPasswordToken(entityManagerFactory,request);
    }

    @PostMapping("/update-presence")
    public Response updateUserPresence(@RequestBody Request request){
        return new UserUtl().updateUserPresence(entityManagerFactory,request);
    }

    @PostMapping("/change-typing-status")
    public Response changeTypingStatus(@RequestBody Request request){
        return new UserUtl().changeTypingStatus(entityManagerFactory,request);
    }

    @PostMapping("/get-by-department")
    public Response getByDepartment(@RequestBody Request request){
        return new UserUtl().getByDepartment(entityManagerFactory,request);
    }

}
