package com.example.realestateapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.slider.Slider;

public class BudgetCalculatorActivity extends AppCompatActivity {

    private TextView tvSavings, tvEmi, tvTenure, tvBudgetResult;
    private Slider sliderSavings, sliderEmi, sliderTenure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_calculator);

        tvSavings = findViewById(R.id.tvSavingsValue);
        tvEmi = findViewById(R.id.tvEmiValue);
        tvTenure = findViewById(R.id.tvTenureValue);
        tvBudgetResult = findViewById(R.id.tvBudgetResult);
        
        sliderSavings = findViewById(R.id.sliderSavings);
        sliderEmi = findViewById(R.id.sliderEmi);
        sliderTenure = findViewById(R.id.sliderTenure);

        updateCalculations();

        if (sliderSavings != null) {
            sliderSavings.addOnChangeListener((slider, value, fromUser) -> {
                tvSavings.setText("₹ " + (int)value + " Lacs");
                updateCalculations();
            });
        }

        if (sliderEmi != null) {
            sliderEmi.addOnChangeListener((slider, value, fromUser) -> {
                tvEmi.setText("₹ " + String.format("%,d", (int)value));
                updateCalculations();
            });
        }

        if (sliderTenure != null) {
            sliderTenure.addOnChangeListener((slider, value, fromUser) -> {
                tvTenure.setText((int)value + " years");
                updateCalculations();
            });
        }

        if (findViewById(R.id.toolbar) != null) {
            findViewById(R.id.toolbar).setOnClickListener(v -> finish());
        }
    }

    private void updateCalculations() {
        if (sliderSavings == null || sliderEmi == null || sliderTenure == null) return;
        
        int savings = (int) sliderSavings.getValue();
        int emi = (int) sliderEmi.getValue();
        int years = (int) sliderTenure.getValue();
        
        int resultMin = savings + (emi * years * 12 / 100000);
        int resultMax = resultMin + 5;
        
        if (tvBudgetResult != null) {
            tvBudgetResult.setText("₹ " + resultMin + " - " + resultMax + " Lacs");
        }
    }
}


