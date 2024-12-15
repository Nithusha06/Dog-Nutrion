package com.example.nutrionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewProductsActivity extends AppCompatActivity {

    private Spinner spinnerBrand, spinnerType, spinnerAge;
    private Button btnSort;
    private RecyclerView recyclerViewProducts;
    private ProductItemAdapter productAdapter;
    private List<ProductItem> productList;
    public static List<ProductItem> cartList = new ArrayList<>(); // Static cart list to share data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        // Initialize Views
        spinnerBrand = findViewById(R.id.spinnerBrand);
        spinnerType = findViewById(R.id.spinnerType);
        spinnerAge = findViewById(R.id.spinnerAge);
        btnSort = findViewById(R.id.btnSort);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        // Set up RecyclerView
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        productAdapter = new ProductItemAdapter(productList, this::addToCart);
        recyclerViewProducts.setAdapter(productAdapter);

        // Set up Toolbar and enable back navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the toolbar title to "View Products"
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back button
            getSupportActionBar().setTitle("View Products"); // Set the title
        }

        // Populate Spinner with data
        ArrayAdapter<CharSequence> brandAdapter = ArrayAdapter.createFromResource(this, R.array.brands, android.R.layout.simple_spinner_item);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(brandAdapter);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(typeAdapter);

        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this, R.array.ages, android.R.layout.simple_spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(ageAdapter);

        // Change the selected item text color for all spinners
        setSpinnerItemTextColor(spinnerBrand);
        setSpinnerItemTextColor(spinnerType);
        setSpinnerItemTextColor(spinnerAge);

        // Simulate some products (This should ideally come from your database)
        populateProducts();

        // Sort button action
        btnSort.setOnClickListener(v -> {
            String selectedBrand = spinnerBrand.getSelectedItem().toString();
            String selectedType = spinnerType.getSelectedItem().toString();
            String selectedAge = spinnerAge.getSelectedItem().toString();

            // Filter products based on selected criteria
            filterProducts(selectedBrand, selectedType, selectedAge);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Handle the back navigation
        return true;
    }

    private void populateProducts() {
        // For illustration, add sample products (You can fetch these from a server or database)
        productList.add(new ProductItem("Pedigree Dry Food", "Pedigree", "Dry Food", "Puppy (0-12 months)", "$20", R.drawable.prod1));
        productList.add(new ProductItem("Royal Canin Wet Food", "Royal Canin", "Wet Food", "Adult (1-7 years)", "$25", R.drawable.prod1));
        productList.add(new ProductItem("Purina High-Protein Food", "Purina Pro Plan", "High-Protein Food", "Senior (7+ years)", "$30", R.drawable.prod1));
        productAdapter.notifyDataSetChanged();
    }

    private void filterProducts(String brand, String type, String ageGroup) {
        List<ProductItem> filteredList = new ArrayList<>();

        // Filter the products based on selected criteria
        for (ProductItem productItem : productList) {
            boolean matchesBrand = brand.equals("All Brands") || productItem.getBrand().equals(brand);
            boolean matchesType = type.equals("All Types") || productItem.getType().equals(type);
            boolean matchesAgeGroup = ageGroup.equals("All Age Groups") || productItem.getAgeGroup().equals(ageGroup);

            if (matchesBrand && matchesType && matchesAgeGroup) {
                filteredList.add(productItem);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No products found for the selected criteria", Toast.LENGTH_SHORT).show();
        }

        // Update the RecyclerView with filtered list
        productAdapter.updateProducts(filteredList);
    }

    // Method to change the selected item text color
    private void setSpinnerItemTextColor(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (selectedItemView != null) {
                    TextView selectedItemText = (TextView) selectedItemView;
                    selectedItemText.setTextColor(getResources().getColor(R.color.selected_spinner_item)); // Customize the color
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    // Add product to cart and navigate to CartActivity
    private void addToCart(ProductItem product) {
        cartList.add(product); // Add to static cart list
        Toast.makeText(this, product.getName() + " added to cart!", Toast.LENGTH_SHORT).show();

        // Navigate to CartActivity
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }
}
