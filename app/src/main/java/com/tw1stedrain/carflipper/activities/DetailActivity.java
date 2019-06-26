package com.tw1stedrain.carflipper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tw1stedrain.carflipper.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

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
