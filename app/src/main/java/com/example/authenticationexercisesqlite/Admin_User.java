package com.example.authenticationexercisesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_User {

    TextView admin_username, admin_email;
    Button admin_out_button, add_productBtn;
    EditText product_name, product_quantity, product_description;
    DBItems dbItems;
    public static final String Extra_Quantity= "prdct_quant";
    @Override
    protected void onCreate(Bundle SaveInstanceState) {

        super.onCreate(SaveInstanceState);
        setContentView(R.layout.admin_user_activity);

        admin_username = (TextView) findViewById(R.id.Admin_Uname);
        admin_email = (TextView) findViewById(R.id.Admin_Gmail);
        admin_out_button = (Button) findViewById(R.id.Admin_Logout);
        product_name = (EditText) findViewById(R.id.Enter_ProductName);
        product_quantity = (EditText) findViewById(R.id.Enter_ProductQuantity);
        product_description = (EditText) findViewById(R.id.Enter_ProductDescription);
        add_productBtn = (Button) findViewById (R.id.Add_ProductBtn);

        dbItems= new DBItems(this);

        Intent in = getIntent();
        String new_email = in.getStringExtra(Login.Extra_Email);
        String new_username = in.getStringExtra(Login.Extra_Username);

        admin_username.setText("WELCOME " + new_username);
        admin_email.setText(new_email + "");

      add_productBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String prdct_name = product_name.getText().toString();
              String prdct_quant = product_quantity.getText().toString();
              String prdct_descrptn = product_description.getText().toString();
              if (prdct_name.equals("") || prdct_quant.equals("") || prdct_quant.equals("0") || prdct_descrptn.equals("")){
                  Toast.makeText(Admin_User.this, "Please Fill all Fields to add Product", Toast.LENGTH_SHORT).show();
              }else{
                  int quantity = Integer.parseInt(product_quantity.getText().toString());
                  Boolean insert = dbItems.Iadd(prdct_name,quantity,prdct_descrptn);
                  if (insert){
                      Toast.makeText(Admin_User.this, "Product Added Successfully", Toast.LENGTH_SHORT).show();

                      product_name.setText("");
                      product_quantity.setText("");
                      product_description.setText("");
                  }else{
                      Toast.makeText(Admin_User.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                  }
              }



          }
      });




        admin_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_User.this, Home.class);
                startActivity(in);
            }
        });


    }
}