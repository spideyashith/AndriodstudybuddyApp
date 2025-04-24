package com.example.studyapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindStudyPartnerActivity extends AppCompatActivity {
    Spinner coursespiner, topicspinner, availabilitySpinner;
    ListView matchlistviesw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_study_partner);

        coursespiner = findViewById(R.id.spinnercourse);
        topicspinner = findViewById(R.id.spinnertopic);
        availabilitySpinner = findViewById(R.id.spinneravability);
        matchlistviesw = findViewById(R.id.listviewmatches);


        String[] courses = {"Select Course", "BCA", "BBA", "Engineering"};
        String[] topics = {"Select Topic", "Math", "Programming", "Marketing"};
        String[] availability = {"Select Availability", "Morning", "Evening", "Online"};


        coursespiner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courses));
        topicspinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, topics));
        availabilitySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,availability));


        //Dummy data
        String[] dummymatches = {"Rahul - BCA - Evenings", "Snea - BBA- Morings"};
        matchlistviesw.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dummymatches));



    }
}