package com.example.authenticationexercisesqlite;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register {
       Spinner user_type;
       Button register_btn;
       EditText register_email, register_username, register_password;
       TextView regto_home, regto_log;
       DBAdmin mydb;
       DBGuest mydbguest;
    @Override
    protected void onCreate(Bundle SaveInstanceState) {

        super.onCreate(SaveInstanceState);
        setContentView(R.layout.register_activity);

        regto_home = (TextView) findViewById(R.id.RegtoHome);
        regto_log = (TextView) findViewById(R.id.RegtoLog);

        user_type = (Spinner) findViewById(R.id.spinner);
       register_email = (EditText)findViewById(R.id.RegEmail);
       register_username = (EditText) findViewById(R.id.RegUsername);
       register_password = (EditText) findViewById(R.id.RegPassword);

       register_btn = (Button) findViewById(R.id.registerbtn);

       mydb = new DBAdmin(this);
        mydbguest = new DBGuest(this);
       type_of_users();

       register_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           String email = register_email.getText().toString();
           String username = register_username.getText().toString();
           String password = register_password.getText().toString();
           if (!email.contains("@")){
                   register_email.setError("Invalid Email");}
           else if (email.equals("") || username.equals("") || password.equals("")){
               Toast.makeText(Register.this, "Please Fill All the Necessary Data", Toast.LENGTH_SHORT).show();

           }else{

              Boolean userCheck = mydb.userExist(username,email);
               Boolean userCheck2 = mydbguest.userExist2(username,email);
              if(userCheck){
                  Toast.makeText(Register.this, "User Already Exist, Please Login or Use Another", Toast.LENGTH_SHORT).show();
              }
              else if(userCheck2){
                      Toast.makeText(Register.this, "User Already Exist, Please Login or Use Another", Toast.LENGTH_SHORT).show();
              }else {
                    String spinner = String.valueOf(user_type.getSelectedItem());
                    if (spinner.equals("Admin")) {
                        Boolean insert = mydb.register(email, username, password);
                        if (insert) {
                            Toast.makeText(Register.this, "Registered Successfully as Admin", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else if (spinner.equals("Guest")) {

                        Boolean insert = mydbguest.register2(email, username, password);
                        if (insert) {
                            Toast.makeText(Register.this, "Registered Successfully as Guest", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                      Toast.makeText(Register.this, "Please Choose Between Admin or Guest", Toast.LENGTH_SHORT).show();
                  }

              }
           }

           }
       });




       // register to home activity
       regto_home.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in = new Intent(Register.this, Home.class);
               startActivity(in);
           }
       });
         // register to login activity
        regto_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Register.this, Login.class);
                startActivity(in);
            }
        });

    }
//Spinner Method
    private void type_of_users(){
        ArrayAdapter<String> options = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.users_options));

        options.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_type.setAdapter(options);
    }
}
