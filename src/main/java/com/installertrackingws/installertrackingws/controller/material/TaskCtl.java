package com.installertrackingws.installertrackingws.controller.material;

import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.utility.material.TaskUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest,@RequestBody TaskBn taskBn){

        return new TaskUtl().save(httpServletRequest,entityManagerFactory,taskBn);

    }

    @GetMapping("/get")
    public Response getAllTask(){

        return new TaskUtl().getAllTask(entityManagerFactory);

    }

    @PostMapping("/update")
    public Response update(HttpServletRequest httpServletRequest,@RequestBody TaskBn taskBn){

        return new TaskUtl().update(httpServletRequest,entityManagerFactory,taskBn);

    }


}
