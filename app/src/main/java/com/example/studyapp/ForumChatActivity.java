package com.example.studyapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForumChatActivity extends AppCompatActivity {

    ListView messagelistview;
    EditText messageinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forum_chat);


        messagelistview.findViewById(R.id.messagelistview);
        messageinput.findViewById(R.id.messageinput);



    }
}