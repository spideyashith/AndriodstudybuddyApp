package com.example.studyapp;

public class Goal {
    public String goalId;
    public String goalText;
    public boolean isCompleted;

    public Goal() {
        // Required for Firebase
    }

    public Goal(String goalId, String goalText, boolean isCompleted) {
        this.goalId = goalId;
        this.goalText = goalText;
        this.isCompleted = isCompleted;
    }
}
