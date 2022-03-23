package com.zero.hintmgr.vo;

public class UserInfoBean {
    private String code; //wx.login()
    private String userName;
    private String tel;
    private String inviteCode;
    
    private String userId;
    
    private String nickName;
    private String avatarUrl;

    public UserInfoBean() {
        super();
    }
    
    public UserInfoBean(String code, String userName, String tel, String inviteCode) {
        super();
        this.code = code;
        this.userName = userName;
        this.tel = tel;
        this.inviteCode = inviteCode;
    }

    public UserInfoBean(String uid) {
        super();
        this.userId = uid;
    }
    
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUid() {
        return userId;
    }

    public void setUid(String uid) {
        this.userId = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfoBean [code=" + code + ", userName=" + userName + ", tel=" + tel + ", inviteCode=" + inviteCode + ", userId=" + userId + "]";
    } 
}
