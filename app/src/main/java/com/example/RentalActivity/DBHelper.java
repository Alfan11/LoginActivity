package com.example.RentalActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "loginSQLite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text)");
        db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text, password text, email text, nohpin text)");
        db.execSQL("CREATE TABLE pesanan(idtest integer PRIMARY KEY AUTOINCREMENT, namatest text, alamat text, durasi text, nohppsn text, date text, merkmobil text, harga text)");
        db.execSQL("INSERT INTO session(id, login) VALUES(1, 'kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    //check session
    public Boolean checkSession(String sessionValues) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = db.update("session", contentValues, "id="+id, null);
        if (update == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //insert user
    public Boolean insertUser(String username, String password, String email, String nohpin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email",email);
        contentValues.put("nohpin",nohpin);

        long insert = db.insert("user", null, contentValues);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //Insert test
    public Boolean insertPesanan(String namatest ,String alamat, String durasi, String nphppsn, String date, String merkmobil, String harga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("namatest",namatest);
        contentValues.put("alamat",alamat);
        contentValues.put("durasi",durasi);
        contentValues.put("nohppsn",nphppsn);
        contentValues.put("date",date);
        contentValues.put("merkmobil",merkmobil);
        contentValues.put("harga",harga);
        long insert = db.insert("pesanan",null,contentValues);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }




////    Create Pesanan
//    public Boolean insertPesanan(String nama, String alamat, String nohppsn, String durasi, String harga){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("nama",nama);
//        contentValues.put("alamat",alamat);
//        contentValues.put("nohppsn",nohppsn);
//        contentValues.put("durasi",durasi);
////        contentValues.put("waktu",waktu);
//        contentValues.put("harga",harga);
//        long insert = db.insert("pesanan",null,contentValues);
//        if (insert == -1){
//            return false;
//        } else {
//            return true;
//        }
//    }

    //RetriveDataUser
    public Cursor alldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user",null);
        return cursor;
    }



    //login cek
    public Boolean cekLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ?  AND password = ?", new String[]{username,password});
        if (cursor.getCount() > 0 ){
            return true;
        } else {
            return false;
        }
    }


}
