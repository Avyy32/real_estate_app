package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);
        TextView tvLoginLink = findViewById(R.id.tvLoginLink);

        btnBack.setOnClickListener(v -> finish());

        btnCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, AccountSettingsActivity.class);
            startActivity(intent);
            finish();
        });

        tvLoginLink.setOnClickListener(v -> finish());
    }
}