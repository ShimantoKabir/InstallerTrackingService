package com.installertrackingws.installertrackingws.model.material;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TemplateDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false)
    public int templateOid;

    @Column(nullable = false)
    public double taskOid;

    @Column(nullable = false)
    public int sequenceNumber;

    public String ip;
    public int modifiedBy;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    public TemplateDetail() {}

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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

    public double getTaskOid() {
        return taskOid;
    }

    public void setTaskOid(double taskOid) {
        this.taskOid = taskOid;
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
