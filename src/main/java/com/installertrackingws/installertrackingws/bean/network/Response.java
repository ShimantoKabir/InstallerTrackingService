package com.installertrackingws.installertrackingws.bean.network;

import com.installertrackingws.installertrackingws.bean.menu.MenuBn;
import com.installertrackingws.installertrackingws.bean.user.UserBn;

import java.util.List;

public class Response {

    public String msg;
    public int code;
    public Object object;
    public Object taskResponse;
    public Object userResponse;
    public List<?> list;
    public List<MenuBn> menuBnList;
    public List<Integer> integerList;
    public List<?> templateList;
    public List<?> workOrderList;
    public List<?> workOrderDetailList;
    public List<?> userList;
    public List<?> siteList;
    public List<?> statusList;
    public List<?> departmentBnList;
    public List<?> woAssignBnList;
    public List<?> cbdList;
    public List<?> taskList;
    public UserBn userBn;
    public int conversationId;

    public Response() {}

    public Object getTaskResponse() {
        return taskResponse;
    }

    public void setTaskResponse(Object taskResponse) {
        this.taskResponse = taskResponse;
    }

    public Object getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(Object userResponse) {
        this.userResponse = userResponse;
    }

    public List<?> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<?> taskList) {
        this.taskList = taskList;
    }

    public List<?> getWoAssignBnList() {
        return woAssignBnList;
    }

    public void setWoAssignBnList(List<?> woAssignBnList) {
        this.woAssignBnList = woAssignBnList;
    }

    public List<?> getDepartmentBnList() {
        return departmentBnList;
    }

    public void setDepartmentBnList(List<?> departmentBnList) {
        this.departmentBnList = departmentBnList;
    }

    public List<?> getCbdList() {
        return cbdList;
    }

    public void setCbdList(List<?> cbdList) {
        this.cbdList = cbdList;
    }

    public List<?> getUserList() {
        return userList;
    }

    public void setUserList(List<?> userList) {
        this.userList = userList;
    }

    public List<?> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<?> siteList) {
        this.siteList = siteList;
    }

    public List<?> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<?> statusList) {
        this.statusList = statusList;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public List<?> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<?> templateList) {
        this.templateList = templateList;
    }

    public List<?> getWorkOrderList() {
        return workOrderList;
    }

    public void setWorkOrderList(List<?> workOrderList) {
        this.workOrderList = workOrderList;
    }

    public List<?> getWorkOrderDetailList() {
        return workOrderDetailList;
    }

    public void setWorkOrderDetailList(List<?> workOrderDetailList) {
        this.workOrderDetailList = workOrderDetailList;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public List<MenuBn> getMenuBnList() {
        return menuBnList;
    }

    public void setMenuBnList(List<MenuBn> menuBnList) {
        this.menuBnList = menuBnList;
    }

    public String getMsg() {
        return msg;
    }

    public UserBn getUserBn() {
        return userBn;
    }

    public void setUserBn(UserBn userBn) {
        this.userBn = userBn;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
