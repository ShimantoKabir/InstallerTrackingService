package com.installertrackingws.installertrackingws.bean.user;

import java.util.Date;

public class UserBeen {

    public int id;
    public String userName;
    public String userEmail;
    public String password;
    public Integer isUserActive;
    public Integer isUserApproved;
    public Integer isOnline;
    public Integer deptId;
    public String token;
    public String ip;
    public Integer modifiedBy;
    public String createdDate;
    public String deptName;
    public String newPassword;
    private Date lastPresenceDate;
    public Integer isTyping;
    public Integer forWho;

    public UserBeen() {}

    public Date getLastPresenceDate() {
        return lastPresenceDate;
    }

    public void setLastPresenceDate(Date lastPresenceDate) {
        this.lastPresenceDate = lastPresenceDate;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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
}
