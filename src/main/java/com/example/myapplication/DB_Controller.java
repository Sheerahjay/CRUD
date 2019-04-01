package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_Controller extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "ITEMS.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE ITEMS(ID INTEGER PRIMARY KEY, NAME TEXT UNIQUE, AMOUNT INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ITEMS;");
        onCreate(db);
    }

    public  void  add (String Id,String name,String amount ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",Id);
        contentValues.put("Name",name);
        contentValues.put("Amount",amount);
        this.getWritableDatabase().insertOrThrow("ITEMS","",contentValues);

    }
    public void delete(String name){
        this.getWritableDatabase().delete("ITEMS","NAME='"+name+"'",null);

    }
    public void update(String old_name,String new_name){
        this.getWritableDatabase().execSQL("UPDATE ITEMS SET NAME='"+new_name+"' WHERE NAME='"+old_name+"'");

    }
    public void list (TextView listitem){
        Cursor cl =this.getReadableDatabase().rawQuery("SELECT * FROM ITEMS",null);
        listitem.setText("");
        while (cl.moveToNext()){
            listitem.append(cl.getString(0)+" "+cl.getString(1)+" "+cl.getString(2)+"\n");
        }
    }
}
