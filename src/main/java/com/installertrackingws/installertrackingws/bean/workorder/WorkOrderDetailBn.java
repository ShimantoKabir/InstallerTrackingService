package com.installertrackingws.installertrackingws.bean.workorder;

import java.util.Date;
import java.util.List;

public class WorkOrderDetailBn {

    public int id;
    public int woId;
    public int templateOid;
    public int taskId;
    public int taskSqNo;
    public Date startDate;
    public Date endDate;
    public int photoQuantity;
    public double doneLat;
    public double doneLon;
    public int doneBy;
    public Date doneDate;
    public int statusOid;
    public String remark;
    public String ip;
    public Integer modifiedBy;
    public Date createdDate;
    public String woName;
    public String templateName;
    public String taskName;
    public String statusName;
    public String userName;
    public List<WorkOrderDetailBn> taskList;

    public WorkOrderDetailBn() {}

    public List<WorkOrderDetailBn> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<WorkOrderDetailBn> taskList) {
        this.taskList = taskList;
    }

    public int getId() {
        return id;
    }

    public String getWoName() {
        return woName;
    }

    public void setWoName(String woName) {
        this.woName = woName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWoId() {
        return woId;
    }

    public void setWoId(int woId) {
        this.woId = woId;
    }

    public int getTemplateOid() {
        return templateOid;
    }

    public void setTemplateOid(int templateOid) {
        this.templateOid = templateOid;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskSqNo() {
        return taskSqNo;
    }

    public void setTaskSqNo(int taskSqNo) {
        this.taskSqNo = taskSqNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPhotoQuantity() {
        return photoQuantity;
    }

    public void setPhotoQuantity(int photoQuantity) {
        this.photoQuantity = photoQuantity;
    }

    public double getDoneLat() {
        return doneLat;
    }

    public void setDoneLat(double doneLat) {
        this.doneLat = doneLat;
    }

    public double getDoneLon() {
        return doneLon;
    }

    public void setDoneLon(double doneLon) {
        this.doneLon = doneLon;
    }

    public int getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(int doneBy) {
        this.doneBy = doneBy;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public int getStatusOid() {
        return statusOid;
    }

    public void setStatusOid(int statusOid) {
        this.statusOid = statusOid;
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
