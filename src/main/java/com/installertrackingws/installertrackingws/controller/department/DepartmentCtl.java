package com.installertrackingws.installertrackingws.controller.department;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.model.department.Department;
import com.installertrackingws.installertrackingws.utility.department.DepartmentUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody Request request){

         return new DepartmentUtl().save(entityManagerFactory,httpServletRequest,request);

    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest, @RequestBody Request request){

        return new DepartmentUtl().update(entityManagerFactory,httpServletRequest,request);

    }

    @GetMapping("/get")
    public Response get(){

        DepartmentUtl departmentUtility = new DepartmentUtl();
        List<DepartmentBn> departmentBeans = departmentUtility.getDepartmentList(entityManagerFactory);

        Response response = new Response();

        if (departmentBeans.size()>0){
            response.setMsg("Get department successfully !");
            response.setCode(200);
            response.setList(departmentBeans);
            return response;
        }else {
            response.setMsg("Did not get any department !");
            response.setCode(200);
            return response;
        }

    }

    @PostMapping("/get-by-user")
    public Response save(@RequestBody Request request){

        return  new DepartmentUtl().getDepartmentByUser(entityManagerFactory,request);

    }

}
