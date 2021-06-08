package com.example.RentalActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import android.text.format.DateFormat;

public class CatalogActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cdMob1,cdMoB2,cdMoB3,cdMoB4,cdMoB5,cdMoB6,cdMoB7,cdMoB8;
    ActionBar actionBar;
    String hari;
    TextView tvHari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);


        cdMob1 = findViewById(R.id.cardMob1);
        cdMob1.setOnClickListener(this);
        cdMoB2 = findViewById(R.id.cardMob2);
        cdMoB2.setOnClickListener(this);
        cdMoB3 = findViewById(R.id.cardMob3);
        cdMoB3.setOnClickListener(this);
        cdMoB4 = findViewById(R.id.cardMob4);
        cdMoB4.setOnClickListener(this);
        cdMoB5 = findViewById(R.id.cardMob5);
        cdMoB5.setOnClickListener(this);
        cdMoB6 = findViewById(R.id.cardMob6);
        cdMoB6.setOnClickListener(this);
        cdMoB7 = findViewById(R.id.cardMob7);
        cdMoB7.setOnClickListener(this);
        cdMoB8 = findViewById(R.id.cardMob8);
        cdMoB8.setOnClickListener(this);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //GetHari
        tvHari =  findViewById(R.id.tvDate);
        Date dateNow = Calendar.getInstance().getTime();
        hari = (String) DateFormat.format("EEEE", dateNow);
        if (hari.equalsIgnoreCase("sunday")){
            hari = "Minggu";
        } else if(hari.equalsIgnoreCase("monday")) {
            hari = "Senin";
        } else if(hari.equalsIgnoreCase("tuesday")) {
            hari = "Selasa";
        } else if(hari.equalsIgnoreCase("wednesday")) {
            hari = "Rabu";
        }else if(hari.equalsIgnoreCase("thursday")) {
            hari = "Kamis";
        }else if(hari.equalsIgnoreCase("friday")) {
            hari = "Jumat";
        }else if(hari.equalsIgnoreCase("saturday")) {
            hari = "Sabtu";
        }
        getToday();
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d", date);
        String monthNumber = (String) DateFormat.format("M", date);
        String year = (String) DateFormat.format("yyyy", date);

        int month = Integer.parseInt(monthNumber);
        String bulan = null;
        if (month == 1) {
            bulan = "Januari";
        } else if (month == 2) {
            bulan = "Februari";
        } else if (month == 3) {
            bulan = "Maret";
        } else if (month == 4) {
            bulan = "April";
        } else if (month == 5) {
            bulan = "Mei";
        } else if (month == 6) {
            bulan = "Juni";
        } else if (month == 7) {
            bulan = "Juli";
        } else if (month == 8) {
            bulan = "Agustus";
        } else if (month == 9) {
            bulan = "September";
        } else if (month == 10) {
            bulan = "Oktober";
        } else if (month == 11) {
            bulan = "November";
        } else if (month == 12) {
            bulan = "Desember";
        }
        String formatFix = hari + ", " + tanggal + " " + bulan + " " + year;
        tvHari.setText(formatFix);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.cardMob1 :
                i = new Intent(this, DetailCatalogActivity1.class);
                startActivity(i);
                break;
            case R.id.cardMob2 :
                i = new Intent(this, DetailCatalogActivity2.class);
                startActivity(i);
                break;
            case R.id.cardMob3 :
                i = new Intent(this, DetailCatalogActivity3.class);
                startActivity(i);
                break;
            case R.id.cardMob4 :
                i = new Intent(this, DetailCatalogActivity4.class);
                startActivity(i);
                break;
            case R.id.cardMob5 :
                i = new Intent(this, DetailCatalogActivity5.class);
                startActivity(i);
                break;
            case R.id.cardMob6 :
                i = new Intent(this, DetailCatalogActivity6.class);
                startActivity(i);
                break;
            case R.id.cardMob7 :
                i = new Intent(this, DetailCatalogActivity7.class);
                startActivity(i);
                break;
            case R.id.cardMob8 :
                i = new Intent(this, DetailCatalogActivity8.class);
                startActivity(i);
                break;
        }
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