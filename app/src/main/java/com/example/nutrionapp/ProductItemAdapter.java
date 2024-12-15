package com.example.nutrionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductViewHolder> {

    private List<ProductItem> productList;
    private ProductItemClickListener itemClickListener; // Listener for handling add-to-cart action

    // Constructor now accepts a ProductItemClickListener
    public ProductItemAdapter(List<ProductItem> productList, ProductItemClickListener itemClickListener) {
        this.productList = productList;
        this.itemClickListener = itemClickListener; // Initialize the listener
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the product item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_products_items, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Get the product at the current position
        ProductItem productItem = productList.get(position);

        // Bind the data to the views
        holder.productName.setText(productItem.getName());
        holder.productPrice.setText(productItem.getPrice());
        holder.productImage.setImageResource(productItem.getImageResource());

        // Set up the "Add to Cart" button click listener
        holder.addToCartButton.setOnClickListener(v -> {
            // Invoke the listener when the button is clicked
            itemClickListener.onAddToCart(productItem);
        });
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the list
        return productList.size();
    }

    // Method to update the product list and refresh the view
    public void updateProducts(List<ProductItem> newProductList) {
        this.productList = newProductList;
        notifyDataSetChanged();
    }

    // ViewHolder class to hold the references to the views
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
        Button addToCartButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            addToCartButton = itemView.findViewById(R.id.button); // Button to add product to cart
        }
    }

    // Define the interface to handle "Add to Cart" clicks
    public interface ProductItemClickListener {
        void onAddToCart(ProductItem product);
    }
}