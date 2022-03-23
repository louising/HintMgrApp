package com.zero.hintmgr.vo;

public class ParamObj {
    private String projectId;
    private String rolePosition;
    private String orderColumn;
    private String orderType;

    @Override
    public String toString() {
        return "ParamObj [projectId=" + projectId + ", rolePosition=" + rolePosition + ", orderColumn=" + orderColumn + ", orderType=" + orderType + "]";
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRolePosition() {
        return rolePosition;
    }

    public void setRolePosition(String userType) {
        this.rolePosition = userType;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

}
