package com.nut.entity;

import java.util.Date;

public class UserInfo {
    private Integer userId;

    private String username;

    private String password;

    private String token;

    private Date createTime;

    private Date editTime;

    public UserInfo(Integer userId, String username, String password, String token, Date createTime, Date editTime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.token = token;
        this.createTime = createTime;
        this.editTime = editTime;
    }

    public UserInfo() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}