package com.example.studyapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    EditText topicinput;
    Button pickdatebtn, pickTimebtn, schedulebtn;
    TextView selectDatetime;
    ListView sessionlist;
    ArrayList<String> sessionitems;
    ArrayAdapter<String> adapter;

    String selectedDate = "", selectedTime = "";

    DatabaseReference sessionsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule);

        topicinput = findViewById(R.id.topicInput);
        pickdatebtn = findViewById(R.id.btnPickDate);
        pickTimebtn = findViewById(R.id.btnPickTime);
        schedulebtn = findViewById(R.id.btnSchedule);
        selectDatetime = findViewById(R.id.selectedDateTime);
        sessionlist = findViewById(R.id.sessionList);

        sessionitems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sessionitems);
        sessionlist.setAdapter(adapter);

        // Firebase reference
        sessionsRef = FirebaseDatabase.getInstance().getReference("sessions");

        pickdatebtn.setOnClickListener(v -> showDatePicker());
        pickTimebtn.setOnClickListener(v -> showTimePicker());

        schedulebtn.setOnClickListener(v -> {
            String topic = topicinput.getText().toString().trim();
            if (!topic.isEmpty() && !selectedDate.isEmpty() && !selectedTime.isEmpty()) {
                String sessionText = "📚" + topic + "\n📅" + selectedDate + " ⏰" + selectedTime;
                sessionitems.add(sessionText);
                adapter.notifyDataSetChanged();

                // Save to Firebase
                String sessionId = sessionsRef.push().getKey();
                Session session = new Session(sessionId, topic, selectedDate, selectedTime);
                sessionsRef.child(sessionId).setValue(session)
                        .addOnSuccessListener(unused -> Toast.makeText(this, "Session saved to Firebase", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());

                // Reset fields
                topicinput.setText("");
                selectedDate = "";
                selectedTime = "";
                selectDatetime.setText("");
            } else {
                Toast.makeText(this, "Fill All The Fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    updateDateTimeDisplay();
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hour, minute) -> {
                    selectedTime = String.format("%02d:%02d", hour, minute);
                    updateDateTimeDisplay();
                },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void updateDateTimeDisplay() {
        if (!selectedDate.isEmpty() && !selectedTime.isEmpty()) {
            selectDatetime.setText("Session: " + selectedDate + " at " + selectedTime);
        }
    }
}
