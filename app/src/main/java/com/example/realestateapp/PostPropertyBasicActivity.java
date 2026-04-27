package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class PostPropertyBasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property_basic);

        ChipGroup cgLookingTo = findViewById(R.id.cgLookingTo);
        ChipGroup cgPropertyType = findViewById(R.id.cgPropertyType);
        EditText etContact = findViewById(R.id.etContactDetails);

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> {
            PropertyRepository repo = PropertyRepository.getInstance();
            repo.draftContact = etContact.getText().toString();
            
            int checkedLookingToId = cgLookingTo.getCheckedChipId();
            if (checkedLookingToId != -1) {
                Chip chip = findViewById(checkedLookingToId);
                repo.draftLookingTo = chip.getText().toString();
            }

            int checkedTypeId = cgPropertyType.getCheckedChipId();
            if (checkedTypeId != -1) {
                Chip chip = findViewById(checkedTypeId);
                repo.draftPropertyType = chip.getText().toString();
            }

            Intent intent = new Intent(PostPropertyBasicActivity.this, PostPropertyDetailsActivity.class);
            startActivity(intent);
        });

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}