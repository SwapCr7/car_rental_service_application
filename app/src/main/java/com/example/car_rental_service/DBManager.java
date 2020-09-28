package com.example.car_rental_service;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DBManager extends SQLiteOpenHelper
{

    private static final String dbname = "CarRentalService.db";
    public DBManager( Context context) {
        super(context,dbname , null, 1);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onCreate(SQLiteDatabase db) {


        String qry = "create table registeredUsers(firstname text, " +
                "lastname text, username text primary key,password text,role text,email text,phone text,address text," +
                "city text,state text,zipcode text,uta_id text, rental_status text,arlington_auto_club_member text)";
        db.execSQL(qry);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS registeredUsers");
        onCreate(db);
    }

    public String addRecord(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12,String p13,String p14)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("firstname",p1);
        cv.put("lastname",p2);
        cv.put("username",p3);
        cv.put("password",p4);
        cv.put("role",p5);
        cv.put("email",p6);
        cv.put("phone",p7);
        cv.put("address",p8);
        cv.put("city",p9);
        cv.put("state",p10);
        cv.put("zipcode",p11);
        cv.put("uta_id",p12);
        cv.put("rental_status",p13);
        cv.put("arlington_auto_club_member",p14);


        // put remainder of data stored here

        long res = db.insert("registeredUsers", null,cv );
        if(res== -1)
            return "failed";
        else
            return "Account Created Successfully";

    }
}
