package com.example.RentalActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {


    TextView log;
    Button btnReg;
    DBHelper db;
    EditText username, password, passwordConf, email, noHp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //database
        db = new DBHelper(this);
        username = (EditText)findViewById(R.id.etNameReg);
        password = (EditText)findViewById(R.id.etPassReg);
        passwordConf = (EditText)findViewById(R.id.etPassConfReg);
        email = (EditText)findViewById(R.id.etMailReg);
        noHp = (EditText)findViewById(R.id.etNoHpReg);

        log = findViewById(R.id.tvBackLogRegis);
        btnReg = findViewById(R.id.RegisterBtn);

        //login button
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        //register
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                String strPasswordConf = passwordConf.getText().toString();
                String strMail = email.getText().toString();
                String strnoHp = noHp.getText().toString();
                if (strPassword.equals(strPasswordConf)){
                    Boolean daftar = db.insertUser(strUsername,strPassword,strMail,strnoHp);
                    if (daftar == true){
                        Toast.makeText(getApplicationContext(),"Registrasi Berhasil",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"Registrasi gagal",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Password tidak sama",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}