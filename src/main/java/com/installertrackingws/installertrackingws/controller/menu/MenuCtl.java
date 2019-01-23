package com.installertrackingws.installertrackingws.controller.menu;

import com.installertrackingws.installertrackingws.bean.menu.MenuBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
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
    public Response setMenu(HttpServletRequest httpServletRequest, @RequestBody Request request){

        MenuUtl menuUtility = new MenuUtl();
        return menuUtility.saveMenu(httpServletRequest,entityManagerFactory,request);

    }

    @PostMapping("/get-by-department")
    public Response getMenuByDepartment(@RequestBody Request request){

        return new MenuUtl().getMenuByDepartment(entityManagerFactory,request);

    }

    @PostMapping("/set-permission")
    public Response setMenuPermission(HttpServletRequest httpServletRequest, @RequestBody Request request){

        MenuUtl menuUtility = new MenuUtl();
        return menuUtility.setMenuPermission(httpServletRequest,entityManagerFactory,request);

    }

}
