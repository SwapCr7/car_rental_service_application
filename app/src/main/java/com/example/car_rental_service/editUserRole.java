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

public class editUserRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_role);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Map sessionMap = sharedpreferences.getAll();
        if (sessionMap == null || sessionMap.isEmpty()) {
            startActivity(new Intent(this,MainActivity.class));
            return;
        }

        UserDAO db = new UserDAO(this);
        Cursor cursor = db.getUserDetails(getIntent().getStringExtra("username"));

        while(cursor.moveToNext()) {
            ((EditText) findViewById(R.id.firstnameText)).setText(cursor.getString(cursor.getColumnIndex("firstname")));
            ((EditText) findViewById(R.id.lastnameText)).setText(cursor.getString(cursor.getColumnIndex("lastname")));
            ((TextView) findViewById(R.id.usernameText)).setText(cursor.getString(cursor.getColumnIndex("username")));
            ((EditText) findViewById(R.id.usertypeText)).setText(cursor.getString(cursor.getColumnIndex("role")));
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
    public void editUserRoleAdmin(View view) {
        String role = ((EditText) findViewById(R.id.usertypeText)).getText().toString().trim();
        UserDAO db = new UserDAO(this);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Map sessionMap = sharedpreferences.getAll();
        db.editUserRole(getIntent().getStringExtra("username"), role);

        Toast.makeText(getApplicationContext(), "Modification Successfull!!!", Toast.LENGTH_SHORT).show();
        TextView username = findViewById(R.id.usernameText);
        Intent intent = new Intent(editUserRole.this, UserDetails.class);
        intent.putExtra("username", username.getText().toString());
        startActivityForResult(intent, 0);
    }

}