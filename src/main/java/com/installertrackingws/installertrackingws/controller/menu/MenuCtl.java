package com.installertrackingws.installertrackingws.controller.menu;

import com.installertrackingws.installertrackingws.bean.menu.MenuBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.utility.menu.MenuUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/get")
    public List<MenuBn> getMenu(){

        MenuUtl menuUtility = new MenuUtl();
        return menuUtility.getAllMenu(entityManagerFactory);

    }

    @PostMapping("/save")
    public Response setMenu(HttpServletRequest httpServletRequest, @RequestBody Response response){

        MenuUtl menuUtility = new MenuUtl();
        return menuUtility.saveMenu(httpServletRequest,entityManagerFactory,response);

    }

    @PostMapping("/get-by-user-id")
    public List getMenuByUserId(@RequestBody UserBn userBn){

        MenuUtl menuUtility = new MenuUtl();
        return menuUtility.getMenuByUserId(entityManagerFactory,userBn);

    }

    @PostMapping("/get-by-department-id")
    public Response getMenuByDepartmentId(@RequestBody UserBn userBn){

        Response response = new Response();

        MenuUtl menuUtility = new MenuUtl();
        List<MenuBn> menuBeanList = menuUtility.getMenuByUserId(entityManagerFactory,userBn);

        if (menuBeanList.size()>0){
            response.setCode(200);
            response.setMsg("Ger department ok");
            response.setList(menuBeanList);
            return response;
        }else {
            response.setCode(400);
            response.setMsg("Ger department ok");
            return response;
        }

    }

    @PostMapping("/set-permission")
    public Response setMenuPermission(HttpServletRequest httpServletRequest, @RequestBody Response response){

        MenuUtl menuUtility = new MenuUtl();
        return menuUtility.setMenuPermission(httpServletRequest,entityManagerFactory,response);

    }

}
