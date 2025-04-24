package com.example.studyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DiscussionForumActivity extends AppCompatActivity {
    ListView formListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_discussion_forum);
        setContentView(R.layout.activity_discussion_forum);


        formListview = findViewById(R.id.forumlistview);

        String[] courseforum = {"BCA Forum", "BBA Forum", "MCA Forum", "MSC Forum", "MBA Forum"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseforum);
        formListview.setAdapter(adapter);

        formListview.setOnItemClickListener(((parent, view, position, id) -> {


            Intent intent = new Intent(DiscussionForumActivity.this, ForumChatActivity.class);
            startActivity(intent);
        }));


    }
}