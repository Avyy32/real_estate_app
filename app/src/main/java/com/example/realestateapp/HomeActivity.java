package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                return true;
            }
            if (item.getItemId() == R.id.nav_search) {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });

        View propertyCard1 = findViewById(R.id.propertyCard1);
        View propertyCard2 = findViewById(R.id.propertyCard2);

        // Load images into property cards
        if (propertyCard1 != null) {
            ImageView img1 = propertyCard1.findViewById(R.id.propertyImage);
            Glide.with(this)
                .load("https://images.unsplash.com/photo-1570129477492-45c003edd2be?auto=format&fit=crop&w=800&q=80")
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(img1);
            propertyCard1.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, PropertyDetailActivity.class)));
        }

        if (propertyCard2 != null) {
            ImageView img2 = propertyCard2.findViewById(R.id.propertyImage);
            Glide.with(this)
                .load("https://images.unsplash.com/photo-1580587771525-78b9dba3b914?auto=format&fit=crop&w=800&q=80")
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(img2);
            propertyCard2.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, PropertyDetailActivity.class)));
        }
    }
}
