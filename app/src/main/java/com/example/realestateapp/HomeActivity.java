package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvProperties, rvNewLaunches;
    private PropertyAdapter adapter;
    private ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Recommended Properties (2-row grid)
        rvProperties = findViewById(R.id.rvProperties);
        rvProperties.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        adapter = new PropertyAdapter(this, PropertyRepository.getInstance().getProperties());
        rvProperties.setAdapter(adapter);

        // Newly Launched Projects (1-row horizontal)
        rvNewLaunches = findViewById(R.id.rvNewLaunches);
        rvNewLaunches.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        
        List<Project> dummyProjects = new ArrayList<>();
        dummyProjects.add(new Project("SLV Golden Towers", "Kogilu, Bangalore", "₹1.27 - 2.59 Cr", "2, 3, 4 BHK Apartments"));
        dummyProjects.add(new Project("Green Estates", "Noida Sector 150", "₹85 L - 1.2 Cr", "Plot / Land"));
        dummyProjects.add(new Project("Royal Residency", "Gurgaon Sector 65", "₹3.5 - 5.0 Cr", "Luxury Villas"));
        
        projectAdapter = new ProjectAdapter(this, dummyProjects);
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