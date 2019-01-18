package com.installertrackingws.installertrackingws.model.workorder;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WoAssign {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false)
    public int oId;

    @Column(nullable = false)
    public int woId;

    @Column(nullable = false)
    public int deptOid;

    @Column(nullable = false)
    public int assignTo;

    public Date assignDate;
    public String assignTime;

    @Column(nullable = false)
    public int statusOid;

    public String scope;
    public String remark;

    @Column(nullable = false)
    public String ip;

    @Column(nullable = false)
    public Integer modifiedBy;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    public WoAssign() {}

    public int getDeptOid() {
        return deptOid;
    }

    public void setDeptOid(int deptOid) {
        this.deptOid = deptOid;
    }

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

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
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
