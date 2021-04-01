package com.example.RentalActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityB extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);

        textView = findViewById(R.id.textView2);
        Intent i = getIntent();
        String emaily = i.getStringExtra("email");
        String pass = i.getStringExtra("pass");

        textView.setText("Welcome " + emaily);
    }
}