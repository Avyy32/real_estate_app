package com.example.realestateapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDealerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_dealer);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        EditText etFullName = findViewById(R.id.etFullName);
        EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);
        EditText etMessage = findViewById(R.id.etMessage);
        Button btnSendInquiry = findViewById(R.id.btnSendInquiry);

        btnSendInquiry.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String phone = etPhoneNumber.getText().toString().trim();
            String message = etMessage.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in your name and phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            Enquiry enquiry = new Enquiry("Modern Family House", name, phone, message);
            EnquiryManager.addEnquiry(enquiry);

            Toast.makeText(this, "Enquiry sent successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
