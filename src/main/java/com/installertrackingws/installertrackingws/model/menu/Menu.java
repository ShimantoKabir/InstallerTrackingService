package com.installertrackingws.installertrackingws.model.menu;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    public int oId;

    @Column(nullable = false)
    public String text;
    public String icon;
    public String link;

    @Column(nullable = false)
    public int parentId;

    @Column(nullable = false)
    public int rk;

    @Column(nullable = false)
    public int srl;

    public String ip;
    public int modifiedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public Menu() {}

    public Menu(int oId,String text, String icon, String link,int parentId,int rk,int srl, String ip, int modifiedBy, Date createdDate, Date modifyDate) {
        this.oId = oId;
        this.text = text;
        this.icon = icon;
        this.link = link;
        this.parentId = parentId;
        this.rk = rk;
        this.srl = srl;
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

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getRk() {
        return rk;
    }

    public void setRk(int rk) {
        this.rk = rk;
    }

    public int getSrl() {
        return srl;
    }

    public void setSrl(int srl) {
        this.srl = srl;
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
