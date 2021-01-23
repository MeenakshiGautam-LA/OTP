package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "OTP";
    private static final String TABLE_MONTH = "month";
    private static DatabaseManager sDatabaseManager = null;


    public static synchronized DatabaseManager getInstance(Context ctx) {
        if (sDatabaseManager == null) {
            sDatabaseManager = new DatabaseManager(ctx.getApplicationContext());
        }
        return sDatabaseManager;
    }

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS '" + TABLE_MONTH + "'(ID INTEGER PRIMARY KEY AUTOINCREMENT," +


                "JSON_OBJECT VARCHAR(65000));");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_MONTH + "'");

    }


    public Long insertData(List<JSONObject> jsonObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("JSON_OBJECT", String.valueOf(jsonObject));
        long result = db.insert(TABLE_MONTH, null, contentValues);
        return result;
    }



}
