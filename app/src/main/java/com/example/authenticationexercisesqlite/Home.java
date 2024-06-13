package com.example.authenticationexercisesqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {

 Button login, register;
    @Override
    protected void onCreate(Bundle SaveInstanceState) {

        super.onCreate(SaveInstanceState);
        setContentView(R.layout.home);

     login = (Button) findViewById(R.id.mainlogin);
     register = (Button)  findViewById(R.id.mainsignup);

     // home to login activity
     login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent in = new Intent(Home.this,Login.class);
             startActivity(in);
         }
     });
        // home to register activity
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Home.this,Register.class);
                startActivity(in);
            }
        });

    }
}
