package com.installertrackingws.installertrackingws.model.user;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    @Column(nullable = false)
    public String password;
    @Column(nullable = false)
    public String userEmail;
    public String userName;
    public String sessionId;
    public Integer isUserActive;
    public Integer isUserApproved;
    public Integer isOnline;
    public Integer deptId;
    public String token;
    public String ip;
    public Integer modifiedBy;
    public Integer isTyping;
    public Integer forWho;
    public String address;
    public double lat;
    public double lon;

    public Integer getIsTyping() {
        return isTyping;
    }

    public void setIsTyping(Integer isTyping) {
        this.isTyping = isTyping;
    }

    public Integer getForWho() {
        return forWho;
    }

    public void setForWho(Integer forWho) {
        this.forWho = forWho;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPresenceDate;

    public User() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(Integer isUserActive) {
        this.isUserActive = isUserActive;
    }

    public Integer getIsUserApproved() {
        return isUserApproved;
    }

    public void setIsUserApproved(Integer isUserApproved) {
        this.isUserApproved = isUserApproved;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getLastPresenceDate() {
        return lastPresenceDate;
    }

    public void setLastPresenceDate(Date lastPresenceDate) {
        this.lastPresenceDate = lastPresenceDate;
    }
}
