package com.installertrackingws.installertrackingws.bean.network;

import com.installertrackingws.installertrackingws.bean.accounts.CostBreakDownBn;
import com.installertrackingws.installertrackingws.bean.communication.FriendRequestBn;
import com.installertrackingws.installertrackingws.bean.communication.NotificationBn;
import com.installertrackingws.installertrackingws.bean.location.LocationBn;
import com.installertrackingws.installertrackingws.bean.material.SiteBn;
import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.material.TemplateBn;
import com.installertrackingws.installertrackingws.bean.menu.MenuBn;
import com.installertrackingws.installertrackingws.bean.setup.SuperviseConfigBn;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.bean.workorder.WorkOrderBn;
import com.installertrackingws.installertrackingws.bean.workorder.WorkOrderDetailBn;
import com.installertrackingws.installertrackingws.model.Etc.Status;
import com.installertrackingws.installertrackingws.model.accounts.CostBreakDown;
import com.installertrackingws.installertrackingws.model.communication.Notification;
import com.installertrackingws.installertrackingws.model.department.Department;
import com.installertrackingws.installertrackingws.model.location.Location;
import com.installertrackingws.installertrackingws.model.material.Site;
import com.installertrackingws.installertrackingws.model.material.Task;
import com.installertrackingws.installertrackingws.model.material.Template;
import com.installertrackingws.installertrackingws.model.material.TemplateDetail;
import com.installertrackingws.installertrackingws.model.setup.CompanyInfo;
import com.installertrackingws.installertrackingws.model.setup.SuperviseConfig;
import com.installertrackingws.installertrackingws.model.user.User;
import com.installertrackingws.installertrackingws.model.workorder.WoAssign;
import com.installertrackingws.installertrackingws.model.workorder.WoAssignDetail;
import com.installertrackingws.installertrackingws.model.workorder.WorkOrder;
import com.installertrackingws.installertrackingws.model.workorder.WorkOrderDetail;

import java.util.List;

public class Response {

    public String msg;
    public int code;
    public Object object;
    public Object taskResponse;
    public Object templateResponse;
    public Object userResponse;
    public UserBn userBn;
    public Integer conversationId;
    public MenuBn menuBn;
    public NotificationBn notificationBn;
    public FriendRequestBn friendRequestBn;
    public String facebookLoginUrl;
    public CompanyInfo companyInfo;
    public LocationBn locationBn;

    public List<?> list;
    public List<?> departmentBnList;
    public List<?> woAssignBnList;
    public List<?> cbdList;
    public List<Task> taskList;
    public List<TaskBn> taskBnList;
    public List<MenuBn> menuBnList;
    public List<Integer> integerList;
    public List<Notification> notificationBnList;
    public List<UserBn> userBnList;
    public List<Site> siteList;
    public List<CostBreakDown> costBreakDownList;
    public List<CostBreakDownBn> costBreakDownBnList;
    public List<Department> departmentList;
    public List<Status> statusList;
    public List<Template> templateList;
    public List<TemplateDetail> templateDetailList;
    public List<User> userList;
    public List<WoAssign> woAssignList;
    public List<WoAssignDetail> woAssignDetailList;
    public List<WorkOrder> workOrderList;
    public List<WorkOrderBn> workOrderBnList;
    public List<WorkOrderDetail> workOrderDetailList;
    public List<TemplateBn> templateBnList;
    public List<WorkOrderDetailBn> workOrderDetailBnList;
    public List<SuperviseConfigBn> superviseConfigBnList;
    public List<SuperviseConfig> superviseConfigList;

    public Response() {}

    public LocationBn getLocationBn() {
        return locationBn;
    }

