package com.example.car_rental_service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDAO extends SQLiteOpenHelper {
    private static final String dbname = "CarRentalService.db";
    public UserDAO( Context context) {
        super(context,dbname , null, 1);
    }

    public void changePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",newPassword);
        db.update("registeredUsers", cv, "username = '"+username+"'", null );
    }
    public Cursor getUserDetails(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{"firstname", "lastname", "username", "password", "role","email", "phone", "address", "city", "state", "zipcode","uta_id", "rental_status", "arlington_auto_club_member"};
        Cursor cursor = db.query("registeredUsers", columns, "username = '"+username+"'", null, null, null, null);
        return cursor;
    }
    public void updateProfile(String username, String firstname, String lastname, String password,String email, String phone, String address, String city, String state, String zipCode, String uta_id, String arlington_member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("firstname",firstname);
        cv.put("lastname",lastname);
        cv.put("password",password);
        cv.put("email",email);
        cv.put("phone",phone);
        cv.put("address",address);
        cv.put("city",city);
        cv.put("state",state);
        cv.put("zipcode",zipCode);
        cv.put("uta_id",uta_id);
        cv.put("arlington_auto_club_member",arlington_member);
        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSs");
        db.update("registeredUsers", cv, "username = '"+username+"'", null );
    }
    public void editUserRole(String username, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("role",role);
        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSs");
        db.update("registeredUsers", cv, "username = '"+username+"'", null );
    }
    public void revokeUser(String username, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rental_status",status);
        System.out.println("======================"+status);
        db.update("registeredUsers", cv, "username = '"+username+"'", null );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor usersummary(String lastname){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{"firstname", "lastname", "username", "email", "phone"};
        Cursor cursor = db.query("registeredUsers", columns, "lastname = '"+lastname+"'", null, null, null, null);
        return cursor;
    }
}
