package com.installertrackingws.installertrackingws.bean.workorder;

import java.util.Date;

public class WorkOrderBn {

    public int id;
    public String name;
    public int siteId;
    public String sitePic;
    public int personInCharge;
    public int requester;
    public Date deadLine;
    public int statusId;
    public String remark;
    public String ip;
    public Integer modifiedBy;
    public Date createdDate;
    public String siteName;
    public String personInChargeName;
    public String requesterName;
    public int woPiCh;
    public String sitePiCt;
    public int statusOid;
    public String woPiChName;
    public String status;

    public WorkOrderBn() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWoPiCh() {
        return woPiCh;
    }

    public String getWoPiChName() {
        return woPiChName;
    }

    public void setWoPiChName(String woPiChName) {
        this.woPiChName = woPiChName;
    }

    public void setWoPiCh(int woPiCh) {
        this.woPiCh = woPiCh;
    }

    public String getSitePiCt() {
        return sitePiCt;
    }

    public void setSitePiCt(String sitePiCt) {
        this.sitePiCt = sitePiCt;
    }

    public int getStatusOid() {
        return statusOid;
    }

    public void setStatusOid(int statusOid) {
        this.statusOid = statusOid;
    }

    public String getSitePic() {
        return sitePic;
    }

    public void setSitePic(String sitePic) {
        this.sitePic = sitePic;
    }

    public int getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(int personInCharge) {
        this.personInCharge = personInCharge;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getPersonInChargeName() {
        return personInChargeName;
    }

    public void setPersonInChargeName(String personInChargeName) {
        this.personInChargeName = personInChargeName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
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

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getRequester() {
        return requester;
    }

    public void setRequester(int requester) {
        this.requester = requester;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
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
