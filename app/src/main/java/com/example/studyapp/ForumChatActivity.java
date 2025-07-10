package com.example.studyapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ForumChatActivity extends AppCompatActivity {

    ListView messagelistview;
    EditText messageinput;
    Button sendButton;

    ArrayList<ChatMessage> messagelist;
    ChatAdapter messageAdapter;

    DatabaseReference chatRef;
    String currentUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forum_chat);

        messagelistview = findViewById(R.id.messagelistview);
        messageinput = findViewById(R.id.messageinput);
        sendButton = findViewById(R.id.sendbutton);

        messagelist = new ArrayList<>();
        messageAdapter = new ChatAdapter(this, messagelist);
        messagelistview.setAdapter(messageAdapter);

        currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        chatRef = FirebaseDatabase.getInstance().getReference("inappchat");

        // Load messages in real time
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                messagelist.clear();
                for (DataSnapshot messageSnap : snapshot.getChildren()) {
                    String message = messageSnap.child("message").getValue(String.class);
                    String sender = messageSnap.child("sender").getValue(String.class);

                    if (message != null && sender != null) {
                        boolean isSentByUser = sender.equals(currentUid);
                        messagelist.add(new ChatMessage(message, isSentByUser));
                    }
                }
                messageAdapter.notifyDataSetChanged();
                messagelistview.setSelection(messagelist.size() - 1); // Auto scroll
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
            }
        });

        // Send new message
        sendButton.setOnClickListener(v -> {
            String message = messageinput.getText().toString().trim();
            if (!message.isEmpty()) {
                HashMap<String, Object> chatMessage = new HashMap<>();
                chatMessage.put("message", message);
                chatMessage.put("sender", currentUid);
                chatRef.push().setValue(chatMessage);
                messageinput.setText("");
            }
        });
    }
}
