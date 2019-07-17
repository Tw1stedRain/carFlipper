package com.tw1stedrain.carflipper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tw1stedrain.carflipper.R;
import com.tw1stedrain.carflipper.models.Car;

public class UpdateCarActivity extends AppCompatActivity {

    FirebaseFirestore db;

    EditText nickname;
    EditText make;
    EditText model;
    EditText year;
    EditText initialCost;
    EditText details;
    String carId;
    Car thisCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car);

        db = FirebaseFirestore.getInstance();

        nickname= findViewById(R.id.add_car_nickname);
        make= findViewById(R.id.add_car_make);
        model= findViewById(R.id.add_car_model);
        year= findViewById(R.id.add_car_year);
        initialCost= findViewById(R.id.add_car_initial_money);
        details= findViewById(R.id.add_car_details);

        Intent intent = getIntent();
        carId = intent.getStringExtra("carId");

        getCar();
    }

    public void getCar() {
        db.collection("cars").document(carId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot snap = task.getResult();

                            thisCar = snap.toObject(Car.class);
                            thisCar.setId(snap.getId());

                            nickname.setText(thisCar.getNickname());
                            make.setText(thisCar.getMake());
                            model.setText(thisCar.getModel());
                            year.setText(String.format("%d", thisCar.getYear()));
                            initialCost.setText(String.format("%d", thisCar.getInitialPaid())); //TODO: this will be initial paid + any $$ put into work/parts
                            details.setText(thisCar.getDetails());                        }
                    }
                });
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
