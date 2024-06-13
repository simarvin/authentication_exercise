package com.example.authenticationexercisesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Login {

    EditText login_email, login_password;
    Button login_btn;
    TextView logto_home, logto_reg;
    DBAdmin mydb;
    DBGuest mydbguest;

        public static final String Extra_Email = "email";
    public static final String Extra_Username = "username";
    @Override
    protected void onCreate(Bundle SaveInstanceState) {

        super.onCreate(SaveInstanceState);
        setContentView(R.layout.login_activity);

        logto_home = (TextView) findViewById(R.id.LogtoHome);
        logto_reg = (TextView) findViewById(R.id.LogtoReg);

        login_email = (EditText) findViewById(R.id.loginEmail);
        login_password =(EditText) findViewById(R.id.loginPassword);

        login_btn = (Button)findViewById(R.id.loginbtn);

          mydb = new DBAdmin(this);
        mydbguest = new DBGuest(this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = login_email.getText().toString();
                String password = login_password.getText().toString();
                String username;

                if(email.equals("") || password.equals("")){
                    Toast.makeText(Login.this, "Please Provide Email and Password to Login", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean check_Admin_Login = mydb.user_admin_Login(email, password);
                    Boolean check_Guest_Login = mydbguest.user_guest_Login(email, password);
                    if(check_Admin_Login){

                        ArrayList<ModalAdmin> data = mydb.fetchAdmin();
                        for (int x=0; x< data.size(); x++) {
                            Log.d("Admins email", " " + data.get(x).Ademail);
                            if(data.get(x).Ademail.equals(email)){
                                username = data.get(x).Aduser.toUpperCase() ;
                                Intent in = new Intent(Login.this, Admin_User.class);
                                in.putExtra(Extra_Username, username);
                                in.putExtra(Extra_Email, email);
                                startActivity(in);
                                Toast.makeText(Login.this, "You have Successfully Loged-in as Admin", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }
                    else if(check_Guest_Login) {

                        ArrayList<ModalGuest> data = mydbguest.fetchGuest();
                        for (int y=0; y< data.size(); y++){
                            Log.d("Guests email", " " + data.get(y).Guestemail);
                            if (data.get(y).Guestemail.equals(email)){
                                username=data.get(y).Guestuser.toUpperCase();
                                Intent in = new Intent(Login.this, Guest_User.class);
                                in.putExtra(Extra_Username, username);
                                in.putExtra(Extra_Email, email);
                                startActivity(in);
                                Toast.makeText(Login.this, "You have Successfully Loged-in as Guest", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else {
                        Toast.makeText(Login.this, "Invalid Credentials, Please Register First", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Login To home activity
        logto_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login.this, Home.class);
                startActivity(in);
            }
        });

        // Login To register activity
        logto_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login.this, Register.class);
                startActivity(in);
            }
        });



    }
}
