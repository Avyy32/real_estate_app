package com.example.realestateapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // My Enquiries
        View menuEnquiries = findViewById(R.id.menuEnquiries);
        if (menuEnquiries != null) {
            ((TextView) menuEnquiries.findViewById(R.id.tvMenuTitle)).setText("My Enquiries");
            menuEnquiries.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, EnquiryActivity.class));
            });
        }

        // Account Settings
        View menuAccount = findViewById(R.id.menuAccount);
        if (menuAccount != null) {
            ((TextView) menuAccount.findViewById(R.id.tvMenuTitle)).setText("Account Settings");
            menuAccount.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, AccountSettingsActivity.class));
            });
        }

        // App Settings
        View menuSettings = findViewById(R.id.menuSettings);
        if (menuSettings != null) {
            ((TextView) menuSettings.findViewById(R.id.tvMenuTitle)).setText("App Settings");
        }

        // Logout
        View menuLogout = findViewById(R.id.menuLogout);
        if (menuLogout != null) {
            ((TextView) menuLogout.findViewById(R.id.tvMenuTitle)).setText("Logout");
            menuLogout.setOnClickListener(v -> {
                // Navigate back to LoginActivity (the "Conquer" front page)
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                // Clear the back stack so user cannot go back to profile after logout
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            });
        }

        // Edit Profile Button
        View btnEditProfile = findViewById(R.id.btnEditProfile);
        if (btnEditProfile != null) {
            btnEditProfile.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, AccountSettingsActivity.class));
            });
        }

        updateUI();
    }

    private void updateUI() {
        String name = prefs.getString("userName", "Chakshit mehta");
        String email = prefs.getString("userEmail", "chakshit.kumar.24cse@bmu.edu.in");
        String imageUri = prefs.getString("profilePicUri", null);

        TextView nameTv = findViewById(R.id.tvProfileName); 
        TextView emailTv = findViewById(R.id.tvProfileEmail);
        TextView detailsEmailTv = findViewById(R.id.tvDetailsEmail);
        ImageView profileIv = findViewById(R.id.ivProfileImage);
        TextView initialTv = findViewById(R.id.tvProfileInitial);

        if (nameTv != null) nameTv.setText(name);
        if (emailTv != null) emailTv.setText(email);
        if (detailsEmailTv != null) detailsEmailTv.setText(email);
        
        if (profileIv != null) {
            if (imageUri != null) {
                if (initialTv != null) initialTv.setVisibility(View.GONE);
                Glide.with(this)
                     .load(Uri.parse(imageUri))
                     .placeholder(R.drawable.circle_placeholder)
                     .circleCrop()
                     .into(profileIv);
            } else {
                if (initialTv != null) {
                    initialTv.setVisibility(View.VISIBLE);
                    initialTv.setText(name.substring(0, 1).toUpperCase());
                }
                profileIv.setImageResource(R.drawable.circle_placeholder);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }
}