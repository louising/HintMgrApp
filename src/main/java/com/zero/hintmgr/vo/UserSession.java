package com.zero.hintmgr.vo;

public class UserSession {
    private String userId;
    private String rolePosition;

    public UserSession() {
    }

    public UserSession(String userId, String rolePosition) {
        super();
        this.userId = userId;
        this.rolePosition = rolePosition;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRolePosition() {
        return rolePosition;
    }

    public void setRolePosition(String rolePosition) {
        this.rolePosition = rolePosition;
    }

    @Override
    public String toString() {
        return "UserSession [userId=" + userId + ", rolePosition=" + rolePosition + "]";
    }
}
