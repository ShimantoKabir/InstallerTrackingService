package com.installertrackingws.installertrackingws.bean.department;

import com.installertrackingws.installertrackingws.bean.user.UserBn;

import java.util.Date;

public class DepartmentBn {

    public int id;
    public String name;
    public int rk;
    public String ip;
    public int modifiedBy;
    public Date createdDate;
    public Date modifyDate;
    public int oId;
    public UserBn userBn;

    public DepartmentBn() {}

    public UserBn getUserBn() {
        return userBn;
    }

    public void setUserBn(UserBn userBn) {
        this.userBn = userBn;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRk() {
        return rk;
    }

    public void setRk(int rk) {
        this.rk = rk;
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

    @Override
    public String toString() {
        return "DepartmentBn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rk=" + rk +
                ", ip='" + ip + '\'' +
                ", modifiedBy=" + modifiedBy +
                ", createdDate=" + createdDate +
                ", modifyDate=" + modifyDate +
                ", oId=" + oId +
                ", userBn=" + userBn +
                '}';
    }
}
