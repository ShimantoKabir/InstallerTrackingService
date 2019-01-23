package com.installertrackingws.installertrackingws.bean.network;

import com.installertrackingws.installertrackingws.bean.communication.NotificationBn;
import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.location.LocationBn;
import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.menu.MenuBn;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.bean.workorder.WoAssignBn;
import com.installertrackingws.installertrackingws.bean.workorder.WorkOrderBn;
import com.installertrackingws.installertrackingws.bean.workorder.WorkOrderDetailBn;
import com.installertrackingws.installertrackingws.model.department.Department;
import com.installertrackingws.installertrackingws.model.location.Location;

import java.util.List;

public class Request {

    public WorkOrderDetailBn workOrderDetailBn;
    public List<TaskBn> taskBnList;
    public List<Integer> integerList;
    public UserBn userBn;
    public UserBn manageUserBn;
    public WoAssignBn woAssignBn;
    public DepartmentBn departmentBn;
    public TaskBn taskBn;
    public WorkOrderBn workOrderBn;
    public LocationBn locationBn;
    public List<MenuBn> menuBnList;
    public NotificationBn notificationBn;

    public Request() {}

    public NotificationBn getNotificationBn() {
        return notificationBn;
    }

    public void setNotificationBn(NotificationBn notificationBn) {
        this.notificationBn = notificationBn;
    }

    public List<MenuBn> getMenuBnList() {
        return menuBnList;
    }

    public void setMenuBnList(List<MenuBn> menuBnList) {
        this.menuBnList = menuBnList;
    }

    public WorkOrderBn getWorkOrderBn() {
        return workOrderBn;
    }

    public void setWorkOrderBn(WorkOrderBn workOrderBn) {
        this.workOrderBn = workOrderBn;
    }

    public LocationBn getLocationBn() {
        return locationBn;
    }

    public void setLocationBn(LocationBn locationBn) {
        this.locationBn = locationBn;
    }

    public TaskBn getTaskBn() {
        return taskBn;
    }

    public void setTaskBn(TaskBn taskBn) {
        this.taskBn = taskBn;
    }

    public UserBn getManageUserBn() {
        return manageUserBn;
    }

    public void setManageUserBn(UserBn manageUserBn) {
        this.manageUserBn = manageUserBn;
    }

    public DepartmentBn getDepartmentBn() {
        return departmentBn;
    }

    public void setDepartmentBn(DepartmentBn departmentBn) {
        this.departmentBn = departmentBn;
    }

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
