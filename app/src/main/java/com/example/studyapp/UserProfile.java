package com.example.studyapp;

import java.util.List;

public class UserProfile {
    public String uid;
    public String name;
    public List<String> subjects;
    public String studyTime;
    public String bio;

    public UserProfile() {}

    public UserProfile(String uid, String name, List<String> subjects, String studyTime, String bio) {
        this.uid = uid;
        this.name = name;
        this.subjects = subjects;
        this.studyTime = studyTime;
        this.bio = bio;
    }
}
