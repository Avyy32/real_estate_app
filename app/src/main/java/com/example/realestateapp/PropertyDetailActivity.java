package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class PropertyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        Property property = (Property) getIntent().getSerializableExtra("property");

        if (property != null) {
            TextView tvName = findViewById(R.id.tvPropertyName);
            TextView tvLocation = findViewById(R.id.tvPropertyLocation);
            TextView tvPrice = findViewById(R.id.tvPropertyPrice);
            TextView tvDescription = findViewById(R.id.tvPropertyDescription);
            TextView tvType = findViewById(R.id.tvType);
            TextView tvBhk = findViewById(R.id.tvBhk);
            TextView tvFloors = findViewById(R.id.tvFloors);
            ImageView ivProperty = findViewById(R.id.propertyImage);

            tvName.setText(property.getName());
            tvLocation.setText(property.getLocation());
            tvPrice.setText(property.getPrice());
            tvDescription.setText(property.getDescription());
            tvType.setText(property.getPropertyType());
            tvBhk.setText(property.getBhk());
            tvFloors.setText(property.getFloors());

            Glide.with(this)
                    .load(property.getImageUri())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .into(ivProperty);
        }

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        Button btnContactDealer = findViewById(R.id.btnContactDealer);
        btnContactDealer.setOnClickListener(v -> {
            Intent intent = new Intent(PropertyDetailActivity.this, ContactDealerActivity.class);
            startActivity(intent);
        });
    }
}