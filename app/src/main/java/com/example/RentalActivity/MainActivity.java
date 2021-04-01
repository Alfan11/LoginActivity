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

public class MainActivity extends AppCompatActivity {

    EditText emailText,passText;
    Button button;
    TextView signUp;
    ImageButton fb,ig;
    private String TAG = "Message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");


        emailText = findViewById(R.id.userET);
        passText = findViewById(R.id.passET);
        button = findViewById(R.id.LogBtn);
        signUp = findViewById(R.id.tvRegisterLog);
        fb = findViewById(R.id.FbLogo);
        ig = findViewById(R.id.instaLogo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = emailText.getText().toString();
                String passStr = passText.getText().toString();

                Intent i = new Intent(getApplicationContext(),MainActivityB.class);
                i.putExtra("email", emailStr);
                i.putExtra("pass", passStr);
                startActivity(i);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });

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