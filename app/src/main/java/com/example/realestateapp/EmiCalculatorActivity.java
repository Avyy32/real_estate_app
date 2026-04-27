package com.example.realestateapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.slider.Slider;

public class EmiCalculatorActivity extends AppCompatActivity {

    private TextView tvLoanAmount, tvInterestRate, tvTenure, tvEmiResult;
    private Slider sliderLoan, sliderInterest, sliderTenure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);

        tvLoanAmount = findViewById(R.id.tvLoanAmountValue);
        tvInterestRate = findViewById(R.id.tvInterestRateValue);
        tvTenure = findViewById(R.id.tvEmiTenureValue);
        tvEmiResult = findViewById(R.id.tvEmiResult);
        
        sliderLoan = findViewById(R.id.sliderLoanAmount);
        sliderInterest = findViewById(R.id.sliderInterestRate);
        sliderTenure = findViewById(R.id.sliderEmiTenure);

        calculateEmi();

        sliderLoan.addOnChangeListener((slider, value, fromUser) -> {
            tvLoanAmount.setText("₹ " + (int)value + " Crores");
            calculateEmi();
        });

        sliderInterest.addOnChangeListener((slider, value, fromUser) -> {
            tvInterestRate.setText(String.format("%.1f", value) + "%");
            calculateEmi();
        });

        sliderTenure.addOnChangeListener((slider, value, fromUser) -> {
            tvTenure.setText((int)value + " years");
            calculateEmi();
        });

        findViewById(R.id.toolbar).setOnClickListener(v -> finish());
    }

    private void calculateEmi() {
        double p = sliderLoan.getValue() * 10000000; // Crores to Rs
        double r = sliderInterest.getValue() / (12 * 100);
        double n = sliderTenure.getValue() * 12;

        double emi = (p * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        
        if (emi > 100000) {
            tvEmiResult.setText("₹ " + String.format("%.2f", emi / 100000) + " Lacs");
        } else {
            tvEmiResult.setText("₹ " + String.format("%,d", (int)emi));
        }
    }
}