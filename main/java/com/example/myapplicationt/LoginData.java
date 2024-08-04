package com.example.myapplicationt;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginData extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;

    public LoginData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL UNIQUE, password TEXT NOT NULL)");

        // Insert initial data
        db.execSQL("INSERT INTO users (username, password) VALUES ('Nickson Kipruto', 'password1')");
        db.execSQL("INSERT INTO users (username, password) VALUES ('Getrude', 'password2')");
        db.execSQL("INSERT INTO users (username, password) VALUES ('Joseph Mundia', 'password3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists and recreate it
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // Validate user credentials
    public Cursor validate(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
    }
}
