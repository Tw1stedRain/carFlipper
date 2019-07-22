package com.tw1stedrain.carflipper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tw1stedrain.carflipper.R;
import com.tw1stedrain.carflipper.models.Part;

public class AddPartActivity extends AppCompatActivity {

    FirebaseFirestore db;

    EditText partName;
    EditText partNum;
    EditText partCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        db = FirebaseFirestore.getInstance();


    }

    public void newPart(View view) {

        partName = findViewById(R.id.add_part_name);
        partNum = findViewById(R.id.add_part_num);
        partCost = findViewById(R.id.add_part_cost);

        Part part = new Part();
        part.setPartName(partName.getText().toString());
        part.setPartNum(partNum.getText().toString());
        part.setAmountPaid(Integer.parseInt(partCost.getText().toString()));

        db.collection("parts")
                .add(part)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("PART", "added ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("CAR", "failed to save to db");
            }
        });
    }

    public void nvm(View view) {
        Intent intent = new Intent(this, AddWorkActivity.class);
        startActivity(intent);
    }
}
