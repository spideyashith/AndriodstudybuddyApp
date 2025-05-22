package com.example.studyapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ResourceAdapter extends ArrayAdapter<String> {

    public ResourceAdapter(Context context, List<String> resources) {
        super(context, android.R.layout.simple_list_item_1, resources);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        String url = getItem(position);
        view.setText("PDF Resource " + (position + 1));
        view.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            getContext().startActivity(intent);
        });
        return view;
    }
}
