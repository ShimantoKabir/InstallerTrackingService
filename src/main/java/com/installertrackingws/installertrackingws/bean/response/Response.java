package com.installertrackingws.installertrackingws.bean.response;

import com.installertrackingws.installertrackingws.bean.material.SiteBn;
import com.installertrackingws.installertrackingws.bean.menu.MenuBean;
import com.installertrackingws.installertrackingws.bean.user.UserBeen;
import com.installertrackingws.installertrackingws.model.Material.Site;

import java.util.List;

public class Response {

    public int code;
    public String msg;
    public List<?> list;
    public Object object;
    public UserBeen userBeen;
    public List<MenuBean> menuBeanList;
    public List<Integer> intList;
    public int conversationId;
    public List<?> siteList;
    public List<?> userList;
    public List<?> workOrderList;

    public Response() {}

    public List<?> getWorkOrderList() {
        return workOrderList;
    }

    public void setWorkOrderList(List<?> workOrderList) {
        this.workOrderList = workOrderList;
    }

    public List<?> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<?> siteList) {
        this.siteList = siteList;
    }

    public List<?> getUserList() {
        return userList;
    }

    public void setUserList(List<?> userList) {
        this.userList = userList;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public List<Integer> getIntList() {
        return intList;
    }

    public void setIntList(List<Integer> intList) {
        this.intList = intList;
    }

    public List<MenuBean> getMenuBeanList() {
        return menuBeanList;
    }

    public void setMenuBeanList(List<MenuBean> menuBeanList) {
        this.menuBeanList = menuBeanList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public UserBeen getUserBeen() {
        return userBeen;
    }

    public void setUserBeen(UserBeen userBeen) {
        this.userBeen = userBeen;
    }
}
