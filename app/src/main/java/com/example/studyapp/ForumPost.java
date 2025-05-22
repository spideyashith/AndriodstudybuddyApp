package com.example.studyapp;

public class ForumPost {
    public String postId;
    public String userId;
    public String username;
    public String message;
    public long timestamp;

    public ForumPost() {} // Required by Firebase

    public ForumPost(String postId, String userId, String username, String message, long timestamp) {
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }
}