    public void setLocationBn(LocationBn locationBn) {
        this.locationBn = locationBn;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public List<SuperviseConfig> getSuperviseConfigList() {
        return superviseConfigList;
    }

    public void setSuperviseConfigList(List<SuperviseConfig> superviseConfigList) {
        this.superviseConfigList = superviseConfigList;
    }

    public List<SuperviseConfigBn> getSuperviseConfigBnList() {
        return superviseConfigBnList;
    }

    public void setSuperviseConfigBnList(List<SuperviseConfigBn> superviseConfigBnList) {
        this.superviseConfigBnList = superviseConfigBnList;
    }

    public List<CostBreakDownBn> getCostBreakDownBnList() {
        return costBreakDownBnList;
    }

    public void setCostBreakDownBnList(List<CostBreakDownBn> costBreakDownBnList) {
        this.costBreakDownBnList = costBreakDownBnList;
    }

    public List<TaskBn> getTaskBnList() {
        return taskBnList;
    }

    public void setTaskBnList(List<TaskBn> taskBnList) {
        this.taskBnList = taskBnList;
    }

    public List<WorkOrderDetailBn> getWorkOrderDetailBnList() {
        return workOrderDetailBnList;
    }

    public void setWorkOrderDetailBnList(List<WorkOrderDetailBn> workOrderDetailBnList) {
        this.workOrderDetailBnList = workOrderDetailBnList;
    }

    public List<TemplateBn> getTemplateBnList() {
        return templateBnList;
    }

    public void setTemplateBnList(List<TemplateBn> templateBnList) {
        this.templateBnList = templateBnList;
    }

    public List<WorkOrderDetail> getWorkOrderDetailList() {
        return workOrderDetailList;
    }

    public void setWorkOrderDetailList(List<WorkOrderDetail> workOrderDetailList) {
        this.workOrderDetailList = workOrderDetailList;
    }

    public List<WorkOrderBn> getWorkOrderBnList() {
        return workOrderBnList;
    }

    public void setWorkOrderBnList(List<WorkOrderBn> workOrderBnList) {
        this.workOrderBnList = workOrderBnList;
    }

    public List<WorkOrder> getWorkOrderList() {
        return workOrderList;
    }

    public void setWorkOrderList(List<WorkOrder> workOrderList) {
        this.workOrderList = workOrderList;
    }

    public List<WoAssignDetail> getWoAssignDetailList() {
        return woAssignDetailList;
    }

    public void setWoAssignDetailList(List<WoAssignDetail> woAssignDetailList) {
        this.woAssignDetailList = woAssignDetailList;
    }

    public List<WoAssign> getWoAssignList() {
        return woAssignList;
    }

    public void setWoAssignList(List<WoAssign> woAssignList) {
        this.woAssignList = woAssignList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<TemplateDetail> getTemplateDetailList() {
        return templateDetailList;
    }

    public void setTemplateDetailList(List<TemplateDetail> templateDetailList) {
        this.templateDetailList = templateDetailList;
    }

    public List<Template> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<Template> templateList) {
        this.templateList = templateList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<CostBreakDown> getCostBreakDownList() {
        return costBreakDownList;
    }

    public void setCostBreakDownList(List<CostBreakDown> costBreakDownList) {
        this.costBreakDownList = costBreakDownList;
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }

    public String getFacebookLoginUrl() {
        return facebookLoginUrl;
    }

    public void setFacebookLoginUrl(String facebookLoginUrl) {
        this.facebookLoginUrl = facebookLoginUrl;
    }

    public FriendRequestBn getFriendRequestBn() {
        return friendRequestBn;
    }

    public void setFriendRequestBn(FriendRequestBn friendRequestBn) {
        this.friendRequestBn = friendRequestBn;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public List<UserBn> getUserBnList() {
        return userBnList;
    }

    public void setUserBnList(List<UserBn> userBnList) {
        this.userBnList = userBnList;
    }

    public NotificationBn getNotificationBn() {
        return notificationBn;
    }

    public void setNotificationBn(NotificationBn notificationBn) {
        this.notificationBn = notificationBn;
    }

    public List<Notification> getNotificationBnList() {
        return notificationBnList;
    }

    public void setNotificationBnList(List<Notification> notificationBnList) {
        this.notificationBnList = notificationBnList;
    }

    public MenuBn getMenuBn() {
        return menuBn;
    }

    public void setMenuBn(MenuBn menuBn) {
        this.menuBn = menuBn;
    }

    public Object getTemplateResponse() {
        return templateResponse;
    }

    public void setTemplateResponse(Object templateResponse) {
        this.templateResponse = templateResponse;
    }

    public Object getTaskResponse() {
        return taskResponse;
    }

    public void setTaskResponse(Object taskResponse) {
        this.taskResponse = taskResponse;
    }

    public Object getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(Object userResponse) {
        this.userResponse = userResponse;
    }

    public List<?> getWoAssignBnList() {
        return woAssignBnList;
    }

    public void setWoAssignBnList(List<?> woAssignBnList) {
        this.woAssignBnList = woAssignBnList;
    }

    public List<?> getDepartmentBnList() {
        return departmentBnList;
    }

    public void setDepartmentBnList(List<?> departmentBnList) {
        this.departmentBnList = departmentBnList;
    }

    public List<?> getCbdList() {
        return cbdList;
    }

    public void setCbdList(List<?> cbdList) {
        this.cbdList = cbdList;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public List<MenuBn> getMenuBnList() {
        return menuBnList;
    }

    public void setMenuBnList(List<MenuBn> menuBnList) {
        this.menuBnList = menuBnList;
    }

    public String getMsg() {
        return msg;
    }

    public UserBn getUserBn() {
        return userBn;
    }

    public void setUserBn(UserBn userBn) {
        this.userBn = userBn;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
