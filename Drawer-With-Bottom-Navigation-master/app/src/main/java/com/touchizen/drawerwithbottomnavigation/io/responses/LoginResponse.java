package com.touchizen.drawerwithbottomnavigation.io.responses;


import retrofit2.Response;

public class LoginResponse {

    private String id_token;

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
}
