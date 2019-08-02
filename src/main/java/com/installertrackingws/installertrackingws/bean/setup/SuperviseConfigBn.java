package com.installertrackingws.installertrackingws.bean.setup;

import java.util.Date;

public class SuperviseConfigBn {

    public int id;
    public int supervisor;
    public String supervisorName;
    public String supervisorEmail;
    public int installer;
    public String installerName;
    public String installerEmail;
    public String ip;
    public int modifiedBy;
    public Date createdDate;

    public SuperviseConfigBn() {}

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public String getInstallerEmail() {
        return installerEmail;
    }

    public void setInstallerEmail(String installerEmail) {
        this.installerEmail = installerEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public int getInstaller() {
        return installer;
    }

    public void setInstaller(int installer) {
        this.installer = installer;
    }

    public String getInstallerName() {
        return installerName;
    }

    public void setInstallerName(String installerName) {
        this.installerName = installerName;
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
