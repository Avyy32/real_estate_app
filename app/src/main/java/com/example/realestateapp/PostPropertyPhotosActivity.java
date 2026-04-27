package com.example.realestateapp;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.List;

public class PostPropertyPhotosActivity extends AppCompatActivity {

    private final List<Uri> selectedImageUris = new ArrayList<>();
    
    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUris.clear();
                    if (result.getData().getClipData() != null) {
                        ClipData clipData = result.getData().getClipData();
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            selectedImageUris.add(clipData.getItemAt(i).getUri());
                        }
                    } else if (result.getData().getData() != null) {
                        selectedImageUris.add(result.getData().getData());
                    }
                    
                    Toast.makeText(this, selectedImageUris.size() + " Images Selected", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property_photos);

        CardView btnUploadPhotos = findViewById(R.id.btnUploadPhotos);
        Button btnFinish = findViewById(R.id.btnFinish);
        EditText etDescription = findViewById(R.id.etDescription);

        btnUploadPhotos.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            galleryLauncher.launch(Intent.createChooser(intent, "Select Photos"));
        });

        btnFinish.setOnClickListener(v -> {
            PropertyRepository repo = PropertyRepository.getInstance();
            
            String imagePath = !selectedImageUris.isEmpty() ? selectedImageUris.get(0).toString() : "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?auto=format&fit=crop&w=800&q=80";
            String desc = etDescription.getText().toString();
            if (desc.isEmpty()) desc = "No description provided.";

            String listingType = "Resale";
            if (repo.draftLookingTo != null && (repo.draftLookingTo.contains("Rent") || repo.draftLookingTo.contains("PG"))) {
                listingType = "Rent";
            }

            Property newProperty = new Property(
                    repo.draftPropertyType + " for " + (repo.draftLookingTo != null ? repo.draftLookingTo : "Sale"),
                    "Location not specified",
                    repo.draftPrice != null ? repo.draftPrice : "Price on request",
                    imagePath,
                    desc,
                    repo.draftPropertyType,
                    repo.draftLookingTo,
                    "3 BHK",
                    repo.draftFloors,
                    "2,500 sqft",
                    "Individual Owner",
                    listingType
            );

            repo.addProperty(newProperty);

            Toast.makeText(this, "Property Posted Successfully!", Toast.LENGTH_LONG).show();
            
            Intent intent = new Intent(PostPropertyPhotosActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}