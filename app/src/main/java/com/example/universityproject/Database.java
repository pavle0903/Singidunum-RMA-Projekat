package com.example.universityproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="baza.sqlite";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    //SQLiteDatabase db;



    @Override
    public void onCreate(SQLiteDatabase db) {
        //kreiranje baze
        //db.execSQL(String.format("DROP TABLE IF EXISTS %s;", Users.TABLE_NAME));

        String SQL = String.format("CREATE TABLE IF NOT EXISTS %s (%s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT);",
                Users.TABLE_NAME, Users.FIEDL_JMBG, Users.FIELD_USERNAME, Users.FIELD_PASSWORD, Users.FIELD_PRED_GLASAO, Users.FIELD_PARL_GLASAO);

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

    public void update_predsednicke(ContentValues cv, String jmbg){

        SQLiteDatabase db = this.getWritableDatabase();
        db.update("users", cv,  "jmbg =" + jmbg,null);
//        System.out.println(jmbg+ "ovo je jmbggg");
//        int novi = 1;
//        String strSQL = "UPDATE users SET pred_glasao = novi WHERE jmbg = "+ jmbg;
//        db.execSQL(strSQL);
    }

//    public void get_predsednicke(String jmbg){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String SQL = String.format("SELECT * FROM %s WHERE jmbg = %s ", Users.FIELD_PRED_GLASAO, jmbg);
//        Cursor result = db.rawQuery(SQL, new String[]{jmbg});
//
//        System.out.println(result + "reeeeeeeeeultat");
//    }
}
