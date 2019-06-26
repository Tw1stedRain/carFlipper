package com.tw1stedrain.carflipper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tw1stedrain.carflipper.R;

public class AddPartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);
    }

    public void nvm(View view) {
        Intent intent = new Intent(this, AddWorkActivity.class);
        startActivity(intent);
    }
}
