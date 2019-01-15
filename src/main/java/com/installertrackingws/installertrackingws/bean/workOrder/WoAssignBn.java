package com.installertrackingws.installertrackingws.bean.workOrder;

import java.util.Date;

public class WoAssignBn {

    public int id;
    public int oId;
    public int woId;
    public int assignTo;
    public String assignTime;
    public int statusOid;
    public String scope;
    public String remark;
    public String ip;
    public Integer modifiedBy;
    public Date createdDate;

    public WoAssignBn() {}

    public int getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(int assignTo) {
        this.assignTo = assignTo;
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

    public int getWoId() {
        return woId;
    }

    public void setWoId(int woId) {
        this.woId = woId;
    }

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public int getStatusOid() {
        return statusOid;
    }

    public void setStatusOid(int statusOid) {
        this.statusOid = statusOid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
