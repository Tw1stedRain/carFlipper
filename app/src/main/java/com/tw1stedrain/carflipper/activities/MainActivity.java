package com.tw1stedrain.carflipper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tw1stedrain.carflipper.R;
import com.tw1stedrain.carflipper.models.Car;
import com.tw1stedrain.carflipper.recyclerViewNeeds.CarLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CarLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();


        // recycler view
        List<Car> empty = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_cars);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CarLayoutAdapter(empty);
        recyclerView.setAdapter(adapter);

        onFillClick();
    }

    // retrieve info from db, fill recycler
    public void onFillClick() {
        db.collection("cars")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot snap = task.getResult();
                            List<Car> cars = new ArrayList<>();
                            for (DocumentSnapshot doc : snap.getDocuments()) {

                                Car car = doc.toObject(Car.class);
                                car.setId(doc.getId());
                                cars.add(car);
                            }
                            adapter.setCars(cars);
                        }
                    }
                });
    }

    public void addCar(View view) {
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }
}
