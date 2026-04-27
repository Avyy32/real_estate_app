package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvProperties, rvNewLaunches;
    private PropertyAdapter adapter;
    private ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Recommended Properties
        rvProperties = findViewById(R.id.rvProperties);
        rvProperties.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        adapter = new PropertyAdapter(this, PropertyRepository.getInstance().getProperties());
        rvProperties.setAdapter(adapter);

        // Newly Launched Projects
        rvNewLaunches = findViewById(R.id.rvNewLaunches);
        rvNewLaunches.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        
        List<Project> projects = new ArrayList<>();
        
        // Towers Project
        Project towers = new Project("Omaxe Twin Towers", "Sector 93B, Noida", "₹ 1.85 - 3.20 Cr", "3, 4 BHK Apartments", 
            "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00");
        towers.setAdditionalImages(Arrays.asList(
            "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00",
            "https://images.unsplash.com/photo-1512917774080-9991f1c4c750",
            "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab",
            "https://images.unsplash.com/photo-1560518883-ce09059eeffa",
            "https://images.unsplash.com/photo-1564013799919-ab600027ffc6"
        ));
        projects.add(towers);

        Project project2 = new Project("Godrej Woods", "Sector 43, Noida", "₹ 2.10 - 4.50 Cr", "2, 3, 4 BHK Flats",
            "https://images.unsplash.com/photo-1512917774080-9991f1c4c750");
        projects.add(project2);

        Project project3 = new Project("M3M Heights", "Sector 65, Gurgaon", "₹ 1.90 - 3.80 Cr", "2, 3 BHK Luxury Flats",
            "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab");
        projects.add(project3);

        projectAdapter = new ProjectAdapter(this, projects);
        rvNewLaunches.setAdapter(projectAdapter);

        // Click listeners
        findViewById(R.id.btnPostProperty).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, PostPropertyBasicActivity.class));
        });

        findViewById(R.id.catBuy).setOnClickListener(v -> openSearch("Buy"));
        findViewById(R.id.catRent).setOnClickListener(v -> openSearch("Rent"));
        findViewById(R.id.catCommercial).setOnClickListener(v -> openSearch("Commercial"));

        findViewById(R.id.homeSearchBar).setOnClickListener(v -> openSearch("Buy"));

        // Tools Click listeners
        findViewById(R.id.cardBudgetCalculator).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, BudgetCalculatorActivity.class));
        });
        
        findViewById(R.id.cardEmiCalculator).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, EmiCalculatorActivity.class));
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) return true;
            if (item.getItemId() == R.id.nav_sell_rent) {
                startActivity(new Intent(this, PostPropertyBasicActivity.class));
                return true;
            }
            if (item.getItemId() == R.id.nav_shortlist) {
                startActivity(new Intent(this, MapActivity.class));
                return true;
            }
            if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
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