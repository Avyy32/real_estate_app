package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnSettingsTop = findViewById(R.id.btnSettingsTop);
        Button btnEditProfile = findViewById(R.id.btnEditProfile);

        btnBack.setOnClickListener(v -> finish());

        btnSettingsTop.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
            startActivity(intent);
        });

        btnEditProfile.setOnClickListener(v -> {
            // Edit profile logic
        });

        // Set up menu items
        setupMenuItems();
    }

    private void setupMenuItems() {
        View menuAccount = findViewById(R.id.menuAccount);
        View menuEnquiries = findViewById(R.id.menuEnquiries);
        View menuSettings = findViewById(R.id.menuSettings);
        View menuLogout = findViewById(R.id.menuLogout);

        ((TextView) menuAccount.findViewById(R.id.tvMenuTitle)).setText(R.string.account_settings);
        ((TextView) menuEnquiries.findViewById(R.id.tvMenuTitle)).setText(R.string.my_enquiries);
        ((TextView) menuSettings.findViewById(R.id.tvMenuTitle)).setText(R.string.settings);
        ((TextView) menuLogout.findViewById(R.id.tvMenuTitle)).setText(R.string.log_out);

        menuAccount.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
            startActivity(intent);
        });

        menuEnquiries.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EnquiryActivity.class);
            startActivity(intent);
        });

        menuSettings.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AccountSettingsActivity.class);
            startActivity(intent);
        });

        menuLogout.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
