package com.example.nutrionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class AddCatalogActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    // UI Components
    private ImageView productImageView;
    private EditText productNameEditText, productDescriptionEditText, productPriceEditText, productRatingEditText;
    private Button saveButton;
    private ImageView gifImageView;

    // Variables
    private Uri selectedImageUri;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catalog);

        // Initialize UI components
        productImageView = findViewById(R.id.add_product_image);
        productNameEditText = findViewById(R.id.input_product_name);
        productDescriptionEditText = findViewById(R.id.input_product_description);
        productPriceEditText = findViewById(R.id.input_product_price);
        productRatingEditText = findViewById(R.id.input_product_rating);
        saveButton = findViewById(R.id.btn_save_catalog);
        gifImageView = findViewById(R.id.gif_image);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Product");

        // Enable the back button (up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Handle the back button click
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Set listeners for image selection and save button
        productImageView.setOnClickListener(v -> openImagePicker());
        saveButton.setOnClickListener(v -> handleSaveProduct());

        // Load the GIF into the ImageView using Glide
        Glide.with(this)
                .load(R.drawable.sample_gif) // Replace with your GIF file
                .into(gifImageView);
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            productImageView.setImageURI(selectedImageUri);
        } else {
            Toast.makeText(this, "Image selection failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleSaveProduct() {
        if (validateInputs()) {
            String productName = productNameEditText.getText().toString();
            String productDescription = productDescriptionEditText.getText().toString();
            double productPrice = Double.parseDouble(productPriceEditText.getText().toString());
            double productRating = Double.parseDouble(productRatingEditText.getText().toString());
            String productImageUri = selectedImageUri != null ? selectedImageUri.toString() : "";

            // Save product details to the database
            long result = databaseHelper.insertProduct(productName, productDescription, productPrice, productRating, productImageUri);

            if (result != -1) {
                Toast.makeText(this, "Product saved successfully!", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity after successful save
            } else {
                Toast.makeText(this, "Failed to save product. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (productNameEditText.getText().toString().trim().isEmpty()) {
            productNameEditText.setError("Product name is required");
            isValid = false;
        }

        if (productDescriptionEditText.getText().toString().trim().isEmpty()) {
            productDescriptionEditText.setError("Product description is required");
            isValid = false;
        }

        if (productPriceEditText.getText().toString().trim().isEmpty()) {
            productPriceEditText.setError("Product price is required");
            isValid = false;
        } else {
            try {
                double price = Double.parseDouble(productPriceEditText.getText().toString().trim());
                if (price <= 0) {
                    productPriceEditText.setError("Price must be greater than 0");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                productPriceEditText.setError("Invalid price");
                isValid = false;
            }
        }

        if (productRatingEditText.getText().toString().trim().isEmpty()) {
            productRatingEditText.setError("Product rating is required");
            isValid = false;
        } else {
            try {
                double rating = Double.parseDouble(productRatingEditText.getText().toString().trim());
                if (rating < 0 || rating > 5) {
                    productRatingEditText.setError("Rating must be between 0 and 5");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                productRatingEditText.setError("Invalid rating");
                isValid = false;
            }
        }

        return isValid;
    }


}
