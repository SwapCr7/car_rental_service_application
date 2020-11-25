package com.example.car_rental_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSummaryList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_summary_list);
        final ListView list = (ListView) findViewById(R.id.listView1);
        UserDAO db = new UserDAO(this);
        String lastname = getIntent().getStringExtra("lastname");
        Cursor cursor = db.usersummary(lastname);
        final List<String> listItemNames = new ArrayList<>();
        final Map<String, String> map = new HashMap<>();
        if(cursor.getCount() == 0){
            list.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()) {
//                listItem.add(cursor.getString(cursor.getColumnIndex("username")));


                listItemNames.add(cursor.getString(cursor.getColumnIndex("username")) + " " + cursor.getString(cursor.getColumnIndex("firstname")) + " " + cursor.getString(cursor.getColumnIndex("lastname")) + " " + cursor.getString(cursor.getColumnIndex("email")) + " " + cursor.getString(cursor.getColumnIndex("phone")));
                map.put(cursor.getString(cursor.getColumnIndex("username")) + " " + cursor.getString(cursor.getColumnIndex("firstname")) + " " + cursor.getString(cursor.getColumnIndex("lastname")) + " " + cursor.getString(cursor.getColumnIndex("email")) + " " + cursor.getString(cursor.getColumnIndex("phone")), cursor.getString(cursor.getColumnIndex("username")));

            }
            Collections.sort(listItemNames);
            final ListAdapter myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItemNames);

            list.setAdapter(myadapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                    String name = map.get((String) list.getAdapter().getItem(position));
                    Intent intent = new Intent(UserSummaryList.this, UserDetails.class);
                    intent.putExtra("username", name);
                    startActivity(intent);
                }
            });
        }
    }
}