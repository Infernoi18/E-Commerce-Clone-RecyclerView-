package com.example.ecommerceclonejava.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.utils.PrefManager;

public class ChoiceActivity extends AppCompatActivity {

    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = new PrefManager(this);

        if (pref.isLoggedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_choice);

        findViewById(R.id.btnLogin).setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class)));

        findViewById(R.id.btnRegister).setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}