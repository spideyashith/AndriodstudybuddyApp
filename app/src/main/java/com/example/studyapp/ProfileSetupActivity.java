package com.example.studyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ProfileSetupActivity extends AppCompatActivity {

    EditText etname, etcourse, etsemester, etbio, etgoals, etintrest;
    Button btnsaveprofile;

    FirebaseAuth mAuth;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_setup);

        etname = findViewById(R.id.etname);
        etcourse = findViewById(R.id.etcourse);
        etsemester = findViewById(R.id.etsemester);
        etbio = findViewById(R.id.etbio);
        etgoals = findViewById(R.id.etgoals);
        etintrest = findViewById(R.id.etintrest);
        btnsaveprofile = findViewById(R.id.btnsaveprofile);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        String uid = currentUser.getUid();
        userRef = FirebaseDatabase.getInstance().getReference("Users").child(uid);

        btnsaveprofile.setOnClickListener(v -> {
            String name = etname.getText().toString().trim();
            String course = etcourse.getText().toString().trim();
            String semester = etsemester.getText().toString().trim();
            String bio = etbio.getText().toString().trim();
            String goals = etgoals.getText().toString().trim();
            String interest = etintrest.getText().toString().trim();

            if (name.isEmpty() || course.isEmpty() || semester.isEmpty()) {
                Toast.makeText(this, "Name, Course, and Semester are required.", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> profileMap = new HashMap<>();
            profileMap.put("name", name);
            profileMap.put("course", course);
            profileMap.put("semester", semester);
            profileMap.put("bio", bio);
            profileMap.put("goals", goals);
            profileMap.put("interest", interest);

            // Show progress dialog while saving
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Saving profile...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            userRef.setValue(profileMap)
                    .addOnSuccessListener(aVoid -> {
                        progressDialog.dismiss();
                        Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileSetupActivity.this, HomeActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(this, "Failed to save profile: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        });
    }
}
