package com.example.car_rental_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Map sessionMap = sharedpreferences.getAll();
        if (sessionMap == null || sessionMap.isEmpty()) {
            startActivity(new Intent(this,MainActivity.class));
            return;
        }
        UserDAO db = new UserDAO(this);
        Cursor cursor = db.getUserDetails((sessionMap.get("username")).toString());

        while(cursor.moveToNext()) {
            ((EditText) findViewById(R.id.firstnameText)).setText(cursor.getString(cursor.getColumnIndex("firstname")));
            ((EditText) findViewById(R.id.lastnameText)).setText(cursor.getString(cursor.getColumnIndex("lastname")));
            ((TextView) findViewById(R.id.usernameText)).setText(cursor.getString(cursor.getColumnIndex("username")));
            ((TextView) findViewById(R.id.usertypeText)).setText(cursor.getString(cursor.getColumnIndex("role")));
            ((EditText) findViewById(R.id.emailText)).setText(cursor.getString(cursor.getColumnIndex("email")));
            ((EditText) findViewById(R.id.phoneText)).setText(cursor.getString(cursor.getColumnIndex("phone")));
            ((EditText) findViewById(R.id.streetaddressText)).setText(cursor.getString(cursor.getColumnIndex("address")));
            ((EditText) findViewById(R.id.cityText)).setText(cursor.getString(cursor.getColumnIndex("city")));
            ((EditText) findViewById(R.id.stateText)).setText(cursor.getString(cursor.getColumnIndex("state")));
            ((EditText) findViewById(R.id.zipcodeText)).setText(cursor.getString(cursor.getColumnIndex("zipcode")));
            ((EditText) findViewById(R.id.utaIDText)).setText(cursor.getString(cursor.getColumnIndex("uta_id")));
            ((TextView) findViewById(R.id.StatusText)).setText(cursor.getString(cursor.getColumnIndex("rental_status")));
            ((EditText) findViewById(R.id.ArlingtonClubText)).setText(cursor.getString(cursor.getColumnIndex("arlington_auto_club_member")));
            ((EditText) findViewById(R.id.passwordText)).setText(cursor.getString(cursor.getColumnIndex("password")));
        }
    }
    public void update(View view) {
        String firstNameText = ((EditText) findViewById(R.id.firstnameText)).getText().toString().trim();
        String lastNameText = ((EditText) findViewById(R.id.lastnameText)).getText().toString().trim();
        String emailText = ((EditText) findViewById(R.id.emailText)).getText().toString().trim();
        String phoneText = ((EditText) findViewById(R.id.phoneText)).getText().toString().trim();
        String addressText = ((EditText) findViewById(R.id.streetaddressText)).getText().toString().trim();
        String cityText = ((EditText) findViewById(R.id.cityText)).getText().toString().trim();
        String stateText = ((EditText) findViewById(R.id.stateText)).getText().toString().trim();
        String zipcodeText = ((EditText) findViewById(R.id.zipcodeText)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.passwordText)).getText().toString().trim();
        String arlingtonClub = ((EditText) findViewById(R.id.ArlingtonClubText)).getText().toString().trim();
        String uta_id = ((EditText) findViewById(R.id.utaIDText)).getText().toString().trim();
        UserDAO db = new UserDAO(this);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Map sessionMap = sharedpreferences.getAll();
        db.updateProfile((sessionMap.get("username")).toString(), firstNameText, lastNameText,password,emailText,phoneText, addressText, cityText, stateText, zipcodeText, uta_id, arlingtonClub);

        Toast.makeText(getApplicationContext(), "Modification Successfull!!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ViewProfile.class));
    }
}