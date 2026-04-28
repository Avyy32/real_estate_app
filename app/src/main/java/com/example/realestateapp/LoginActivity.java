package com.example.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.*;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private Button btnContinue, btnSkip, btnVerify;
    private EditText phoneInput, otpInput;

    private FirebaseAuth mAuth;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnContinue = findViewById(R.id.btnContinue);
        btnSkip = findViewById(R.id.btnSkip);
        phoneInput = findViewById(R.id.phoneInput);

        // NEW VIEWS (make sure you added them in XML)
        otpInput = findViewById(R.id.otpInput);
        btnVerify = findViewById(R.id.btnVerify);

        mAuth = FirebaseAuth.getInstance();

        // 👉 SEND OTP
        btnContinue.setOnClickListener(v -> {
            String phone = phoneInput.getText().toString().trim();

            if (phone.length() != 10) {
                Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            sendOtp("+91" + phone);
        });

        // 👉 VERIFY OTP
        btnVerify.setOnClickListener(v -> {
            String otp = otpInput.getText().toString().trim();

            if (otp.isEmpty()) {
                Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show();
                return;
            }

            verifyOtp(otp);
        });

        // 👉 SKIP LOGIN
        btnSkip.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        });
    }

    // 🔹 SEND OTP
    private void sendOtp(String phoneNumber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

        Toast.makeText(this, "OTP Sent", Toast.LENGTH_SHORT).show();

        // SHOW OTP FIELD
        otpInput.setVisibility(View.VISIBLE);
        btnVerify.setVisibility(View.VISIBLE);
    }

    // 🔹 CALLBACKS
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                    signInUser(credential);
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(LoginActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCodeSent(@NonNull String id,
                                       @NonNull PhoneAuthProvider.ForceResendingToken token) {
                    verificationId = id;
                }
            };

    // 🔹 VERIFY OTP
    private void verifyOtp(String otp) {
        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(verificationId, otp);

        signInUser(credential);
    }

    // 🔹 LOGIN SUCCESS
    private void signInUser(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();

                    } else {
                        Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}