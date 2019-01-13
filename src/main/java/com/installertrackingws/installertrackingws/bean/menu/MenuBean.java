package com.installertrackingws.installertrackingws.bean.menu;

import com.installertrackingws.installertrackingws.model.menu.MenuPermission;

import java.util.List;

public class MenuBean{

    public int id;
    public int oId;
    public String text;
    public String icon;
    public String link;
    public int parentId;
    public int rank;
    public int srl;
    public List<MenuBean> children;
    public List<MenuPermission> menuPermissionList;

    public MenuBean() {}

    public MenuBean(int id, int oId, String text, String icon, String link, int parentId, int rank, int srl, List<MenuBean> children, List<MenuPermission> menuPermissionList) {
        this.id = id;
        this.oId = oId;
        this.text = text;
        this.icon = icon;
        this.link = link;
        this.parentId = parentId;
        this.rank = rank;
        this.srl = srl;
        this.children = children;
        this.menuPermissionList = menuPermissionList;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getSrl() {
        return srl;
    }

    public void setSrl(int srl) {
        this.srl = srl;
    }

    public List<MenuBean> getChildren() {
        return children;
    }

    public void setChildren(List<MenuBean> children) {
        this.children = children;
    }

    public List<MenuPermission> getMenuPermissionList() {
        return menuPermissionList;
    }

    public void setMenuPermissionList(List<MenuPermission> menuPermissionList) {
        this.menuPermissionList = menuPermissionList;
    }
}
