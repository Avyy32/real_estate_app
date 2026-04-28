package com.example.realestateapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class ShareDetailsActivity extends AppCompatActivity {

    private boolean isAgent = false;
    private String mode = "call";
    private final String TARGET_NUMBER = "8307901188";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);

        mode = getIntent().getStringExtra("mode");
        if (mode == null) mode = "call";

        TextView tvTitle = findViewById(R.id.tvShareTitle);
        EditText etName = findViewById(R.id.etShareName);
        EditText etPhone = findViewById(R.id.etSharePhone);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        MaterialButtonToggleGroup toggleGroup = findViewById(R.id.toggleGroupAgent);

        if (mode.equals("whatsapp")) {
            tvTitle.setText("Provide your details to Chat with this advertiser");
            btnSubmit.setText("Chat Now");
        } else {
            tvTitle.setText("Please share your details to contact the advertiser");
            btnSubmit.setText("Call");
        }

        // Set text color to black
        etName.setTextColor(getResources().getColor(android.R.color.black));
        etPhone.setTextColor(getResources().getColor(android.R.color.black));

        if (toggleGroup != null) {
            toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
                if (isChecked) {
                    isAgent = (checkedId == R.id.btnAgentYes);
                }
            });
        }

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            } else {
                // Perform redirect
                if (mode.equals("whatsapp")) {
                    openWhatsApp();
                } else {
                    makeCall();
                }
                finish();
            }
        });

        findViewById(R.id.toolbar).setOnClickListener(v -> finish());
    }

    private void openWhatsApp() {
        try {
            String url = "https://api.whatsapp.com/send?phone=91" + TARGET_NUMBER + "&text=Hi, I am interested in your property listing.";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+91" + TARGET_NUMBER));
        startActivity(intent);
    }
}