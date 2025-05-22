package com.example.studyapp;

import java.util.List;

public class StudyPartner {
    public String name, course, year;
    public List<String> interests;
    public boolean matched;
    public String uid;

    public StudyPartner() {} // Default for Firebase

    public StudyPartner(String name, String course, String year, List<String> interests, boolean matched) {
        this.name = name;
        this.course = course;
        this.year = year;
        this.interests = interests;
        this.matched = matched;
    }
}
