package com.example.studyapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class MatchPartnerActivity extends AppCompatActivity implements StudyPartnerAdapter.OnPartnerClickListener {

    RecyclerView recyclerView;
    StudyPartnerAdapter adapter;
    List<StudyPartner> partnerList = new ArrayList<>();
    DatabaseReference usersRef;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_partner);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StudyPartnerAdapter(this, partnerList, this);
        recyclerView.setAdapter(adapter);

        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        usersRef = FirebaseDatabase.getInstance().getReference("Users");

        loadPartners();
    }

    private void loadPartners() {
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                partnerList.clear();
                for (DataSnapshot userSnap : snapshot.getChildren()) {
                    StudyPartner partner = userSnap.getValue(StudyPartner.class);
                    // Exclude current user from partner list
                    if (partner != null && !partner.uid.equals(currentUserId)) {
                        partnerList.add(partner);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MatchPartnerActivity.this, "Failed to load partners", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestClick(StudyPartner partner) {
        // For now, just show a Toast. Later we can add sending requests feature
        Toast.makeText(this, "Requested to study with " + partner.name, Toast.LENGTH_SHORT).show();
    }
}
