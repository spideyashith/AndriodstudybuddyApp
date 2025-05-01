package com.example.studyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        btnLogout = findViewById(R.id.btnLogout);

        Button feedback = findViewById(R.id.rating);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,RatingActivity.class);
                startActivity(intent);
            }
        });

        // Setup toolbar
        setSupportActionBar(toolbar);

        // Setup drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();

                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Toast.makeText(HomeActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_study_partner) {
                    Toast.makeText(HomeActivity.this, "Study Partner Selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_forums) {
                    Toast.makeText(HomeActivity.this, "Discussion Forums Selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_resources) {
                    Toast.makeText(HomeActivity.this, "Resources Selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_logout) {
                    Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity
                }

                return true;
            }
        });

        // Optional: handle logout button
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            finish();
        });
        findViewById(R.id.btnfindpatner).setOnClickListener(v->{
            Intent intent = new Intent(HomeActivity.this, FindStudyPartnerActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnresoursesharing).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ResourceSharingActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnschedulehome).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ScheduleActivity.class);
            startActivity(intent);
        });







    }




}
