package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {

    settings context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeManager.setTheme(this);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        {
            Button item = findViewById(R.id.btnT1);
            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThemeManager.changeTo(context, ThemeManager.THEME_2);
                }
            };
            item.setOnClickListener(buttonClickListener);
        }
        {
            Button item = findViewById(R.id.btnT2);
            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThemeManager.changeTo(context, ThemeManager.THEME_1);
                }
            };
            item.setOnClickListener(buttonClickListener);

        }
    }



    @Override
    public  boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    }