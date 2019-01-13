package com.installertrackingws.installertrackingws.model.menu;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class MenuPermission {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    @NotNull
    public int deptId;

    @NotNull
    public int menuOid;

    public String ip;
    public int modifiedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date modifyDate;

    public MenuPermission() {}

    public MenuPermission(@NotNull int deptId, @NotNull int menuOid, String ip, int modifiedBy, Date createdDate, Date modifyDate) {
        this.deptId = deptId;
        this.menuOid = menuOid;
        this.ip = ip;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;
        this.modifyDate = modifyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getMenuOid() {
        return menuOid;
    }

    public void setMenuOid(int menuOid) {
        this.menuOid = menuOid;
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

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
