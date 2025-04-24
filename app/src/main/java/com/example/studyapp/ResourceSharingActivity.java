package com.example.studyapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ResourceSharingActivity extends AppCompatActivity {
    EditText resourseInput;
    Button btnupload;
    ListView resourselistview;

    ArrayList<String> resourseList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resource_sharing);

        resourseInput = findViewById(R.id.edittextresourse);
        btnupload = findViewById(R.id.buttonUpload);
        resourselistview = findViewById(R.id.listviewresourse);


        resourseList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resourseList);
        resourselistview.setAdapter(adapter);


        btnupload.setOnClickListener(v -> {
            String resoursename = resourseInput.getText().toString().trim();
            if (!resoursename.isEmpty()){
                resourseList.add(resoursename);
                adapter.notifyDataSetChanged();
                resourseInput.setText("");
            }
        });

    }
}