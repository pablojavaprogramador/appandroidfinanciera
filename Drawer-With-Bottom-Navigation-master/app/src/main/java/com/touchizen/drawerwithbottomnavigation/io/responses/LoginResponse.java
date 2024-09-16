package com.touchizen.drawerwithbottomnavigation.io.responses;


public class LoginResponse {
    private String token;
    private String displayName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}