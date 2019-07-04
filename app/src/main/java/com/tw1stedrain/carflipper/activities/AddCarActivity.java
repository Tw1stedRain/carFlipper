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
import com.tw1stedrain.carflipper.models.Car;

public class AddCarActivity extends AppCompatActivity {

    FirebaseFirestore db;

    EditText addNickname;
    EditText addMake;
    EditText addModel;
    EditText addYear;
    EditText addInitialCost;
    EditText addDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        db = FirebaseFirestore.getInstance();
    }

    public void newCarClick(View view) {
        addNickname = findViewById(R.id.add_car_nickname);
        addMake = findViewById(R.id.add_car_make);
        addModel = findViewById(R.id.add_car_model);
        addYear = findViewById(R.id.add_car_year);
        addInitialCost = findViewById(R.id.add_car_initial_money);
        addDetails = findViewById(R.id.add_car_details);

        Car car = new Car();
        car.setNickname(addNickname.getText().toString());
        car.setMake(addMake.getText().toString());
        car.setModel(addModel.getText().toString());
        car.setYear(Integer.parseInt(addYear.getText().toString()));
        car.setInitialPaid(Integer.parseInt(addInitialCost.getText().toString()));
        car.setDetails(addDetails.getText().toString());

        db.collection("cars")
                .add(car)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("CAR", "added ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("CAR", "failed to save to database");
                    }
                });
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
