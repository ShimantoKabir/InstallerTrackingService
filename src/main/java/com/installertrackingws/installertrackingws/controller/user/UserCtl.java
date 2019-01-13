package com.installertrackingws.installertrackingws.controller.user;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.menu.MenuBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.helper.Token;
import com.installertrackingws.installertrackingws.utility.email.Email;
import com.installertrackingws.installertrackingws.utility.menu.MenuUtl;
import com.installertrackingws.installertrackingws.utility.router.RouterUtl;
import com.installertrackingws.installertrackingws.utility.user.UserUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    public Response manage(@RequestBody UserBn userBn){
        return new UserUtl().manageUser(entityManagerFactory,userBn);
    }

    @PostMapping("/active")
    public Response userActiveAttempt(@RequestBody UserBn userBn) throws ParseException {
        return new UserUtl().activeUser(entityManagerFactory,userBn.getToken());
    }

    @PostMapping("/registration")
    public Response registrationAttempt(HttpServletRequest httpServletRequest, @RequestBody UserBn userBn) {

        Response response = new Response();

        String token = Token.getToken();
        userBn.setToken(token);

        Response registrationResponse = new UserUtl().registrationUser(httpServletRequest,entityManagerFactory,userBn);

        if (registrationResponse.getCode()==200){

            Email email = new Email();
            Response emailResponse = email.sendRegistrationSuccessEmail(javaMailSender,userBn,httpServletRequest.getRemoteAddr());

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
    public Response loginAttempt(@RequestBody UserBn userBn){
        return new UserUtl().checkUserLogin(entityManagerFactory,userBn);
    }

    @PostMapping("/initial-data")
    public Response loginInitialData(@RequestBody UserBn userBn){

        RouterUtl routerUtility = new RouterUtl();
        List routerList = routerUtility.getRouter(entityManagerFactory,userBn);

        MenuUtl menuUtility = new MenuUtl();
        List<MenuBn> menuBnList = menuUtility.getMenuByUserId(entityManagerFactory,userBn);

        if (routerList.size() > 0 && menuBnList.size() > 0){

            Response routerResponse = new Response();
            routerResponse.setMsg("Router List ok");
            routerResponse.setCode(200);
            routerResponse.setList(routerList);

            Response menuResponse = new Response();
            menuResponse.setList(menuBnList);
            menuResponse.setMsg("Menu list ok");
            menuResponse.setCode(200);

            List<Response> loginInitialDataList = new ArrayList();
            loginInitialDataList.add(routerResponse);
            loginInitialDataList.add(menuResponse);

            Response initialResponse = new Response();
            initialResponse.setCode(200);
            initialResponse.setMsg("Initial response ok !");
            initialResponse.setList(loginInitialDataList);

            return initialResponse;

        }else {
            Response initialResponse = new Response();
            initialResponse.setCode(400);
            initialResponse.setMsg("Can not get initial response !");
            return initialResponse;
        }

    }

    @PostMapping("/save/profile")
    public Response saveProfile(@RequestBody UserBn userBn){
        return new UserUtl().saveProfile(entityManagerFactory,userBn);
    }

    @PostMapping("/change-password")
    public Response changePassword(@RequestBody UserBn userBn){
        return new UserUtl().changePassword(entityManagerFactory,userBn);
    }

    @PostMapping("/get-forgot-password-link")
    public Response getForgotPasswordLink(HttpServletRequest httpServletRequest,@RequestBody UserBn userBn){

        Response response = new Response();

        String token = Token.getToken();
        userBn.setToken(token);

        Response registrationResponse = new UserUtl().getForgotPasswordLink(entityManagerFactory,userBn);

        if (registrationResponse.getCode()==200){

            Email email = new Email();
            Response emailResponse = email.sendForgotPasswordLinkMail(javaMailSender,userBn,httpServletRequest.getRemoteAddr());
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
    public Response verifyForgotPasswordToken(HttpServletRequest httpServletRequest,@RequestBody UserBn userBn) throws ParseException {
        return new UserUtl().verifyForgotPasswordToken(entityManagerFactory,userBn);
    }

    @PostMapping("/update-presence")
    public Response updateUserPresence(HttpServletRequest httpServletRequest,@RequestBody UserBn userBn){
        return new UserUtl().updateUserPresence(httpServletRequest,entityManagerFactory,userBn);
    }

    @PostMapping("/change-typing-status")
    public Response changeTypingStatus(@RequestBody UserBn userBn){
        return new UserUtl().changeTypingStatus(entityManagerFactory,userBn);
    }

    @PostMapping("/get-by-department")
    public Response getByDepartment(@RequestBody DepartmentBn departmentBn){
        return new UserUtl().getByDepartment(entityManagerFactory,departmentBn);
    }

}
