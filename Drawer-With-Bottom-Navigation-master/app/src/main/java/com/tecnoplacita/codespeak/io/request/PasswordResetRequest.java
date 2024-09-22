package com.tecnoplacita.codespeak.io.request;


public class PasswordResetRequest {

    private final String email;

    public PasswordResetRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}