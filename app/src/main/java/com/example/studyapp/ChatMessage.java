package com.example.studyapp;

public class ChatMessage {
    public String message;
    public boolean isSentByUser;

    public ChatMessage(String message, boolean isSentByUser) {
        this.message = message;
        this.isSentByUser = isSentByUser;
    }
}
