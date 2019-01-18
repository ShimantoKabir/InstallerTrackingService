package com.installertrackingws.installertrackingws.model.workorder;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WoAssignDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false)
    public int woAssignOid;

    @Column(nullable = false)
    public String breakDown;

    @Column(nullable = false)
    public String cost;

    @Column(nullable = false)
    public String ip;

    @Column(nullable = false)
    public Integer modifiedBy;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    public WoAssignDetail() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWoAssignOid() {
        return woAssignOid;
    }

    public void setWoAssignOid(int woAssignOid) {
        this.woAssignOid = woAssignOid;
    }

    public String getBreakDown() {
        return breakDown;
    }

    public void setBreakDown(String breakDown) {
        this.breakDown = breakDown;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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
