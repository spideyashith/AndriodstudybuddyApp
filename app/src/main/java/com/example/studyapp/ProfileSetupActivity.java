package com.example.studyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileSetupActivity extends AppCompatActivity {
    EditText etname, etcourse, etsemester, etbio, etgoals, etintrest;
    Button btnsaveprofile;

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

        btnsaveprofile.setOnClickListener(v -> {


            Intent intent = new Intent(ProfileSetupActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });







    }
}