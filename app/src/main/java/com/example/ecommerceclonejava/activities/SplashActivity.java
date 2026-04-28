package com.example.ecommerceclonejava.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.utils.PrefManager;

public class SplashActivity extends AppCompatActivity {

    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pref = new PrefManager(this);

        new Handler().postDelayed(() -> {
            if (pref.isLoggedIn()) {
                startActivity(new Intent(this, HomeActivity.class));
            } else {
                startActivity(new Intent(this, ChoiceActivity.class));
            }
            finish();
        }, 2000);
    }
}