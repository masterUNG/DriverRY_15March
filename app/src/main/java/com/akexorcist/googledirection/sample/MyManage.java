package com.akexorcist.googledirection.sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 3/17/2017 AD.
 */

public class MyManage {

    private Context context;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {
        this.context = context;

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }

    public long addUser(String strUser, String strPassword) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("User", strUser);
        contentValues.put("Password", strPassword);

        return sqLiteDatabase.insert("userTABLE", null, contentValues);
    }

}   // Main Class
