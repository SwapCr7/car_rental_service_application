package com.example.car_rental_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Map;

public class AdminViewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_profile);
        UserDAO db = new UserDAO(this);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Map sessionMap = sharedpreferences.getAll();
        Cursor cursor = db.getUserDetails((sessionMap.get("username")).toString());
        while(cursor.moveToNext()) {
            ((EditText) findViewById(R.id.firstnameText)).setText(cursor.getString(cursor.getColumnIndex("firstname")));
            ((EditText) findViewById(R.id.lastnameText)).setText(cursor.getString(cursor.getColumnIndex("lastname")));
            ((EditText) findViewById(R.id.usernameText)).setText(cursor.getString(cursor.getColumnIndex("username")));
            ((EditText) findViewById(R.id.usertypeText)).setText(cursor.getString(cursor.getColumnIndex("role")));
            ((EditText) findViewById(R.id.emailText)).setText(cursor.getString(cursor.getColumnIndex("email")));
            ((EditText) findViewById(R.id.phoneText)).setText(cursor.getString(cursor.getColumnIndex("phone")));
            ((EditText) findViewById(R.id.streetaddressText)).setText(cursor.getString(cursor.getColumnIndex("address")));
            ((EditText) findViewById(R.id.cityText)).setText(cursor.getString(cursor.getColumnIndex("city")));
            ((EditText) findViewById(R.id.stateText)).setText(cursor.getString(cursor.getColumnIndex("state")));
            ((EditText) findViewById(R.id.zipcodeText)).setText(cursor.getString(cursor.getColumnIndex("zipcode")));
            ((EditText) findViewById(R.id.utaidText)).setText(cursor.getString(cursor.getColumnIndex("uta_id")));
            ((EditText) findViewById(R.id.rentalstatusText)).setText(cursor.getString(cursor.getColumnIndex("rental_status")));
            ((EditText) findViewById(R.id.arlingtonClubText)).setText(cursor.getString(cursor.getColumnIndex("arlington_auto_club_member")));
        }
    }
}
