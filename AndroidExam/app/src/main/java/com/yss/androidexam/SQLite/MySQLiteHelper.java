package com.yss.androidexam.SQLite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/11.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public MySQLiteHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version,
                          DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public MySQLiteHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table diary (_id Integer primary key AUTOINCREMENT,topic varchar(100),content varchar(1000))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            oldVersion=newVersion;
            db.execSQL("drop table diary");
            db.execSQL("create table diary (_id Integer primary key AUTOINCREMENT,topic varchar(100),content varchar(1000))");
    }
}
