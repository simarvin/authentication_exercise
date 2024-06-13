package com.example.authenticationexercisesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBGuest extends SQLiteOpenHelper {
    public DBGuest(@Nullable Context context) {
        super(context, "profile2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(id Integer primary key, email2 Text , username2 Text , password2 Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }
    //registration method for Guest user-----registration nga part sa Guest------
    public Boolean register2(String eml2, String uname2, String pword2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conValues = new ContentValues();
        conValues.put("email2", eml2);
        conValues.put("username2", uname2);
        conValues.put("password2", pword2);

        long result = db.insert("users", null, conValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    //identifying or validation of username and email of the Guest User---during registration ni siya sa Guest------
    public Boolean userExist2(String uname2, String eml2){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from users where username2 = ? and email2 = ?" , new String[]{uname2, eml2});

        if (cur.getCount()>0){
            return true;
        }else{
            return false;
        }

    }
    //Identifying or validation of username and email for Guest_User only---during Login ni siya sa Guest------
    public Boolean user_guest_Login(String eml2, String pword2){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from users where email2 = ? and password2 = ?", new String[]{eml2 , pword2});

        if (cur.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    //made an arryList method to pass all data from database into a set of arrays so I can access all of it freely using loops.----sa pag display na nis users-----
    public ArrayList<ModalGuest> fetchGuest(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select *  from users", null);
        ArrayList<ModalGuest> arrayList = new ArrayList<>();

        while (cur.moveToNext()){
            ModalGuest modalGuest = new ModalGuest();
            modalGuest.id = cur.getInt(0);
            modalGuest.Guestemail = cur.getString(1);
            modalGuest.Guestuser = cur.getString(2);
            modalGuest.Guestpass = cur.getString(3);

            arrayList.add(modalGuest);

        }
        return arrayList;
    }
}
