package com.installertrackingws.installertrackingws.bean.menu;

import com.installertrackingws.installertrackingws.model.menu.MenuPermission;

import java.util.Date;
import java.util.List;

public class MenuBn {

    public int id;
    public int oId;
    public String text;
    public String icon;
    public String link;
    public int parentId;
    public int rk;
    public int srl;
    public String ip;
    public int modifiedBy;
    public Date createdDate;
    public Date modifyDate;
    public List<MenuBn> children;
    public List<MenuPermission> menuPermissionBnList;

    public MenuBn() {}

    public List<MenuBn> getChildren() {
        return children;
    }

    public void setChildren(List<MenuBn> children) {
        this.children = children;
    }

    public List<MenuPermission> getMenuPermissionBnList() {
        return menuPermissionBnList;
    }

    public void setMenuPermissionBnList(List<MenuPermission> menuPermissionBnList) {
        this.menuPermissionBnList = menuPermissionBnList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getRk() {
        return rk;
    }

    public void setRk(int rk) {
        this.rk = rk;
    }

    public int getSrl() {
        return srl;
    }

    public void setSrl(int srl) {
        this.srl = srl;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
