package com.example.car_rental_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

public class SearchUsers extends AppCompatActivity {
    EditText lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_users);
    }
    public void user_summary(View view) {
        lastname = findViewById(R.id.lastnameText);
        System.out.println(lastname);
        UserDAO db = new UserDAO(this);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Map sessionMap = sharedpreferences.getAll();
        Intent intent = new Intent(SearchUsers.this, UserSummaryList.class);
        intent.putExtra("lastname", lastname.getText().toString());
        startActivityForResult(intent, 0);
        /*Cursor cursor = db.usersummary(lastname.getText().toString());*/
    }
}