package com.example.nutrionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class VisaCardActivity extends AppCompatActivity {

    private EditText edtCardNumber, edtExpiryDate, edtCVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_card);

        // Initialize views
        edtCardNumber = findViewById(R.id.edtCardNumber);
        edtExpiryDate = findViewById(R.id.edtExpiryDate);
        edtCVV = findViewById(R.id.edtCVV);
        Button btnSubmitPayment = findViewById(R.id.btnSubmitPayment);
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Set up the toolbar with a title and enable back button
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Show the back button
        getSupportActionBar().setTitle("Visa Card");  // Set the title

        // Set OnClickListener for the submit button
        btnSubmitPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate card details
                if (validateCardDetails()) {
                    // Process payment or show success message
                    Toast.makeText(VisaCardActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                    // Optionally, navigate to another screen or close the activity
                    finish(); // Close the VisaCardActivity
                }
            }
        });
    }

    private boolean validateCardDetails() {
        String cardNumber = edtCardNumber.getText().toString().trim();
        String expiryDate = edtExpiryDate.getText().toString().trim();
        String cvv = edtCVV.getText().toString().trim();

        // Validate card number (16 digits)
        if (cardNumber.isEmpty() || cardNumber.length() != 16) {
            edtCardNumber.setError("Please enter a valid 16-digit card number");
            return false;
        }

        // Validate expiry date (MM/YY)
        if (expiryDate.isEmpty() || !expiryDate.matches("(0[1-9]|1[0-2])\\/([0-9]{2})")) {
            edtExpiryDate.setError("Please enter a valid expiry date (MM/YY)");
            return false;
        }

        // Validate CVV (3 digits)
        if (cvv.isEmpty() || cvv.length() != 3) {
            edtCVV.setError("Please enter a valid 3-digit CVV");
            return false;
        }

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Handle the back button click
        return true;
    }
}
