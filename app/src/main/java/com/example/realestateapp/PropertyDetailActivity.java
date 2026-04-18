package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class PropertyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        ImageView propertyImage = findViewById(R.id.propertyImage);
        // Using a real image URL for demonstration
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1570129477492-45c003edd2be?auto=format&fit=crop&w=800&q=80")
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(propertyImage);

        Button btnContactDealer = findViewById(R.id.btnContactDealer);
        btnContactDealer.setOnClickListener(v -> {
            Intent intent = new Intent(PropertyDetailActivity.this, ContactDealerActivity.class);
            startActivity(intent);
        });
    }
}
