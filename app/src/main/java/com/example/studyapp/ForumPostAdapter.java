package com.example.studyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;

public class ForumPostAdapter extends ArrayAdapter<ForumPost> {

    public ForumPostAdapter(Context context, List<ForumPost> posts) {
        super(context, 0, posts);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ForumPost post = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView usernameTimeView = convertView.findViewById(android.R.id.text1);
        TextView messageView = convertView.findViewById(android.R.id.text2);

        if (post != null) {
            usernameTimeView.setText(post.username + " - " + new Date(post.timestamp).toString());
            messageView.setText(post.message);
        }

        return convertView;
    }
}
