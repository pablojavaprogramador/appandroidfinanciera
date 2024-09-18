package com.touchizen.drawerwithbottomnavigation.io.request;

public class PasswordResetConfirmationRequest {

    private final String token;
    private final String newPassword;

    public PasswordResetConfirmationRequest(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
