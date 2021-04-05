package com.example.calculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeManager.setTheme(this);
        setContentView(R.layout.activity_about2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public  boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    }

