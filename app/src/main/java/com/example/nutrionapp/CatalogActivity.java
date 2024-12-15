package com.example.nutrionapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private DatabaseHelper databaseHelper;  // Database helper instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Initialize product list
        productList = new ArrayList<>();

        // Fetch products from the database
        productList = databaseHelper.getAllProducts();

        if (productList == null || productList.isEmpty()) {
            Log.d("CatalogActivity", "No products found in the database.");
        }

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize product adapter and set it to RecyclerView
        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);
        Log.d("CatalogActivity", "RecyclerView initialized successfully");

        // Set up Floating Action Button (fab_add)
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        if (fabAdd == null) {
            Log.e("CatalogActivity", "Floating Action Button not found");
            return; // Exit if the button is not found
        }
        fabAdd.setOnClickListener(v -> {
            // Navigate to AddCatalogActivity when fab_add is clicked
            Log.d("CatalogActivity", "Floating Action Button clicked");
            Intent intent = new Intent(CatalogActivity.this, AddCatalogActivity.class);
            startActivity(intent);
        });

        // Set up the "View Products" button (btn_view_products)
        FloatingActionButton btnViewProducts = findViewById(R.id.btn_view_products);
        if (btnViewProducts != null) {
            btnViewProducts.setOnClickListener(v -> {
                Log.d("CatalogActivity", "View Products button clicked");
                // Navigate to ViewProductsActivity when btn_view_products is clicked
                Intent intent = new Intent(CatalogActivity.this, ViewProductsActivity.class);
                startActivity(intent);
            });
        }

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back arrow in the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Product Catalog");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back arrow
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload products from the database when returning to this activity
        productList.clear();
        productList.addAll(databaseHelper.getAllProducts());
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle toolbar item clicks
        if (item.getItemId() == android.R.id.home) {
            // Handle the back arrow click to finish the activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}