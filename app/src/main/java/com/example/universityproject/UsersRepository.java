package com.example.universityproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UsersRepository {

    private Database database;

    public UsersRepository(Database db){
        this.database = db;
    }

    public void addUser(String username, String password, String jmbg, String pred_glasao, String parl_glasao){
        SQLiteDatabase db = database.getWritableDatabase();
        //da bismo uneli podatke u bazu, treba nam skup vrednosti, ContentValues
        ContentValues cv = new ContentValues();

        cv.put(Users.FIELD_USERNAME, username);
        cv.put(Users.FIELD_PASSWORD, password);
        cv.put(Users.FIEDL_JMBG, jmbg);
        cv.put(Users.FIELD_PRED_GLASAO, pred_glasao);
        cv.put(Users.FIELD_PARL_GLASAO, parl_glasao);
        //radimo insert ovog skupa podataka
        db.insert(Users.TABLE_NAME, null, cv);
    }


    public Users getByUsername(String username){
        SQLiteDatabase db = database.getReadableDatabase();

        // SELECT * FROM user WHERE username = ?

        String query = String.format("SELECT * FROM %s WHERE %s = ?", Users.TABLE_NAME, Users.FIELD_USERNAME);
        Cursor result = db.rawQuery(query, new String[]{String.valueOf(username)});
        if(result.moveToFirst()){
            String usernameFound = result.getString(result.getColumnIndex(Users.FIELD_USERNAME));
            String password = result.getString(result.getColumnIndex(Users.FIELD_PASSWORD));
            String jmbg = result.getString(result.getColumnIndex(Users.FIEDL_JMBG));

            Users user = new Users();
            user.setUsername(usernameFound);
            user.setPassword(password);
            return user;


        }else{
            return null;
        }
    }
    public ArrayList<Users> getAllUsers(){
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s", Users.TABLE_NAME);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        ArrayList<Users> list = new ArrayList<Users>(result.getCount());

        while(!result.isAfterLast()){
            String username = result.getString(result.getColumnIndex(Users.FIELD_USERNAME));
            String password = result.getString(result.getColumnIndex(Users.FIELD_PASSWORD));
            String jmbg = result.getString(result.getColumnIndex(Users.FIEDL_JMBG));
            String pred_glasao = result.getString(result.getColumnIndex(Users.FIELD_PRED_GLASAO));
            String parl_glasao = result.getString(result.getColumnIndex(Users.FIELD_PARL_GLASAO));

            Users newUser= new Users();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setJmbg(jmbg);
            newUser.setPred_glas(pred_glasao);
            newUser.setParl_glas(parl_glasao);
            list.add(newUser);
            result.moveToNext();
        }

        return list;

    }


}
