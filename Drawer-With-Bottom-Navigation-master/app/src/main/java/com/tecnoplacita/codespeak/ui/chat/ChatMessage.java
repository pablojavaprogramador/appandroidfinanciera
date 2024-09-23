package com.tecnoplacita.codespeak.ui.chat;

public class ChatMessage {
    private final String text;
    private final boolean isUserMessage; // true if the message is from the user, false if it's from the bot

    public ChatMessage(String text, boolean isUserMessage) {
        this.text = text;
        this.isUserMessage = isUserMessage;
    }

    public String getText() {
        return text;
    }

    public boolean isUserMessage() {
        return isUserMessage;
    }
}
