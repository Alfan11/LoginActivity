package com.example.RentalActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DaftarPesanan extends AppCompatActivity {


    String[] daftar;
    int[] id;
    ListView listView1;
    protected Cursor cursor;
    DBHelper db;
    public static DaftarPesanan m;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pesanan);

        //toolbar
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        m = this;
        db = new DBHelper(this);

        RefreshList();

        }


    public void RefreshList() {
        SQLiteDatabase DBHelp = db.getReadableDatabase();
        cursor = DBHelp.rawQuery("SELECT * FROM pesanan", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1).toString();
        }
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DaftarPesanan.this);
                builder.setTitle("Rental Mobil");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0: {
                                Intent i = new Intent(DaftarPesanan.this, DetailPesanan.class);
                                i.putExtra("namatest", selection);
                                startActivity(i);
                                break;
                            }
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
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