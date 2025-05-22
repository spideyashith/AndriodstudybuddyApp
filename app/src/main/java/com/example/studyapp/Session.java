package com.example.studyapp;

public class Session {
    public String sessionId;
    public String topic;
    public String date;
    public String time;

    public Session() {
        // Required for Firebase
    }

    public Session(String sessionId, String topic, String date, String time) {
        this.sessionId = sessionId;
        this.topic = topic;
        this.date = date;
        this.time = time;
    }
}
