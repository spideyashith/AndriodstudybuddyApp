package com.example.studyapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InAppChatActivity extends AppCompatActivity {
    EditText messageInput;
    ImageButton sendMessageBtn;
    ListView chatListView;

    ArrayList<String> messageList;
    ArrayAdapter<String> adapter;

    DatabaseReference chatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_chat);

        messageInput = findViewById(R.id.chatmessageInput);
        sendMessageBtn = findViewById(R.id.sendmessagebtn);
        chatListView = findViewById(R.id.chatlistview);

        messageList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messageList);
        chatListView.setAdapter(adapter);

        // Firebase DB reference
        chatRef = FirebaseDatabase.getInstance().getReference("inappchat");

        // Send message to Firebase
        sendMessageBtn.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();

            if (!message.isEmpty()) {
                String msgId = chatRef.push().getKey();
                chatRef.child(msgId).setValue("You: " + message);
                messageInput.setText("");
            } else {
                Toast.makeText(this, "Enter a message", Toast.LENGTH_SHORT).show();
            }
        });

        // Listen for new messages
        chatRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot msgSnapshot : snapshot.getChildren()) {
                    String msg = msgSnapshot.getValue(String.class);
                    messageList.add(msg);
                }
                adapter.notifyDataSetChanged();
                chatListView.smoothScrollToPosition(messageList.size() - 1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(InAppChatActivity.this, "Failed to load messages.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
