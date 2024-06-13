package com.example.authenticationexercisesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBAdmin extends SQLiteOpenHelper {
    public DBAdmin(@Nullable Context context) {
        super(context, "profile", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users (id Integer primary key, email Text , username Text , password Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    //registration method for Admin user-----registration nga part sa Admin------
    public Boolean register(String eml, String uname, String pword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conValues = new ContentValues();
        conValues.put("email", eml);
        conValues.put("username", uname);
        conValues.put("password", pword);

        long result = db.insert("users", null, conValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //identifying or validation of username and email of the Admin User---during registration ni siya sa Admin------
    public Boolean userExist(String uname, String eml) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from users where username = ? and email = ?", new String[]{uname, eml});

        if (cur.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    //Identifying or validation of username and email for Admin_User only---during Login ni siya sa Admin------
    public Boolean user_admin_Login(String eml, String pword) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from users where email = ? and password = ?", new String[]{eml, pword});

        if (cur.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    //made an arryList method to pass all data from database into a set of arrays so I can access all of it freely using loops.----sa pag display na nis users-----
    public ArrayList<ModalAdmin> fetchAdmin(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select *  from users", null);
        ArrayList<ModalAdmin> arrayList = new ArrayList<>();

        while (cur.moveToNext()){
            ModalAdmin modalAdmin = new ModalAdmin();
            modalAdmin.id = cur.getInt(0);
            modalAdmin.Ademail = cur.getString(1);
            modalAdmin.Aduser = cur.getString(2);
            modalAdmin.Adpass = cur.getString(3);

            arrayList.add(modalAdmin);

        }
        return arrayList;
    }


}