package com.installertrackingws.installertrackingws.controller.treasure;

import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.treasure.CostBreakDownBn;
import com.installertrackingws.installertrackingws.model.treasure.CostBreakDown;
import com.installertrackingws.installertrackingws.utility.material.TaskUtl;
import com.installertrackingws.installertrackingws.utility.treasure.CostBreakDownBnUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cost-break-down")
public class CostBreakDownBnCtl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/save")
    public Response save(HttpServletRequest httpServletRequest, @RequestBody CostBreakDownBn costBreakDownBn){

        return CostBreakDownBnUtl.save(httpServletRequest,entityManagerFactory,costBreakDownBn);

    }

}
