package com.example.studyapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class InAppChatActivity extends AppCompatActivity {
    EditText messageinput;
    ImageButton sendmessageinput;
    ListView messagelistinput;

    ArrayList<String> messagelist;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_in_app_chat);

        messageinput = findViewById(R.id.chatmessageInput);
        sendmessageinput = findViewById(R.id.sendmessagebtn);
        messagelistinput = findViewById(R.id.chatlistview);

        messagelist = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messagelist);
        messagelistinput.setAdapter(adapter);

        sendmessageinput.setOnClickListener(v ->{
            String message = messageinput.getText().toString().trim();

            if (!message.isEmpty()){
                messagelist.add(message);
                adapter.notifyDataSetChanged();
                messageinput.setText("");
            }
        });

    }
}