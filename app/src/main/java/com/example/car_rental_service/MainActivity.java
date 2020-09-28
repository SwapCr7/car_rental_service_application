package com.example.car_rental_service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

public class MainActivity extends AppCompatActivity {

    /*TODO Need to make fields Mandatory*/
    TextView textView;
    EditText username, password;
    SharedPreferences sharedpreferences;
    EditText edtPassword;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        textView = findViewById(R.id.userText);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        edtPassword = findViewById(R.id.pwdText);
      //  DBManager db = new DBManager(this);
   //     String result = db.addRecord("tanishk", "khare","tanishk123","hello","admin","tanishkkhare@gmail.com","6822944658","409 summit avenue","Arlington","Texas","76013","1001773469","active","no");
    }
    public void checkValidUser(View view) {
        username = findViewById(R.id.userText);
        password = findViewById(R.id.pwdText);
        if (username.getText().toString().trim().length() != 0 && password.getText().toString().trim().length() != 0) {
            SQLiteDatabase sqldb = this.openOrCreateDatabase("CarRentalService.db", MODE_PRIVATE, null);
            Cursor cursor = sqldb.rawQuery("select name FROM sqlite_master WHERE type='table' AND name='registeredUsers'", null);
            if (cursor.getCount() > 0) {
                String query = "Select * from registeredUsers where username = '" + username.getText().toString().trim() + "' and password = '" + password.getText().toString().trim() + "'";
                cursor = sqldb.rawQuery(query, null);
                if (cursor.getCount() <= 0) {
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                    cursor.close();
                } else {
                    String data = "user";
                    if (cursor.moveToFirst()) {
                        data = cursor.getString(cursor.getColumnIndex("role"));
                    }
                    cursor.close();
                    SharedPreferences.Editor session = sharedpreferences.edit();
                    session.putString("username", username.getText().toString().trim());
                    session.putString("role", data);
                    session.commit();
                    if (data.equals("manager")) {
                        startActivity(new Intent(this, ManagerHomeScreen.class));
                    } else if (data.equals("admin")) {
                        startActivity(new Intent(this, AdminHomeScreen.class));
                    } else
                        startActivity(new Intent(this, UserHomeScreen.class));
                    username.setText("");
                    password.setText("");
                }
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                username.setText("");
                password.setText("");
                cursor.close();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Enter required fields", Toast.LENGTH_SHORT).show();
            username.setText("");
            password.setText("");
        }
    }
}
