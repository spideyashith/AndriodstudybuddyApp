package com.example.studyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ResourceSharingActivity extends AppCompatActivity {

    private static final int PICK_PDF_CODE = 1;
    private Button btnUploadPDF;
    private ListView resourseListView;

    private ArrayList<String> resourseList;
    private ResourceAdapter adapter;

    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private DatabaseReference resourceRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resource_sharing);

        btnUploadPDF = findViewById(R.id.buttonUpload);
        resourseListView = findViewById(R.id.listviewresourse);

        resourseList = new ArrayList<>();
        adapter = new ResourceAdapter(this, resourseList); // Custom adapter needed
        resourseListView.setAdapter(adapter);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        resourceRef = database.getReference("resources");

        btnUploadPDF.setOnClickListener(v -> selectPDF());
    }

    private void selectPDF() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri pdfUri = data.getData();
            uploadPDF(pdfUri);
        }
    }

    private void uploadPDF(Uri pdfUri) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Uploading...");
        dialog.show();

        String fileName = "resource_" + System.currentTimeMillis() + ".pdf";
        StorageReference storageRef = storage.getReference().child("resources/" + fileName);

        storageRef.putFile(pdfUri).addOnSuccessListener(taskSnapshot ->
                storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String downloadUrl = uri.toString();
                    resourceRef.push().setValue(downloadUrl).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            resourseList.add(downloadUrl);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Failed to upload to database", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    });
                })
        ).addOnFailureListener(e -> {
            Toast.makeText(this, "Upload Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
    }
}
