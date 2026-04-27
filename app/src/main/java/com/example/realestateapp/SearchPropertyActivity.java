package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.List;

public class SearchPropertyActivity extends AppCompatActivity {

    private String currentTab = "Buy";
    private TextView tabBuy, tabRent, tabCommercial;
    private EditText etSearch;
    private RecyclerView rvResults;
    private PropertyAdapter adapter;
    private List<Property> filteredList = new ArrayList<>();
    private View layoutSuggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_property);

        currentTab = getIntent().getStringExtra("tab");
        if (currentTab == null) currentTab = "Buy";

        tabBuy = findViewById(R.id.tabBuy);
        tabRent = findViewById(R.id.tabRent);
        tabCommercial = findViewById(R.id.tabCommercial);
        etSearch = findViewById(R.id.etSearchLocation);
        rvResults = findViewById(R.id.rvSearchResults);
        layoutSuggestions = findViewById(R.id.layoutSuggestions);

        rvResults.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PropertyAdapter(this, filteredList);
        rvResults.setAdapter(adapter);

        updateTabUI();

        tabBuy.setOnClickListener(v -> { currentTab = "Buy"; updateTabUI(); performSearch(etSearch.getText().toString()); });
        tabRent.setOnClickListener(v -> { currentTab = "Rent"; updateTabUI(); performSearch(etSearch.getText().toString()); });
        tabCommercial.setOnClickListener(v -> { currentTab = "Commercial"; updateTabUI(); performSearch(etSearch.getText().toString()); });

        findViewById(R.id.btnClose).setOnClickListener(v -> finish());

        ChipGroup popularCities = findViewById(R.id.cgPopularCities);
        for (int i = 0; i < popularCities.getChildCount(); i++) {
            View child = popularCities.getChildAt(i);
            if (child instanceof Chip) {
                Chip chip = (Chip) child;
                chip.setOnClickListener(v -> {
                    String city = chip.getText().toString().replace("+ ", "");
                    etSearch.setText(city);
                    performSearch(city);
                });
            }
        }

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void updateTabUI() {
        tabBuy.setBackgroundResource(currentTab.equals("Buy") ? R.drawable.search_tab_selected : 0);
        tabBuy.setTextColor(currentTab.equals("Buy") ? getResources().getColor(R.color.primary) : getResources().getColor(R.color.white));

        tabRent.setBackgroundResource(currentTab.equals("Rent") ? R.drawable.search_tab_selected : 0);
        tabRent.setTextColor(currentTab.equals("Rent") ? getResources().getColor(R.color.primary) : getResources().getColor(R.color.white));

        tabCommercial.setBackgroundResource(currentTab.equals("Commercial") ? R.drawable.search_tab_selected : 0);
        tabCommercial.setTextColor(currentTab.equals("Commercial") ? getResources().getColor(R.color.primary) : getResources().getColor(R.color.white));
        
        etSearch.setHint("Try - " + currentTab + " in Noida");
    }

    private void performSearch(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            layoutSuggestions.setVisibility(View.VISIBLE);
            rvResults.setVisibility(View.GONE);
        } else {
            layoutSuggestions.setVisibility(View.GONE);
            rvResults.setVisibility(View.VISIBLE);
            
            List<Property> allProperties = PropertyRepository.getInstance().getProperties();
            for (Property p : allProperties) {
                // Check if city matches and category matches (Buy/Rent/Commercial)
                boolean matchesCategory = false;
                if (currentTab.equals("Buy") && p.getLookingTo().equalsIgnoreCase("Sell")) matchesCategory = true;
                else if (currentTab.equals("Rent") && (p.getLookingTo().equalsIgnoreCase("Rent / Lease") || p.getLookingTo().equalsIgnoreCase("Paying Guest"))) matchesCategory = true;
                else if (currentTab.equals("Commercial") && p.getPropertyType().equalsIgnoreCase("Commercial")) matchesCategory = true;

                if (matchesCategory && (p.getLocation().toLowerCase().contains(query.toLowerCase()) || 
                    p.getName().toLowerCase().contains(query.toLowerCase()))) {
                    filteredList.add(p);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}