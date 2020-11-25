package com.example.car_rental_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText FirstNameText,LastNameText,UserNameText,passwordText,EmailText,PhoneText,StreetAddressText,CityText,
            StateText,UtaIdText,ZipCodeText,MemberStatusText;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirstNameText= (EditText) this.<View>findViewById(R.id.FirstNameText);
        LastNameText= (EditText)findViewById(R.id.LastNameText);
        UserNameText= (EditText)findViewById(R.id.UserNameText);
        passwordText= (EditText)findViewById(R.id.passwordText);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        EmailText= (EditText)findViewById(R.id.EmailText);
        PhoneText= (EditText)findViewById(R.id.PhoneText);
        StreetAddressText= (EditText)findViewById(R.id.StreetAddressText);
        CityText= (EditText)findViewById(R.id.CityText);
        StateText= (EditText)findViewById(R.id.StateText);
        ZipCodeText= (EditText)findViewById(R.id.ZipCodeText);
        UtaIdText = (EditText)findViewById(R.id.UtaIdText);
        MemberStatusText = (EditText)findViewById(R.id.MemberStatusText);
    }

    public void register_user(View view){
        DBManager db = new DBManager(this);
        String role = "user";
        String status = "active";
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String result = db.addRecord(FirstNameText.getText().toString(), LastNameText.getText().toString(),
                UserNameText.getText().toString(), passwordText.getText().toString(), radioButton.getText().toString().toLowerCase(),
                EmailText.getText().toString(), PhoneText.getText().toString(), StreetAddressText.getText().toString(),
                CityText.getText().toString(), StateText.getText().toString(), ZipCodeText.getText().toString(),
                UtaIdText.getText().toString(),status, MemberStatusText.getText().toString());
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        if (result.equals("Registration Successfull"))
            startActivity(new Intent(this, MainActivity.class));
        FirstNameText.setText("");
        LastNameText.setText("");
        UserNameText.setText("");
        passwordText.setText("");
        EmailText.setText("");
        PhoneText.setText("");
        StreetAddressText.setText("");
        CityText.setText("");
        StateText.setText("");
        ZipCodeText.setText("");
        UtaIdText.setText("");
        MemberStatusText.setText("");
    }
}