package com.example.RentalActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DetailPesanan extends AppCompatActivity {

    ActionBar actionBar;
    String sNama, sAlamat, sNotelp, sMerk, sHarga, sDurasi;
    TextView tvNama, tvAlamat, tvNotelp, tvMerk, tvHarga, tvDurasi;


    protected Cursor cursor;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        //toolbar
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBHelper(this);
        SQLiteDatabase dbHelper = db.getReadableDatabase();
        cursor = dbHelper.rawQuery("SELECT * FROM pesanan WHERE namatest = '" +
                getIntent().getStringExtra("namatest") +
                "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0 ){
            cursor.moveToPosition(0);
            sNama = cursor.getString(1);
            sAlamat = cursor.getString(2);
            sNotelp = cursor.getString(4);
            sMerk = cursor.getString(6);
            sHarga = cursor.getString(7);
            sDurasi = cursor.getString(3);
        }

        tvNama = findViewById(R.id.HNama);
        tvAlamat = findViewById(R.id.HAlamat);
        tvNotelp = findViewById(R.id.HTelp);
        tvMerk = findViewById(R.id.HMerk);
        tvHarga = findViewById(R.id.HHarga);
        tvDurasi = findViewById(R.id.HDurasi);

        tvNama.setText("   "+ sNama);
        tvAlamat.setText("     " + sAlamat);
        tvNotelp.setText("     " + sNotelp);
        tvMerk.setText("     " + sMerk);
        tvHarga.setText("     Rp. " + sHarga);
        tvDurasi.setText("  "+sDurasi);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}