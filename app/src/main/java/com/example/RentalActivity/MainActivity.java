package com.example.RentalActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    //login
    DBHelper db;


    EditText username,password;
    TextInputLayout txEmail, txPassword;
    Button buttonLog;
    TextView signUp;
    ImageButton fb,ig;
    private String TAG = "Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");


        username = (EditText)findViewById(R.id.userET);
        password = (EditText)findViewById(R.id.passET);
        buttonLog = findViewById(R.id.LogBtn);
        signUp = findViewById(R.id.tvRegisterLog);
        //signUp = findViewById(R.id.tvRegisterLog);
        fb = findViewById(R.id.FbLogo);
        ig = findViewById(R.id.instaLogo);

        //database
        db = new DBHelper(this);

        //login
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();

                if (strUsername.equals("") || strPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Form Tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean log = db.cekLogin(strUsername, strPassword);
                    if (log == true) {
                        Boolean updateSession = db.upgradeSession("ada", 1);
                        if (updateSession == true) {
                            Toast.makeText(getApplicationContext(), "Berhasil masuk", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, DashActivity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Login Gagal, Sesuaikan Username & Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        //register
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //Register
        //signUp.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
             //   Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
               // startActivity(i);
            //}
        //});

        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Belum ada ig nya mon maap",Toast.LENGTH_SHORT).show();
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Ini juga belum ada hehe",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"msg onStart");
    }
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"msg onResume");
    }
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"msg onPause");
    }
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"msg onStop");
    }
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG,"msg onRestart");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"msg onDestroy");
    }
}