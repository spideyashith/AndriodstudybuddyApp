package com.example.studyapp;

import android.os.Bundle;
import android.view.View;
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

public class ForumChatActivity extends AppCompatActivity {

    private ListView messagelistview;
    private  EditText messageinput;

    private Button sendButton;

    private ArrayList<String> messagelist;
    private ArrayAdapter<String> messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forum_chat);


        messagelistview.findViewById(R.id.messagelistview);
        messageinput.findViewById(R.id.messageinput);
        sendButton.findViewById(R.id.sendbutton);

        messagelist = new ArrayList<>();
        messageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,messagelist);
        messagelistview.setAdapter(messageAdapter);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageinput.getText().toString().trim();
                if (!message.isEmpty()){
                    messagelist.add("You: " +message);
                    messageAdapter.notifyDataSetChanged();
                    messageinput.setText("");

                }
            }
        });



    }
}