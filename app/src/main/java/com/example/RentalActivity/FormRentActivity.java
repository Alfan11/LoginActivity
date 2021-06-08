package com.example.RentalActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    TextView halloUser;
    EditText etDate, etNamaPsn, etAlamatPsn, etNohpPsn;
    DatePickerDialog.OnDateSetListener setListener;
    DBHelper db;
    Button btnPsn;
    RadioGroup rg;
    RadioButton rd1,rd2;
    String durasi = "";
    String date="";
    String valueofspin="";
    String strHarga="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_rent);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = new DBHelper(this);

        //form
        etNamaPsn = (EditText)findViewById(R.id.etNamaPs);
        etAlamatPsn = (EditText)findViewById(R.id.etAlamatPs);
        etNohpPsn = (EditText)findViewById(R.id.etNoHpPs);

        //radiobuttongroup
        rg = findViewById(R.id.rgDurasi);
        rd1 = findViewById(R.id.rdFull);
        rd2 = findViewById(R.id.rdHalf);

        //spinner
        spinner = findViewById(R.id.inputSpinPs);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.listmobil,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueofspin = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Radiogrup get durasi
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rdFull){
                    durasi = "24 Jam";
                } else if (checkedId==R.id.rdHalf){
                    durasi = "12 Jam";
                }
            }
        });


        //database
//        db = new DBHelper(this);
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
                        date = day+"/"+month+"/"+year;
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

        btnPsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNama = etNamaPsn.getText().toString();
                String strAlt = etAlamatPsn.getText().toString();
                String strNohp = etNohpPsn.getText().toString();
                String strSpinner = valueofspin.trim();
                String strDur = durasi.trim();
                String strDate = date.trim();


                if (strDur.equals("24 Jam") && strSpinner.equals("Avanza Veloz")){
                    strHarga = "300000";
                } else if (strDur.equals("12 Jam") && strSpinner.equals("Avanza Veloz")){ //sudah
                    strHarga = "200000";
                }else if (strDur.equals("24 Jam") && strSpinner.equals("Daihatsu Ayla")){
                    strHarga = "300000";
                } else if (strDur.equals("12 Jam") && strSpinner.equals("Daihatsu Ayla")) {
                    strHarga = "200000";
                } else if (strDur.equals("24 Jam") && strSpinner.equals("Toyota Alphard")) {
                    strHarga = "400000";
                } else if (strDur.equals("12 Jam") && strSpinner.equals("Toyota Alphard")) {
                    strHarga = "300000";
                } else if (strDur.equals("24 Jam") && strSpinner.equals("Toyota Fortuner")) {
                    strHarga = "350000";
                } else if (strDur.equals("12 Jam") && strSpinner.equals("Toyota Fortuner")) {
                    strHarga = "300000";
                } else if (strDur.equals("24 Jam") && strSpinner.equals("Suzuki Jimny")) {
                    strHarga = "350000";
                } else if (strDur.equals("12 Jam") && strSpinner.equals("Suzuki Jimny")) {
                    strHarga = "300000";
                } else if (strDur.equals("24 Jam") && strSpinner.equals("Suzuki Ertiga")) {
                    strHarga = "300000";
                } else if (strDur.equals("12 Jam") && strSpinner.equals("Suzuki Ertiga")) {
                    strHarga = "250000";
                } else if (strDur.equals("24 Jam") && strSpinner.equals("Honda Jazz")) { //sudah
                    strHarga = "350000";
                } else if (strDur.equals("12 Jam") && strSpinner.equals("Honda Jazz")) {
                    strHarga = "300000";
                } else if (strDur.equals("24 Jam") && strSpinner.equals("Honda Crv")) {
                    strHarga = "350000";
                } else if (strDur.equals("12 Jam") && strSpinner.equals("Honda Crv")) {
                    strHarga = "300000";
                }

                if (strNama.equals("") || strAlt.equals("") || strNohp.equals("") || strSpinner.equals("") || strDur.equals("") || strDate.equals("")){
                    Toast.makeText(getApplicationContext(), "Form Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insertPesanan = db.insertPesanan(strNama, strAlt, strDur, strNohp, strDate, strSpinner, strHarga);
                    if (insertPesanan == true){
                        Toast.makeText(getApplicationContext(),"Input sukses",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(FormRentActivity.this, DashActivity.class);
                        startActivity(i);
                        finish();
                    } else{
                        Toast.makeText(getApplicationContext(),"gagal",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


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