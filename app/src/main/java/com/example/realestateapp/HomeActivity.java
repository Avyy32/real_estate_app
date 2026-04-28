package com.example.realestateapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvProperties, rvNewLaunches;
    private PropertyAdapter adapter;
    private ProjectAdapter projectAdapter;
    private final String TARGET_PHONE = "8307901188";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Load Banner
        ImageView ivBanner = findViewById(R.id.ivBanner);
        if (ivBanner != null) {
            Glide.with(this).load("https://images.unsplash.com/photo-1486406146926-c627a92ad1ab").centerCrop().into(ivBanner);
        }

        // Setup Adapters
        rvProperties = findViewById(R.id.rvProperties);
        rvProperties.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        adapter = new PropertyAdapter(this, PropertyRepository.getInstance().getProperties());
        rvProperties.setAdapter(adapter);

        rvNewLaunches = findViewById(R.id.rvNewLaunches);
        rvNewLaunches.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        projectAdapter = new ProjectAdapter(this, getDummyProjects());
        rvNewLaunches.setAdapter(projectAdapter);

        setupClickListeners();
        setupSupportListeners();
        setupFeedbackLogic();
    }

    private void setupClickListeners() {
        findViewById(R.id.btnPostProperty).setOnClickListener(v -> startActivity(new Intent(this, PostPropertyBasicActivity.class)));
        findViewById(R.id.catBuy).setOnClickListener(v -> openSearch("Buy"));
        findViewById(R.id.catRent).setOnClickListener(v -> openSearch("Rent"));
        findViewById(R.id.catCommercial).setOnClickListener(v -> openSearch("Commercial"));
        findViewById(R.id.homeSearchBar).setOnClickListener(v -> openSearch("Buy"));
        findViewById(R.id.cardBudgetCalculator).setOnClickListener(v -> startActivity(new Intent(this, BudgetCalculatorActivity.class)));
        findViewById(R.id.cardEmiCalculator).setOnClickListener(v -> startActivity(new Intent(this, EmiCalculatorActivity.class)));
        
        findViewById(R.id.btnNotifications).setOnClickListener(v -> startActivity(new Intent(this, NotificationsActivity.class)));

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) return true;
            if (itemId == R.id.nav_sell_rent) { startActivity(new Intent(this, PostPropertyBasicActivity.class)); return true; }
            if (itemId == R.id.nav_shortlist) { startActivity(new Intent(this, MapActivity.class)); return true; }
            if (itemId == R.id.nav_profile) { startActivity(new Intent(this, ProfileActivity.class)); return true; }
            return false;
        });
    }

    private void setupSupportListeners() {
        findViewById(R.id.btnSupportCall).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + TARGET_PHONE));
            startActivity(intent);
        });

        findViewById(R.id.btnSupportWhatsapp).setOnClickListener(v -> {
            try {
                String url = "https://api.whatsapp.com/send?phone=91" + TARGET_PHONE + "&text=Hi, I need support with Conqueror App.";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnRequestCallback).setOnClickListener(v -> 
            Toast.makeText(this, "Callback requested! We will call you back on " + TARGET_PHONE, Toast.LENGTH_LONG).show());
    }

    private void setupFeedbackLogic() {
        View[] stars = {
            findViewById(R.id.star1), findViewById(R.id.star2),
            findViewById(R.id.star3), findViewById(R.id.star4),
            findViewById(R.id.star5)
        };

        for (int i = 0; i < stars.length; i++) {
            final int index = i;
            stars[i].setOnClickListener(v -> {
                for (int j = 0; j <= index; j++) {
                    ((ImageView)stars[j]).setImageResource(android.R.drawable.btn_star_big_on);
                    ((ImageView)stars[j]).setColorFilter(getResources().getColor(android.R.color.holo_orange_light));
                }
                for (int j = index + 1; j < stars.length; j++) {
                    ((ImageView)stars[j]).setImageResource(android.R.drawable.btn_star_big_off);
                    ((ImageView)stars[j]).setColorFilter(getResources().getColor(android.R.color.darker_gray));
                }
                Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private List<Project> getDummyProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Omaxe Twin Towers", "Sector 93B, Noida", "₹ 1.85 - 3.20 Cr", "3, 4 BHK Apartments", "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00"));
        projects.add(new Project("Godrej Woods", "Sector 43, Noida", "₹ 2.10 - 4.50 Cr", "2, 3, 4 BHK Flats", "https://images.unsplash.com/photo-1512917774080-9991f1c4c750"));
        return projects;
    }

    private void openSearch(String tab) {
        Intent intent = new Intent(this, SearchPropertyActivity.class);
        intent.putExtra("tab", tab);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) adapter.notifyDataSetChanged();
    }
}