package com.example.studyapp;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InAppChatActivity extends AppCompatActivity {
    EditText messageinput;
    ImageButton sendmessagebtn;
    ListView chatlistview;

    ArrayList<String> messagelist;
    ArrayAdapter<String> adapter;
    Handler handler = new Handler();  // for delayed responses

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_in_app_chat);

        messageinput = findViewById(R.id.chatmessageInput);
        sendmessagebtn = findViewById(R.id.sendmessagebtn);
        chatlistview = findViewById(R.id.chatlistview);

        messagelist = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messagelist);
        chatlistview.setAdapter(adapter);

        sendmessagebtn.setOnClickListener(v -> {
            String message = messageinput.getText().toString().trim();

            if (!message.isEmpty()) {
                // Add user's message
                messagelist.add("You: " + message);
                adapter.notifyDataSetChanged();
                chatlistview.smoothScrollToPosition(messagelist.size() - 1);
                messageinput.setText("");

                // Simulate buddy's reply after 1.5 seconds
                handler.postDelayed(() -> {
                    String reply = getMockReply(message);
                    messagelist.add("Buddy: " + reply);
                    adapter.notifyDataSetChanged();
                    chatlistview.smoothScrollToPosition(messagelist.size() - 1);
                }, 1500);
            }
        });
    }

    // Simple mock replies based on keywords
    private String getMockReply(String userMessage) {
        String lowerMsg = userMessage.toLowerCase();
        if (lowerMsg.contains("hello") || lowerMsg.contains("hi")) {
            return "Hey there! Ready to study?";
        } else if (lowerMsg.contains("notes") || lowerMsg.contains("unit")) {
            return "I can help! Which unit do you need?";
        } else if (lowerMsg.contains("thanks") || lowerMsg.contains("thank you")) {
            return "You're welcome! 😊";
        } else {
            return "Sounds good! Let's stay focused. 📚";
        }
    }
}
