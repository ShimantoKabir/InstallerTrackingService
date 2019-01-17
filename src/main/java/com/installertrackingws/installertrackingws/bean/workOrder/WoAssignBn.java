package com.installertrackingws.installertrackingws.bean.workOrder;

import com.installertrackingws.installertrackingws.model.WorkOrder.WoAssignDetail;

import java.util.Date;
import java.util.List;

public class WoAssignBn {

    public int id;
    public int oId;
    public int woId;
    public int deptOid;
    public int assignTo;
    public Date assignDate;
    public String assignTime;
    public int statusOid;
    public String scope;
    public String remark;
    public String ip;
    public Integer modifiedBy;
    public Date createdDate;
    public List<WoAssignDetailBn> woAssignDetailBnList;
    public List<WoAssignDetail> woAssignDetailList;
    public String assignUserName;
    public String workOrderName;
    public String statusName;
    public String deptName;

    public WoAssignBn() {}

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptOid() {
        return deptOid;
    }

    public void setDeptOid(int deptOid) {
        this.deptOid = deptOid;
    }

    public List<WoAssignDetailBn> getWoAssignDetailBnList() {
        return woAssignDetailBnList;
    }

    public void setWoAssignDetailBnList(List<WoAssignDetailBn> woAssignDetailBnList) {
        this.woAssignDetailBnList = woAssignDetailBnList;
    }

    public List<WoAssignDetail> getWoAssignDetailList() {
        return woAssignDetailList;
    }

    public void setWoAssignDetailList(List<WoAssignDetail> woAssignDetailList) {
        this.woAssignDetailList = woAssignDetailList;
    }

    public String getAssignUserName() {
        return assignUserName;
    }

    public void setAssignUserName(String assignUserName) {
        this.assignUserName = assignUserName;
    }

    public String getWorkOrderName() {
        return workOrderName;
    }

    public void setWorkOrderName(String workOrderName) {
        this.workOrderName = workOrderName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
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
