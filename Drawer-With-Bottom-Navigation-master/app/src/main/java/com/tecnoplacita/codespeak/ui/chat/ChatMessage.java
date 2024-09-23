package com.tecnoplacita.codespeak.ui.chat;

public class ChatMessage {
    private String message;
    private boolean isUser; // true si es del usuario, false si es del bot

    public ChatMessage(String message, boolean isUser) {
        this.message = message;
        this.isUser = isUser;
    }

    public String getMessage() {
        return message;
    }

    public boolean isUser() {
        return isUser;
    }
}
