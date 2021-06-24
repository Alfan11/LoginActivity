package com.example.RentalActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;
import android.text.format.DateFormat;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashActivity extends AppCompatActivity {

    public CardView card1,card2,card3;
    DBHelper db;
    protected Cursor cursor;
    Button btnBack;
    BottomNavigationView bottomNavigationView;
    TextView halloUser, tvHari;
    ImageButton detil;
    String hari;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        db = new DBHelper(this);

        halloUser = findViewById(R.id.tvWelcomeDash);
        Bundle b = getIntent().getExtras();
        String usn = b.getString("nametest");
        halloUser.setText("Welcome in app, " + usn);

        card1 = (CardView) findViewById(R.id.cardDash1);
        card2 = (CardView) findViewById(R.id.cardDash2);
        bottomNavigationView = findViewById(R.id.botNavView);


        //Bottom Navigation Home
        bottomNavigationView.setSelectedItemId(R.id.homeNav);

        //PerfomItem
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homeNav:
//                        startActivity(new Intent(getApplicationContext(),DashActivity.class));
//                        overridePendingTransition(0,0);
                        return true;
                    //LOGOUT
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

        //ImageButtonDetil
        detil = findViewById(R.id.imgDetil);
        detil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DaftarPesanan.class);
                startActivity(i);
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


        //Get Hari
        tvHari = findViewById(R.id.tvDate);
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
}