package com.example.authenticationexercisesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBQuantity extends SQLiteOpenHelper {
    public DBQuantity(@Nullable Context context) {
        super(context, "Quantity", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Quantity(id Integer primary key, quantityOldVal Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("drop Table if exists Quantity");
    }

    public Boolean addQuantity(int quantz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues conValues = new ContentValues();
        conValues.put("quantityOldVal", quantz);

        long result = db.insert("Quantity", null,conValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }

    }
    public Cursor oldQuantity(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Quantity", null);
        return cursor;
    }

   public ArrayList<ModalGuest> fetchQuant(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select *  from Quantity", null);
        ArrayList<ModalGuest> arrayList = new ArrayList<>();

        while (cur.moveToNext()){
            ModalGuest modalGuest = new ModalGuest();
            modalGuest.Quantity = cur.getInt(1);


            arrayList.add(modalGuest);

        }
        return arrayList;
    }
}
