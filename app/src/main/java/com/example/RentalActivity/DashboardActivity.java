package com.example.RentalActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    public CardView card1,card2;
    DBHelper db;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigation = findViewById(R.id.botNavView);

        card1 = (CardView) findViewById(R.id.cardDash1);
        card2 = (CardView) findViewById(R.id.cardDash2);

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
            Intent i = new Intent(DashboardActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updateSession = db.upgradeSession("kosong",1);
                if (updateSession == true){
                    Toast.makeText(getApplicationContext(),"Berhasil Logout", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DashboardActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }


}