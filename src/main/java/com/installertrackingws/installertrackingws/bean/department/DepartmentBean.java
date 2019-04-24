package com.installertrackingws.installertrackingws.bean.department;

import com.installertrackingws.installertrackingws.bean.user.UserBeen;
import java.util.Date;

public class DepartmentBean {

    public int id;
    public String name;
    public int rank;
    public String ip;
    public int modifiedBy;
    public Date createdDate;
    public Date modifyDate;
    public int oId;
    public UserBeen userBeen;

    public DepartmentBean() {}

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public UserBeen getUserBeen() {
        return userBeen;
    }

    public void setUserBeen(UserBeen userBeen) {
        this.userBeen = userBeen;
    }

    @Override
    public String toString() {
        return "DepartmentBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rk=" + rank +
                ", ip='" + ip + '\'' +
                ", modifiedBy=" + modifiedBy +
                ", createdDate=" + createdDate +
                ", modifyDate=" + modifyDate +
                ", userBeen=" + userBeen +
                '}';
    }
}
