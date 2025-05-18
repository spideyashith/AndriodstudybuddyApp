package com.example.studyapp;

import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import java.util.*;

public class StudyGoalsActivity extends AppCompatActivity {

    EditText etGoal;
    Button btnAddGoal;
    RecyclerView goalsRecyclerView;
    ProgressBar progressBar;
    TextView tvProgress;

    List<Goal> goalList = new ArrayList<>();
    GoalRecyclerAdapter adapter;
    DatabaseReference goalsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_goals);

        etGoal = findViewById(R.id.etGoal);
        btnAddGoal = findViewById(R.id.btnAddGoal);
        goalsRecyclerView = findViewById(R.id.goalsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        goalsRef = FirebaseDatabase.getInstance().getReference("StudyGoals").child(uid);

        goalsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoalRecyclerAdapter(goalList, goalsRef);
        goalsRecyclerView.setAdapter(adapter);

        btnAddGoal.setOnClickListener(v -> {
            String goalText = etGoal.getText().toString().trim();
            if (!goalText.isEmpty()) {
                String goalId = goalsRef.push().getKey();
                Goal newGoal = new Goal(goalId, goalText, false);
                goalsRef.child(goalId).setValue(newGoal);
                etGoal.setText("");
            }
        });

        goalsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                goalList.clear();
                int completed = 0;
                for (DataSnapshot goalSnap : snapshot.getChildren()) {
                    Goal goal = goalSnap.getValue(Goal.class);
                    if (goal != null) {
                        goalList.add(goal);
                        if (goal.isCompleted) completed++;
                    }
                }
                adapter.notifyDataSetChanged();
                updateProgress(completed, goalList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudyGoalsActivity.this, "Failed to load goals", Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Goal goalToDelete = adapter.getGoalAt(position);

                if (goalToDelete.goalId != null) {
                    goalsRef.child(goalToDelete.goalId).removeValue()
                            .addOnSuccessListener(aVoid -> Toast.makeText(StudyGoalsActivity.this, "Goal deleted", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(StudyGoalsActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show());
                }
                goalList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(goalsRecyclerView);
    }

    private void updateProgress(int completed, int total) {
        int percent = (total == 0) ? 0 : (completed * 100 / total);
        progressBar.setProgress(percent);
        tvProgress.setText(percent + "% Complete");
    }

}
