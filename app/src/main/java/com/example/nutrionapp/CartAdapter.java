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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<CartItem> cartItemList;
    private final Runnable onCartItemUpdated;

    public CartAdapter(List<CartItem> cartItemList, Runnable onCartItemUpdated) {
        this.cartItemList = cartItemList;
        this.onCartItemUpdated = onCartItemUpdated;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);

        holder.productImage.setImageResource(cartItem.getImageResId());
        holder.productName.setText(cartItem.getName());
        holder.productPrice.setText(cartItem.getPrice());
        holder.quantity.setText(String.valueOf(cartItem.getQuantity()));

        // Increase quantity
        holder.increaseBtn.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            notifyItemChanged(position);
            onCartItemUpdated.run();
        });

        // Decrease quantity
        holder.decreaseBtn.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                notifyItemChanged(position);
                onCartItemUpdated.run();
            }
        });

        // Remove item
        holder.removeBtn.setOnClickListener(v -> {
            cartItemList.remove(position);
            notifyItemRemoved(position);
            onCartItemUpdated.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, quantity;
        Button increaseBtn, decreaseBtn, removeBtn;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            quantity = itemView.findViewById(R.id.quantity);
            increaseBtn = itemView.findViewById(R.id.increaseBtn);
            decreaseBtn = itemView.findViewById(R.id.decreaseBtn);
            removeBtn = itemView.findViewById(R.id.removeBtn);
        }
    }
}
