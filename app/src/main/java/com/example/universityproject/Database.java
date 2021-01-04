package com.example.universityproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="baza.sqlite";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //kreiranje baze
        //db.execSQL(String.format("DROP TABLE IF EXISTS %s;", Users.TABLE_NAME));

        String SQL = String.format("CREATE TABLE IF NOT EXISTS %s (%s TEXT PRIMARY KEY, %s TEXT, %s TEXT);",
                Users.TABLE_NAME, Users.FIEDL_JMBG, Users.FIELD_USERNAME, Users.FIELD_PASSWORD);

        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //implementacija promene baze iz jedne verzije u drugu
        //ovde bi trebalo implementirati tranziciju
        db.execSQL(String.format("DROP TABLE IF EXISTS %s;", Users.TABLE_NAME));
        //kreiranje nove tabele
        onCreate(db);
        System.out.println("ovo je on upgrade");
    }
}
