package com.tw1stedrain.carflipper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tw1stedrain.carflipper.R;
import com.tw1stedrain.carflipper.models.Car;

public class DetailActivity extends AppCompatActivity {

    FirebaseFirestore db;


    TextView nickname;
    TextView make;
    TextView model;
    TextView year;
    TextView costToDate;
    TextView details;

    String carId;
    Car thisCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = FirebaseFirestore.getInstance();


        nickname = findViewById(R.id.detail_nickname);
        make = findViewById(R.id.detail_make);
        model = findViewById(R.id.detail_model);
        year = findViewById(R.id.detail_year);
        costToDate = findViewById(R.id.detail_money_to_date);
        details = findViewById(R.id.detail_car_details);

        Intent intent = getIntent();
        carId = intent.getStringExtra("carId");
        System.out.println("detail carId: " + carId);

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
                            costToDate.setText(String.format("%d", thisCar.getInitialPaid())); //TODO: this will be initial paid + any $$ put into work/parts
                            details.setText(thisCar.getDetails());
                        }
                    }
                });
    }



    // Nav buttons
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addWork(View view) {
        Intent intent = new Intent(this, AddWorkActivity.class);
        startActivity(intent);
    }

    public void updateCar(View view) {
        Intent intent = new Intent(this, UpdateCarActivity.class);
        startActivity(intent);
    }
}
