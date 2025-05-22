package com.example.studyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindStudyPartnerActivity extends AppCompatActivity {

    Spinner coursespiner, topicspinner, availabilitySpinner;
    ListView matchlistviesw;

    private String selectedCourse = "";
    private String selectedTopic = "";
    private String selectedAvailability = "";

    private List<String> matchedList = new ArrayList<>();
    private ArrayAdapter<String> matchAdapter;

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
        topicspinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, topics));
        availabilitySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, availability));

        matchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matchedList);
        matchlistviesw.setAdapter(matchAdapter);

        // Spinner Listeners
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                selectedCourse = coursespiner.getSelectedItem().toString();
                selectedTopic = topicspinner.getSelectedItem().toString();
                selectedAvailability = availabilitySpinner.getSelectedItem().toString();
                fetchStudyPartners();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };

        coursespiner.setOnItemSelectedListener(spinnerListener);
        topicspinner.setOnItemSelectedListener(spinnerListener);
        availabilitySpinner.setOnItemSelectedListener(spinnerListener);
    }

    private void fetchStudyPartners() {
        matchedList.clear();

        FirebaseDatabase.getInstance().getReference("study_partners")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            if (!userSnapshot.getKey().equals(currentUid)) {
                                String name = userSnapshot.child("name").getValue(String.class);
                                String course = userSnapshot.child("course").getValue(String.class);
                                String year = userSnapshot.child("year").getValue(String.class);
                                List<String> interests = new ArrayList<>();

                                for (DataSnapshot interest : userSnapshot.child("interests").getChildren()) {
                                    interests.add(interest.getValue(String.class));
                                }

                                // Filtering logic
                                boolean courseMatch = selectedCourse.equals("Select Course") || course.equals(selectedCourse);
                                boolean topicMatch = selectedTopic.equals("Select Topic") || interests.contains(selectedTopic);

                                if (courseMatch && topicMatch) {
                                    matchedList.add(name + " - " + course + " - Year " + year);
                                }
                            }
                        }

                        matchAdapter.notifyDataSetChanged();

                        if (matchedList.isEmpty()) {
                            matchedList.add("No matches found.");
                            matchAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FindStudyPartnerActivity.this, "Failed to load matches.", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
