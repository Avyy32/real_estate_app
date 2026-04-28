package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class LoginActivity extends AppCompatActivity {

    private Button btnContinue;
    private Button btnSkip;
    private EditText phoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView ivLoginBackgroundGif = findViewById(R.id.ivLoginBackgroundGif);
        if (ivLoginBackgroundGif != null) {
            Glide.with(this)
                 .asGif()
                 .load("https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExNHJqZ3R6Z3R6Z3R6Z3R6Z3R6Z3R6Z3R6Z3R6Z3R6Z3R6Z3R6JmVwPXYxX2ludGVybmFsX2dpZl9ieV9pZCZjdD1n/3o7TKDkDbIDJieKbVm/giphy.gif")
                 .into(ivLoginBackgroundGif);
        }

        btnContinue = findViewById(R.id.btnContinue);
        btnSkip = findViewById(R.id.btnSkip);
        phoneInput = findViewById(R.id.phoneInput);

        // Safely set up listeners
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            });
        }

        if (btnSkip != null) {
            btnSkip.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}