package com.egos.samples.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Egos on 2016/12/27.
 * <p>
 * Demo 学生表
 */
public class StudentDatabase {
    private final static String TABLE = "student";
    private final static String STUDENT_ID = "student_id";
    private final static String STUDENT_NAME = "student_name";
    private final static String STUDENT_AGE = "student_age";
    private final static String STUDENT_SEX = "student_sex";

    private MyDatabaseHelper mDatabaseHelper;

    public StudentDatabase(Context context) {
        mDatabaseHelper = MyDatabaseHelper.getInstance(context);
    }

    public static void createDatabase(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE + "(" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + STUDENT_ID + " VARCHAR(20), " + STUDENT_NAME + " TEXT, " + STUDENT_AGE + " INTEGER)";
        db.execSQL(sql);
    }

    public static void upgradeDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion < 2) {
            // 比如增加性别字段
            String sql = "ALERT TABLE " + TABLE + "ADD COLUMN " + STUDENT_SEX + " TEXT";
            db.execSQL(sql);
        }
    }

    public static void dropDatabase(SQLiteDatabase db) {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        db.execSQL(sql);
    }

    public void insert(String id, String name, int age) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        // db.execSQL();
        String sql = "insert into " + TABLE + "(" + STUDENT_ID + ", " + STUDENT_NAME + ", " + STUDENT_AGE + ") values "
                + "( '" + id + "', '" + name + "', " + age + ")";
        db.execSQL(sql);

        // db.insert();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(STUDENT_ID, id);
//        contentValues.put(STUDENT_NAME, name);
//        contentValues.put(STUDENT_AGE, age);
//        db.insert(TABLE, null, contentValues);
    }

    public void delete(String id) {
        String sql = "delete from " + TABLE + " where ";
    }

    public int query() {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        //        db.query()
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE, null, null, null, null, null, null);
            return cursor != null ? cursor.getCount() : 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}