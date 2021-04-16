package com.example.RentalActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashActivity extends AppCompatActivity {

    public CardView card1,card2;
    DBHelper db;
    Button btnBack, imgLogout;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        db = new DBHelper(this);

        card1 = (CardView) findViewById(R.id.cardDash1);
        card2 = (CardView) findViewById(R.id.cardDash2);
        btnBack = findViewById(R.id.backB);
        bottomNavigationView = findViewById(R.id.botNavView);


        //Bottom Navigation Home
        bottomNavigationView.setSelectedItemId(R.id.homeNav);

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
                            Intent i = new Intent(DashActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        return true;
                }
                return false;
            }
        });


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CatalogActivity.class);
                startActivity(i);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FormRentActivity.class);
                startActivity(i);
            }
        });

        Boolean checkSession = db.checkSession("ada");
        if (checkSession == false){
            Intent i = new Intent(DashActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        //Logout
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Boolean updateSession = db.upgradeSession("kosong",1);
//                if (updateSession == true){
//                    Toast.makeText(getApplicationContext(),"Berhasil Logout", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(DashActivity.this,MainActivity.class);
//                    startActivity(i);
//                    finish();
//                }
//            }
//        });

    }
}