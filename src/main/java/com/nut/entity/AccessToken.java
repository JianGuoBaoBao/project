package com.nut.entity;

public class AccessToken {
    private String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken() {
        super();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }
}