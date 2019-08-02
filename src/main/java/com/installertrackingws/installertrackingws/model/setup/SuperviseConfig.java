package com.installertrackingws.installertrackingws.model.setup;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SuperviseConfig {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    public int supervisor;
    public int installer;
    public int modifiedBy;
    public String ip;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    public SuperviseConfig() {}

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

    public int getInstaller() {
        return installer;
    }

    public void setInstaller(int installer) {
        this.installer = installer;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
