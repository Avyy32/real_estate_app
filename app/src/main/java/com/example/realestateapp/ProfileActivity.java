package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        View menuEnquiries = findViewById(R.id.menuEnquiries);
        if (menuEnquiries != null) {
            TextView tvTitle = menuEnquiries.findViewById(R.id.tvMenuTitle);
            tvTitle.setText("My Enquiries");
            menuEnquiries.setOnClickListener(v -> {
                startActivity(new Intent(ProfileActivity.this, EnquiryActivity.class));
            });
        }

        View menuAccount = findViewById(R.id.menuAccount);
        if (menuAccount != null) {
            ((TextView) menuAccount.findViewById(R.id.tvMenuTitle)).setText("Account Settings");
        }

        View menuSettings = findViewById(R.id.menuSettings);
        if (menuSettings != null) {
            ((TextView) menuSettings.findViewById(R.id.tvMenuTitle)).setText("App Settings");
        }

        View menuLogout = findViewById(R.id.menuLogout);
        if (menuLogout != null) {
            ((TextView) menuLogout.findViewById(R.id.tvMenuTitle)).setText("Logout");
        }
    }
}
