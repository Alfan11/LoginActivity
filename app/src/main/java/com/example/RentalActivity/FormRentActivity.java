package com.example.RentalActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FormRentActivity extends AppCompatActivity {

    Spinner spinner;
    ActionBar actionBar;
    TextView tvName;
    EditText etDate, etNamaPsn, etAlamatPsn, etNohpPsn, etMobilPsn, etDurasiPsn, etHarga;
    DatePickerDialog.OnDateSetListener setListener;
    DBHelper db;
    Button btnPsn;
    RadioGroup rg;
    RadioButton rd;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_rent);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //form
        etNamaPsn = (EditText)findViewById(R.id.etNamaPs);
        etAlamatPsn = (EditText)findViewById(R.id.etAlamatPs);
        etNohpPsn = (EditText)findViewById(R.id.etNoHpPs);
        spinner = findViewById(R.id.inputSpinPs);
        rg = findViewById(R.id.rgDurasiSewa);



        String getValueSpin = spinner.getSelectedItem().toString();

        //Durasi
        int radioId = rg.getCheckedRadioButtonId();
        rd = findViewById(radioId);


        //database
        db = new DBHelper(this);
        btnPsn = findViewById(R.id.btnPsnTrans);

        etDate = findViewById(R.id.etInputTgl);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormRentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                //disabled past date
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                //disable date max
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (1000*60*60*24*7));
                datePickerDialog.show();
            }
        });

//        btnPsn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String strNama = etNamaPsn.getText().toString();
//                String strAlamat = etAlamatPsn.getText().toString();
//                String strNohp = etNohpPsn.getText().toString();
//                String strSpin = getValueSpin;
//                String strDurasi = rd.getText().toString();
//                String harga;
//                if (strSpin.equals("Avanza Veloz")){
//                    harga = "200000";
//                } else if (strSpin.equals("Daihatsu Ayla")){
//                    harga = "200000";
//                } else {
//                    harga = "500000";
//                }
//                Boolean insertPsn = db.insertPesanan(strNama, strAlamat, strNohp, strDurasi, harga);
//                if (insertPsn == true){
//                    Toast.makeText(getApplicationContext(),"Pesanan Berhasil Dilakukan",Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(FormRentActivity.this,DashActivity.class);
//                    startActivity(i);
//                    finish();
//                } else {
//                    Toast.makeText(getApplicationContext(),"Pesanan Gagal",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


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