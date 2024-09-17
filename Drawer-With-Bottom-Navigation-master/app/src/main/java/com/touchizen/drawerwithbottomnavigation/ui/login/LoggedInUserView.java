package com.touchizen.drawerwithbottomnavigation.ui.login;

public class LoggedInUserView {
    private String displayName;
    private String email;

    public LoggedInUserView(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
