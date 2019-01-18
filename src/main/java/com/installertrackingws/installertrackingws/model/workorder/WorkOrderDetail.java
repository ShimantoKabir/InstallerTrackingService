package com.installertrackingws.installertrackingws.model.workorder;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkOrderDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false)
    public int woId;

    @Column(nullable = false)
    public int templateOid;

    @Column(nullable = false)
    public int taskId;

    @Column(nullable = false)
    public int taskSqNo;

    @Column(nullable = true)
    public Date startDate;

    @Column(nullable = true)
    public Date endDate;

    @Column(columnDefinition="int(11) default 0",nullable = false)
    public int photoQuantity;

    @Column(nullable = true)
    public double doneLat;

    @Column(nullable = true)
    public double doneLon;

    @Column(nullable = true)
    public int doneBy;

    @Column(nullable = true)
    public Date doneDate;

    public int statusOid;
    public String remark;

    @Column(nullable = false)
    public String ip;

    @Column(nullable = false)
    public Integer modifiedBy;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    public WorkOrderDetail() {}

    public int getPhotoQuantity() {
        return photoQuantity;
    }

    public void setPhotoQuantity(int photoQuantity) {
        this.photoQuantity = photoQuantity;
    }

    public int getWoId() {
        return woId;
    }

    public void setWoId(int woId) {
        this.woId = woId;
    }

    public int getTaskSqNo() {
        return taskSqNo;
    }

    public void setTaskSqNo(int taskSqNo) {
        this.taskSqNo = taskSqNo;
    }

    public int getStatusOid() {
        return statusOid;
    }

    public void setStatusOid(int statusOid) {
        this.statusOid = statusOid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
