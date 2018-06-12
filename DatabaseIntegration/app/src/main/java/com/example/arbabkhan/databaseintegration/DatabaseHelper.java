package com.example.arbabkhan.databaseintegration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DTOs.StudentInfo;

/**
 * Created by ArbabKhan on 10/05/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    //Constructor for database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //MARK: Mandatory override methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate(db);
    }

    public boolean insertData(String strName, String strSurName, String strMarkObtained) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COL_2, strName);
        content.put(COL_3, strSurName);
        content.put(COL_4, Integer.parseInt(strMarkObtained));
        long result = db.insert(TABLE_NAME, null, content);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public List getAllStudentsData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME, null);
        List list_object = this.getStudentsInfo(res);
        return list_object;
    }

    public List getStudentsInfo(Cursor cursor)  {

        List list = new ArrayList();
        while (cursor.moveToNext()) {

            HashMap hashMap = new HashMap();
            hashMap.put("user_id", cursor.getString(0));
            hashMap.put("user_name", cursor.getString(1));
            hashMap.put("sur_name", cursor.getString(2));
            hashMap.put("marks", cursor.getString(3));

            StudentInfo info = new StudentInfo(hashMap);
            list.add(info);
        }

        return list;

    }

}
