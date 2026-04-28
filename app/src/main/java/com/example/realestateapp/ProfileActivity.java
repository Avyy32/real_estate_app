package com.example.realestateapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private ImageView ivProfileImage;
    private TextView tvGreeting, tvProfileEmail, tvProfilePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);

        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvGreeting = findViewById(R.id.tvGreeting);
        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        tvProfilePhone = findViewById(R.id.tvProfilePhone);
        TextView btnEditProfile = findViewById(R.id.btnEditProfile);

        loadUserData();

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
            startActivity(intent);
        });

        setupMenuItems();
        setupBottomNav();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserData();
    }

    private void loadUserData() {
        String name = prefs.getString("userName", "Chakshit Mehta");
        String email = prefs.getString("userEmail", "chakshit.kumar.24cse@bmu.edu.in");
        String phone = prefs.getString("userPhone", "+91-8307901188");
        String imageUriStr = prefs.getString("profilePicUri", null);

        tvGreeting.setText("Hope You Had A Great Day\n" + name + "!");
        tvProfileEmail.setText(email);
        tvProfilePhone.setText(phone);

        if (imageUriStr != null) {
            Glide.with(this).load(Uri.parse(imageUriStr)).circleCrop().into(ivProfileImage);
        } else {
            ivProfileImage.setImageResource(R.drawable.circle_placeholder);
        }
    }

    private void setupMenuItems() {
        // Rate our App
        View menuRate = findViewById(R.id.menuRate);
        ((ImageView) menuRate.findViewById(R.id.ivMenuIcon)).setImageResource(android.R.drawable.btn_star_big_on);
        ((TextView) menuRate.findViewById(R.id.tvMenuTitle)).setText("Rate our App");

        // Communication Settings
        View menuCommunication = findViewById(R.id.menuCommunication);
        ((ImageView) menuCommunication.findViewById(R.id.ivMenuIcon)).setImageResource(android.R.drawable.ic_menu_preferences);
        ((TextView) menuCommunication.findViewById(R.id.tvMenuTitle)).setText("Communication Settings");

        // Share Feedback
        View menuFeedback = findViewById(R.id.menuFeedback);
        ((ImageView) menuFeedback.findViewById(R.id.ivMenuIcon)).setImageResource(android.R.drawable.ic_menu_send);
        ((TextView) menuFeedback.findViewById(R.id.tvMenuTitle)).setText("Share Feedback");

        // Change Password
        View menuChangePassword = findViewById(R.id.menuChangePassword);
        ((ImageView) menuChangePassword.findViewById(R.id.ivMenuIcon)).setImageResource(android.R.drawable.ic_lock_idle_lock);
        ((TextView) menuChangePassword.findViewById(R.id.tvMenuTitle)).setText("Change Password");

        // Logout
        View menuLogout = findViewById(R.id.menuLogout);
        ((ImageView) menuLogout.findViewById(R.id.ivMenuIcon)).setImageResource(android.R.drawable.ic_lock_power_off);
        ((TextView) menuLogout.findViewById(R.id.tvMenuTitle)).setText("Logout");

        menuLogout.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void setupBottomNav() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.nav_profile);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_sell_rent) {
                startActivity(new Intent(this, PostPropertyBasicActivity.class));
                return true;
            } else if (itemId == R.id.nav_shortlist) {
                startActivity(new Intent(this, MapActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                return true;
            }
            return false;
        });
    }
}