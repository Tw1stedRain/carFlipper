package com.tw1stedrain.carflipper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tw1stedrain.carflipper.R;
import com.tw1stedrain.carflipper.models.Work;

public class AddWorkActivity extends AppCompatActivity {

    FirebaseFirestore db;

    EditText addName;
    EditText addTime;
    EditText addDetails;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        db = FirebaseFirestore.getInstance();

    }

    public void addWorkClick(View view) {
        addName = findViewById(R.id.add_work_name);
        addTime = findViewById(R.id.add_work_time);
        addName = findViewById(R.id.add_work_details);

        Work work = new Work();
        work.setName(addName.getText().toString());
        work.setTime(Long.parseLong(addTime.getText().toString()));
        work.setDetails(addDetails.getText().toString());

        db.collection("work")
                .add(work)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("WORK", "added ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("WORK", "failed to save to database");
                    }
                });
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addPart(View view) {
        Intent intent = new Intent(this, AddPartActivity.class);
        startActivity(intent);
    }
}
