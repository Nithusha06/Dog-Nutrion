package com.example.nutrionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nutrionapp.CartAdapter;
import com.example.nutrionapp.CartItem;
import com.example.nutrionapp.R;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;
    private TextView txtSubtotal;
    private Button btnCheckout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        txtSubtotal = findViewById(R.id.txtSubtotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        toolbar = findViewById(R.id.toolbar);

        // Set up the toolbar with a back button
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Show the back button
        getSupportActionBar().setTitle("Shopping Cart");  // Set the title

        // Initialize cart items (this would typically come from your app's data layer)
        cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem("Product 1", "$20.00", 1, R.drawable.prod1));
        cartItemList.add(new CartItem("Product 2", "$30.00", 1, R.drawable.prod1));
        cartItemList.add(new CartItem("Product 3", "$15.00", 1, R.drawable.prod1));

        // Setup RecyclerView
        cartAdapter = new CartAdapter(cartItemList, this::onCartItemUpdated);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(cartAdapter);

        // Calculate initial subtotal
        calculateSubtotal();

        // Handle checkout button click
        btnCheckout.setOnClickListener(v -> {
            // Navigate to the Visa Card UI (VisaCardActivity)
            Intent intent = new Intent(ShoppingCartActivity.this, VisaCardActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Proceeding to checkout...", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle the back button press
        onBackPressed();  // This will navigate to the previous activity
        return true;
    }

    private void onCartItemUpdated() {
        // Recalculate subtotal when the cart is updated
        calculateSubtotal();
    }

    private void calculateSubtotal() {
        double subtotal = 0.0;
        for (CartItem item : cartItemList) {
            // Convert item.getPrice() (String) to a double before multiplying
            double price = Double.parseDouble(item.getPrice().replace("$", "").trim());
            subtotal += price * item.getQuantity();
        }
        txtSubtotal.setText(String.format("Subtotal: $%.2f", subtotal));
    }
}
