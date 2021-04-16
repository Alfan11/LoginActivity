package com.example.RentalActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityB extends AppCompatActivity {

    ActionBar actionBar;
    TextView textView;
    Button btnBack, displayData;
    DBHelper db;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);

        db = new DBHelper(this);

        btnBack = findViewById(R.id.backB);
        displayData = findViewById(R.id.displaydata);
         actionBar = getSupportActionBar();
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bottomNavigationView = findViewById(R.id.botNavView);


        //Bottom Navigation Home
//        bottomNavigationView.setSelectedItemId(R.id.DetailNav);

        //PerfomItem
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homeNav:
                        startActivity(new Intent(getApplicationContext(),DashActivity.class));
                        overridePendingTransition(0,0);
                        return true;
//                    case R.id.botNavView:
//                        startActivity(new Intent(getApplicationContext(),MainActivityB.class));
//                        overridePendingTransition(0,0);
//                        return true;
                    case R.id.logoutNav:
                        Boolean updateSession = db.upgradeSession("kosong",1);
                        if (updateSession == true){
                            Toast.makeText(getApplicationContext(),"Berhasil Logout", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivityB.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        return true;
                }
                return false;
            }
        });

        Boolean checkSession = db.checkSession("ada");
        if (checkSession == false){
            Intent i = new Intent(MainActivityB.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        //logout
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Boolean updateSession = db.upgradeSession("kosong",1);
//                if (updateSession == true){
//                    Toast.makeText(getApplicationContext(),"Berhasil Logout", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(MainActivityB.this,MainActivity.class);
//                    startActivity(i);
//                    finish();
//                }
                //Retrive Data
                Cursor cursor = db.alldata();
                if (cursor.getCount()==0){
                    Toast.makeText(getApplicationContext(),"TIDAK ADA DATA",Toast.LENGTH_SHORT).show();
                } else {
                    while (cursor.moveToNext()){
                        Toast.makeText(getApplicationContext(),"ID "+cursor.getString(0),Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"Usm "+cursor.getString(1),Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"mail "+cursor.getString(3),Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"nohp "+cursor.getString(4),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        displayData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase simpledb = getApplicationContext().openOrCreateDatabase("loginSQLite.db", Context.MODE_PRIVATE,null);
//            }
//        });



    }


}