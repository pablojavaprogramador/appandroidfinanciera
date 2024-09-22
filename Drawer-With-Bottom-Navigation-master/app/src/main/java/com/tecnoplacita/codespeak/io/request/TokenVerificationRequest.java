package com.tecnoplacita.codespeak.io.request;

public class TokenVerificationRequest {
    private String token;

    public TokenVerificationRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
