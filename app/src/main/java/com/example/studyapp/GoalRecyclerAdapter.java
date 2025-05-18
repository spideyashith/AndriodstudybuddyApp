package com.example.studyapp;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class GoalRecyclerAdapter extends RecyclerView.Adapter<GoalRecyclerAdapter.GoalViewHolder> {

    private final List<Goal> goalList;
    private final DatabaseReference dbRef;

    public GoalRecyclerAdapter(List<Goal> goalList, DatabaseReference dbRef) {
        this.goalList = goalList;
        this.dbRef = dbRef;
    }

    public static class GoalViewHolder extends RecyclerView.ViewHolder {
        CheckedTextView checkedTextView;

        public GoalViewHolder(View itemView) {
            super(itemView);
            checkedTextView = (CheckedTextView) itemView;
        }
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CheckedTextView view = (CheckedTextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        Goal goal = goalList.get(position);
        holder.checkedTextView.setText(goal.goalText);
        holder.checkedTextView.setChecked(goal.isCompleted);

        holder.checkedTextView.setOnClickListener(v -> {
            goal.isCompleted = !goal.isCompleted;
            if (goal.goalId != null) {
                dbRef.child(goal.goalId).setValue(goal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    public Goal getGoalAt(int position) {
        return goalList.get(position);
    }
}
