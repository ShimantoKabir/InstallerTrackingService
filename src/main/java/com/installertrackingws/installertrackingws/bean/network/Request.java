package com.installertrackingws.installertrackingws.bean.network;

import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.bean.workOrder.WoAssignBn;
import com.installertrackingws.installertrackingws.bean.workOrder.WorkOrderDetailBn;
import java.util.List;

public class Request {

    public WorkOrderDetailBn workOrderDetailBn;
    public List<TaskBn> taskBnList;
    public List<Integer> integerList;
    public UserBn userBn;
    public WoAssignBn woAssignBn;

    public Request() {}

    public WoAssignBn getWoAssignBn() {
        return woAssignBn;
    }

    public void setWoAssignBn(WoAssignBn woAssignBn) {
        this.woAssignBn = woAssignBn;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public UserBn getUserBn() {
        return userBn;
    }

    public void setUserBn(UserBn userBn) {
        this.userBn = userBn;
    }

    public List<TaskBn> getTaskBnList() {
        return taskBnList;
    }

    public void setTaskBnList(List<TaskBn> taskBnList) {
        this.taskBnList = taskBnList;
    }

    public WorkOrderDetailBn getWorkOrderDetailBn() {
        return workOrderDetailBn;
    }

    public void setWorkOrderDetailBn(WorkOrderDetailBn workOrderDetailBn) {
        this.workOrderDetailBn = workOrderDetailBn;
    }
}
