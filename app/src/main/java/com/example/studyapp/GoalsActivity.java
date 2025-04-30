package com.example.studyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class GoalsActivity extends AppCompatActivity {
    EditText textgoal;
    Button btnaddgoal;
    ListView listgoalview;

    ArrayList<String> goallist;
    ArrayAdapter<String>adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goals);


        textgoal = findViewById(R.id.goalinput);
        btnaddgoal = findViewById(R.id.btnddgoal);
        listgoalview = findViewById(R.id.goalsidview);

        goallist = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, goallist);
        listgoalview.setAdapter(adapter);
        listgoalview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        btnaddgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goal = textgoal.getText().toString().trim();
                if (!goal.isEmpty()){
                    goallist.add(goal);
                    adapter.notifyDataSetChanged();
                    textgoal.setText("");

                }else {
                    Toast.makeText(GoalsActivity.this, "Please Enter a Goal!", Toast.LENGTH_LONG).show();
                }
            }
        });

        listgoalview.setOnItemClickListener((adapter, view, position, id) ->{
            String selectedgoal = goallist.get(position);
            boolean checked = listgoalview.isItemChecked(position);
            if (checked) {
                Toast.makeText(GoalsActivity.this, "Marked as complete: " + selectedgoal, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(GoalsActivity.this, "Unchecked: " + selectedgoal, Toast.LENGTH_SHORT).show();
            }
        });


    }
}