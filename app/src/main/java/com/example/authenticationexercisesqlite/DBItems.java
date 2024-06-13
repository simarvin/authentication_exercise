package com.example.authenticationexercisesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBItems extends SQLiteOpenHelper {
    private static final String quantity = "quantz";
    public DBItems(@Nullable Context context) {super(context, "productItem", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table productItem(id Integer primary key, product_name Text, quantity Integer, description Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists productItem");
    }

    public Boolean Iadd(String pname, Integer pquan, String pdescrptn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contV = new ContentValues();

        contV.put("product_name",pname);
        contV.put("quantity", pquan);
        contV.put("description",pdescrptn);

        long result = db.insert("productItem", null, contV);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor IgetListContents(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from productItem", null);
        return cur;
    }
    public ArrayList<ModalGuest> Iquants(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from productItem", null);
        ArrayList<ModalGuest> arrayList = new ArrayList<>();

        while (cur.moveToNext()){
            ModalGuest modalGuest = new ModalGuest();
            modalGuest.qntity = cur.getInt(2);


            arrayList.add(modalGuest);

        }
        return arrayList;
    }

    public int addValues(){
        SQLiteDatabase db = this.getReadableDatabase();
         int total = 0;
            Cursor cur = db.rawQuery("select sum quantity from productItem", null);
            if(cur.moveToFirst()){
            total = cur.getInt(0);
            }
            return total;
        }

        }
