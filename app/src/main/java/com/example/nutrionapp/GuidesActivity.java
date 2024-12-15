package com.example.nutrionapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class GuidesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guides);

        // Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up navigation arrow
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back arrow
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_directions); // Custom back arrow (optional)
            getSupportActionBar().setTitle("Dog Nutrition Guide"); // Set Toolbar title
        }
    }

    // Handle navigation arrow click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate back when the back arrow is clicked
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
