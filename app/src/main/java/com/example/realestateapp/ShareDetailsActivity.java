package com.example.realestateapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class ShareDetailsActivity extends AppCompatActivity {

    private boolean isAgent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);

        EditText etName = findViewById(R.id.etShareName);
        EditText etPhone = findViewById(R.id.etSharePhone);
        Button btnView = findViewById(R.id.btnFinalViewNumber);
        MaterialButtonToggleGroup toggleGroup = findViewById(R.id.toggleGroupAgent);

        // Fix Yes/No buttons: make them selectable and track the choice
        if (toggleGroup != null) {
            toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
                if (isChecked) {
                    if (checkedId == R.id.btnAgentYes) {
                        isAgent = true;
                    } else if (checkedId == R.id.btnAgentNo) {
                        isAgent = false;
                    }
                }
            });
        }

        btnView.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            } else {
                String message = "Thank you " + name + "! " + (isAgent ? "Agent details captured." : "User details captured.");
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        findViewById(R.id.toolbar).setOnClickListener(v -> finish());
    }
}