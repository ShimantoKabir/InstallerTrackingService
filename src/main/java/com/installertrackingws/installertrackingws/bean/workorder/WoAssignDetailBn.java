package com.installertrackingws.installertrackingws.bean.workorder;

import java.util.Date;

public class WoAssignDetailBn {

    public int id;
    public int woAssignOid;
    public String breakDown;
    public String cost;
    public String name;
    public int cbdId;
    public String ip;
    public Integer modifiedBy;
    public Date createdDate;

    public WoAssignDetailBn() {}

    public int getCbdId() {
        return cbdId;
    }

    public void setCbdId(int cbdId) {
        this.cbdId = cbdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
