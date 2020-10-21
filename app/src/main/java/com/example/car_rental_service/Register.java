package com.example.car_rental_service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void sucessfullregister(View view){
        Toast.makeText(getApplicationContext(), "Registration Sucessfull", Toast.LENGTH_SHORT).show();
    }
}