package com.example.nutrionapp;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productNameTextView.setText(product.getName());
        holder.productDescriptionTextView.setText(product.getDescription());
        holder.productPriceTextView.setText("₹" + product.getPrice());
        holder.productRatingTextView.setText("Rating: " + product.getRating());

        String imageUri = product.getImageUri();
        if (imageUri != null && !imageUri.isEmpty()) {
            Glide.with(holder.productImageView.getContext())
                    .load(Uri.parse(imageUri))
                    .into(holder.productImageView);
        } else {
            holder.productImageView.setImageResource(R.drawable.prod1); // Default image
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView productDescriptionTextView;
        private TextView productPriceTextView;
        private TextView productRatingTextView;
        private ImageView productImageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productDescriptionTextView = itemView.findViewById(R.id.product_description);
            productPriceTextView = itemView.findViewById(R.id.product_price);
            productRatingTextView = itemView.findViewById(R.id.product_rating);
            productImageView = itemView.findViewById(R.id.product_image);
        }
    }
}
