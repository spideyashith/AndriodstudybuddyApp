package com.example.studyapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResourceSharingActivity extends AppCompatActivity {

    private EditText resourseInput;
    private Button btnUpload;
    private ListView resourseListView;

    private ArrayList<String> resourseList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resource_sharing);

        // Initialize Views
        resourseInput = findViewById(R.id.edittextresourse);
        btnUpload = findViewById(R.id.buttonUpload);
        resourseListView = findViewById(R.id.listviewresourse);

        // Initialize List and Adapter
        resourseList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resourseList);
        resourseListView.setAdapter(adapter);

        // Handle Upload Button Click
        btnUpload.setOnClickListener(v -> {
            String resourseName = resourseInput.getText().toString().trim();

            if (!resourseName.isEmpty()) {
                resourseList.add(resourseName);
                adapter.notifyDataSetChanged();
                resourseInput.setText("");
                Toast.makeText(this, "Resource uploaded!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter a resource name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
