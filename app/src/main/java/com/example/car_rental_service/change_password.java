package com.example.car_rental_service;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class change_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Map sessionMap = sharedpreferences.getAll();
        if (sessionMap == null || sessionMap.isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void update_password(View view) {
        UserDAO db = new UserDAO(getApplicationContext());
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Map sessionMap = sharedpreferences.getAll();
        String newpassword = ((EditText) findViewById(R.id.newpasswordtext)).getText().toString().trim();
        String username = sessionMap.get("username").toString();
        System.out.println("==============username "+username);
        System.out.println("=======password "+newpassword);
        db.changePassword(username, newpassword);
        SharedPreferences.Editor session = sharedpreferences.edit();
        session.clear();
        session.commit();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Toast.makeText(getApplicationContext(), "Reset Password Successful", Toast.LENGTH_SHORT).show();
    }
}