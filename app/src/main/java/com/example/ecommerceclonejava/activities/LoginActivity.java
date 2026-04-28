package com.example.ecommerceclonejava.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceclonejava.R;
import com.example.ecommerceclonejava.utils.PrefManager;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        pref = new PrefManager(this);

        findViewById(R.id.btnLogin).setOnClickListener(v -> {

            String e = email.getText().toString();
            String p = password.getText().toString();

            if (pref.checkUser(e, p)) {
                pref.setLoggedIn(true);
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}