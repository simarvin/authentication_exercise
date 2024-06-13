package com.example.authenticationexercisesqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Guest_User {

    TextView guest_username, guest_email, total_product, total_quantity;
    Button guest_logout;
    ListView lists;
    DBItems dbItems;
    DBQuantity dbQuantity;
    @Override
    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.guest_user_activity);

        guest_email = (TextView) findViewById(R.id.Guest_Email);
        guest_username = (TextView) findViewById(R.id.Guest_Username);
        guest_logout = (Button) findViewById(R.id.Guest_Logout);
        total_product = (TextView) findViewById(R.id.TotalProduct);
        total_quantity = (TextView) findViewById(R.id.TotalQuantity);
        lists = (ListView) findViewById(R.id.listOfProducts);

             dbItems = new DBItems(this);
             dbQuantity = new DBQuantity(this);
        Intent in = getIntent();
        String new_email = in.getStringExtra(Login.Extra_Email);
        String new_username = in.getStringExtra(Login.Extra_Username);

        guest_username.setText("WELCOME " + new_username);
        guest_email.setText(new_email + "");

        guest_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Guest_User.this, Home.class);
                startActivity(in);
            }
        });





        ArrayList<String> theList = new ArrayList<>();
        Cursor cur = dbItems.IgetListContents();
        Cursor cursor = dbQuantity.oldQuantity();



        if (cur.getCount() == 0){
            Toast.makeText(Guest_User.this, "No List of Products", Toast.LENGTH_SHORT).show();
        }else {
            while (cur.moveToNext() ) {

                    total_product.setText("Total # of Product: " + cur.getInt(0) + "");

                    theList.add(cur.getString(1) + " - " + cur.getString(2) + "pc" + "\n" + cur.getString(3));
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                    lists.setAdapter(listAdapter);

            }




        }


        }

    }

