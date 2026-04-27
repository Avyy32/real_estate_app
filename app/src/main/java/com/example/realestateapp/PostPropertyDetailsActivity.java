package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class PostPropertyDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property_details);

        PropertyRepository repo = PropertyRepository.getInstance();
        
        LinearLayout layoutSellDetails = findViewById(R.id.layoutSellDetails);
        TextView tvPriceHeader = findViewById(R.id.tvPriceHeader);
        EditText etPrice = findViewById(R.id.etExpectedPrice);

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
            EditText etTotalFloors = findViewById(R.id.etTotalFloors);
            EditText etPropFloor = findViewById(R.id.etPropertyOnFloor);
            
            repo.draftFloors = etPropFloor.getText().toString() + " of " + etTotalFloors.getText().toString();
            repo.draftPrice = "₹ " + etPrice.getText().toString();

            Intent intent = new Intent(PostPropertyDetailsActivity.this, PostPropertyPhotosActivity.class);
            startActivity(intent);
        });

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}