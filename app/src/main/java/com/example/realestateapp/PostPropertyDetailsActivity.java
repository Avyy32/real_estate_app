package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PostPropertyDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property_details);

        PropertyRepository repo = PropertyRepository.getInstance();
        
        LinearLayout layoutSellDetails = findViewById(R.id.layoutSellDetails);
        TextView tvPriceHeader = findViewById(R.id.tvPriceHeader);
        EditText etPrice = findViewById(R.id.etExpectedPrice);
        EditText etAddress = findViewById(R.id.etAddress);
        EditText etTotalFloors = findViewById(R.id.etTotalFloors);
        EditText etPropFloor = findViewById(R.id.etPropertyOnFloor);

        // Dynamically change Step 2 based on Step 1 selection
        if ("Sell".equalsIgnoreCase(repo.draftLookingTo)) {
            layoutSellDetails.setVisibility(View.VISIBLE);
            tvPriceHeader.setText("Expected Price");
        } else {
            layoutSellDetails.setVisibility(View.GONE);
            tvPriceHeader.setText("Expected Monthly Rent");
            etPrice.setHint("₹ Monthly Rent");
        }

        Button btnNext = findViewById(R.id.btnNextStep);
        btnNext.setOnClickListener(v -> {
            String address = etAddress.getText().toString();
            String price = etPrice.getText().toString();
            
            if (address.isEmpty()) {
                Toast.makeText(this, "Please enter property address", Toast.LENGTH_SHORT).show();
                return;
            }
            if (price.isEmpty()) {
                Toast.makeText(this, "Please enter expected price/rent", Toast.LENGTH_SHORT).show();
                return;
            }

            repo.draftAddress = address;
            repo.draftFloors = etPropFloor.getText().toString() + " of " + etTotalFloors.getText().toString();
            repo.draftPrice = "₹ " + price + ("Sell".equalsIgnoreCase(repo.draftLookingTo) ? "" : "/mo");

            Intent intent = new Intent(PostPropertyDetailsActivity.this, PostPropertyPhotosActivity.class);
            startActivity(intent);
        });

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}