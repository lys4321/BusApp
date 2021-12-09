package com.example.busstopapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, @Nullable int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                        "CREATE TABLE IF NOT EXISTS User(" +
                        "userId varchar(20) PRIMARY KEY ," +
                        "userPw varchar(20) )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                "DROP TABLE IF EXISTS User"
        );
        onCreate(db);
    }

    public void onInsert(SQLiteDatabase db, String userId, String userPw){
        db.execSQL(
                "INSERT INTO User VALUES(" + userId + "," + userPw + ")"
        );
    }

    public int onSelect(SQLiteDatabase db, String userId) { //조건없는 유저 탐색
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM User", null);

        while(cursor.moveToNext()) {
            String id = cursor.getString(0);

            if(id == userId){
                return 1;
            }
        }

        return 0;
    }
}
