package com.example.realestateapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class AccountSettingsActivity extends AppCompatActivity {

    private ImageView ivProfilePic;
    private EditText etName, etEmail, etPhone, etLocation;
    private Uri selectedImageUri;
    private SharedPreferences prefs;

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    Glide.with(this).load(selectedImageUri).circleCrop().into(ivProfilePic);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);

        ivProfilePic = findViewById(R.id.ivProfilePic);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etLocation = findViewById(R.id.etLocation);
        Button btnSaveChanges = findViewById(R.id.btnSaveChanges);

        // Load existing data
        etName.setText(prefs.getString("userName", "Chakshit mehta"));
        etEmail.setText(prefs.getString("userEmail", "chakshit.kumar.24cse@bmu.edu.in"));
        etPhone.setText(prefs.getString("userPhone", ""));
        etLocation.setText(prefs.getString("userLocation", ""));
        
        String imageUriStr = prefs.getString("profilePicUri", null);
        if (imageUriStr != null) {
            Glide.with(this).load(Uri.parse(imageUriStr)).circleCrop().into(ivProfilePic);
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnChangePic).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImageLauncher.launch(intent);
        });

        btnSaveChanges.setOnClickListener(v -> {
            String newName = etName.getText().toString().trim();
            String newEmail = etEmail.getText().toString().trim();
            String newPhone = etPhone.getText().toString().trim();
            String newLocation = etLocation.getText().toString().trim();

            if (newName.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Please fill Name and Email", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("userName", newName);
            editor.putString("userEmail", newEmail);
            editor.putString("userPhone", newPhone);
            editor.putString("userLocation", newLocation);

            if (selectedImageUri != null) {
                editor.putString("profilePicUri", selectedImageUri.toString());
            }
            editor.apply();

            Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}