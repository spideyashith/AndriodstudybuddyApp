package com.example.studyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    private List<ChatMessage> messages;
    private LayoutInflater inflater;

    public ChatAdapter(Context context, List<ChatMessage> messages) {
        this.messages = messages;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public ChatMessage getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;  // Sent and Received
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).isSentByUser ? VIEW_TYPE_SENT : VIEW_TYPE_RECEIVED;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = getItem(position);
        int viewType = getItemViewType(position);

        if (convertView == null) {
            if (viewType == VIEW_TYPE_SENT) {
                convertView = inflater.inflate(R.layout.item_chat_sent, parent, false);
            } else {
                convertView = inflater.inflate(R.layout.item_chat_received, parent, false);
            }
        }

        TextView messageBody = convertView.findViewById(R.id.text_message_body);
        messageBody.setText(message.message);

        return convertView;
    }
}
