package com.installertrackingws.installertrackingws.bean.material;

import com.installertrackingws.installertrackingws.model.Material.Task;

import java.util.Date;
import java.util.List;

public class TemplateBn {

    public int id;
    public int oId;
    public String name;
    public String ip;
    public int modifiedBy;
    public Date createdDate;
    public List<TaskBn> taskList;

    public TemplateBn() {}

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public List<TaskBn> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskBn> taskList) {
        this.taskList = taskList;
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
}
