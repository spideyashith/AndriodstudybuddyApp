package com.example.studyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button btnLogout, btnchat;
    ProgressBar progressBar;

    TextView tvName, tvCourse, tvSemester, tvBio, tvGoals, tvInterest;

    FirebaseAuth mAuth;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
            return;
        }

        String uid = currentUser.getUid();
        userRef = FirebaseDatabase.getInstance().getReference("Users").child(uid);

        // UI components
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        btnLogout = findViewById(R.id.btnLogout);
        btnchat = findViewById(R.id.chatbtn);
        progressBar = findViewById(R.id.progressBar);

        tvName = findViewById(R.id.tvName);
        tvCourse = findViewById(R.id.tvCourse);
        tvSemester = findViewById(R.id.tvSemester);
        tvBio = findViewById(R.id.tvBio);
        tvGoals = findViewById(R.id.tvGoals);
        tvInterest = findViewById(R.id.tvInterest);

        // Toolbar and drawer setup
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            int id = item.getItemId();
            if (id == R.id.nav_logout) {
                mAuth.signOut();
                Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
            return true;
        });

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        });

        findViewById(R.id.btnfindpatner).setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, FindStudyPartnerActivity.class)));

        findViewById(R.id.btnresoursesharing).setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ResourceSharingActivity.class)));

        findViewById(R.id.btnschedulehome).setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ScheduleActivity.class)));

        btnchat.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, InAppChatActivity.class)));

        findViewById(R.id.rating).setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, RatingActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Show progress bar while loading
        progressBar.setVisibility(View.VISIBLE);

        // Load user profile data from Firebase
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);

                if (snapshot.exists()) {
                    Toast.makeText(HomeActivity.this, "Data loaded successfully", Toast.LENGTH_SHORT).show();

                    tvName.setText("Name: " + snapshot.child("name").getValue(String.class));
                    tvCourse.setText("Course: " + snapshot.child("course").getValue(String.class));
                    tvSemester.setText("Semester: " + snapshot.child("semester").getValue(String.class));
                    tvBio.setText("Bio: " + snapshot.child("bio").getValue(String.class));
                    tvGoals.setText("Goals: " + snapshot.child("goals").getValue(String.class));
                    tvInterest.setText("Interest: " + snapshot.child("interest").getValue(String.class));
                } else {
                    Toast.makeText(HomeActivity.this, "No user data found", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(HomeActivity.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
