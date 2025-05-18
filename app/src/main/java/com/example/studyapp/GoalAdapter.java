package com.example.studyapp;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.google.firebase.database.DatabaseReference;
import java.util.List;

public class GoalAdapter extends ArrayAdapter<Goal> {

    private final List<Goal> goals;
    private final DatabaseReference dbRef;

    public GoalAdapter(Context context, List<Goal> goals, DatabaseReference dbRef) {
        super(context, 0, goals);
        this.goals = goals;
        this.dbRef = dbRef;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);

        Goal goal = goals.get(pos);
        CheckedTextView checkBox = (CheckedTextView) convertView;
        checkBox.setText(goal.goalText);  // Correct field
        checkBox.setChecked(goal.isCompleted);

        checkBox.setOnClickListener(v -> {
            goal.isCompleted = !goal.isCompleted;
            if (goal.goalId != null) {
                dbRef.child(goal.goalId).setValue(goal);
            }
        });

        return convertView;
    }
}
