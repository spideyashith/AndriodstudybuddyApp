package com.example.studyapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RatingActivity extends AppCompatActivity {
    RatingBar ratingbar;
    EditText feedbackinput;
    Button submitfeedback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rating);

        ratingbar = findViewById(R.id.ratingBar);
        feedbackinput = findViewById(R.id.feedback_input);
        submitfeedback = findViewById(R.id.submit_feedback);

        submitfeedback.setOnClickListener(view ->{
            float rating = ratingbar.getRating();
            String feedback = feedbackinput.getText().toString().trim();


            if (rating == 0){
                Toast.makeText(RatingActivity.this, "Please Give a rating!", Toast.LENGTH_SHORT).show();
            }else {
                String feedbackmessage = "Rating: "+ rating + "\nFeedback: " +feedback;
                Toast.makeText(RatingActivity.this, feedbackmessage, Toast.LENGTH_LONG).show();


                ratingbar.setRating(0);
                feedbackinput.setText("");
            }
        });





    }
}