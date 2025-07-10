package com.example.studyapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;

public class InAppChatActivity extends AppCompatActivity {

    ListView chatListView;
    EditText chatMessageInput;
    ImageButton sendMessageBtn;

    ArrayList<ChatMessage> messageList;
    ChatAdapter adapter;

    DatabaseReference chatRef;
    String currentUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_chat);

        chatListView = findViewById(R.id.chatlistview);
        chatMessageInput = findViewById(R.id.chatmessageInput);
        sendMessageBtn = findViewById(R.id.sendmessagebtn);

        messageList = new ArrayList<>();
        adapter = new ChatAdapter(this, messageList);
        chatListView.setAdapter(adapter);

        currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        chatRef = FirebaseDatabase.getInstance().getReference("inappchat");

        // Listen for messages
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot msgSnap : snapshot.getChildren()) {
                    String msg = msgSnap.child("message").getValue(String.class);
                    String sender = msgSnap.child("sender").getValue(String.class);
                    if (msg != null && sender != null) {
                        boolean isSentByMe = sender.equals(currentUid);
                        messageList.add(new ChatMessage(msg, isSentByMe));
                    }
                }
                adapter.notifyDataSetChanged();
                chatListView.setSelection(adapter.getCount() - 1); // scroll to bottom
            }

            @Override
            public void onCancelled(DatabaseError error) { }
        });

        // Send message
        sendMessageBtn.setOnClickListener(v -> {
            String text = chatMessageInput.getText().toString().trim();
            if (!text.isEmpty()) {
                HashMap<String, Object> message = new HashMap<>();
                message.put("message", text);
                message.put("sender", currentUid);
                chatRef.push().setValue(message);
                chatMessageInput.setText("");
            }
        });
    }
}
