package com.egos.samples.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Egos on 2016/12/27.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "egos.db";
    public static final int DB_VERSION = 1;

    private MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static MyDatabaseHelper mInstance;

    public static MyDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MyDatabaseHelper.class) {
                if (mInstance == null) {
                    mInstance = new MyDatabaseHelper(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 初次安装，所有的表需要
        StudentDatabase.createDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            // 部分数据库更新
            StudentDatabase.upgradeDatabase(db, oldVersion, newVersion);
        } else {
            // 降级安装需要把数据库删掉，这种情况大部分出现在Root手机
            StudentDatabase.dropDatabase(db);
        }
    }
}