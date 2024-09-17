package com.touchizen.drawerwithbottomnavigation.io.request;

public class LoginRequest {

    private String email;
    private String password;

    // Constructor sin argumentos
    public LoginRequest() {
    }

    // Constructor con argumentos
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
