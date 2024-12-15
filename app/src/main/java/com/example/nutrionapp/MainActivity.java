package com.example.nutrionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Log to confirm MainActivity is loaded
        Log.d(TAG, "MainActivity onCreate");

        Button catalogButton = findViewById(R.id.catalogbutton);
        catalogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Catalog Button clicked");

                // Navigate to CatalogActivity
                Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                startActivity(intent);
            }
        });


        Button cartButton = findViewById(R.id.ShoppingCartbutton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Catalog Button clicked");

                // Navigate to CatalogActivity
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        Button educationalButton = findViewById(R.id.Edubutton);
        educationalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Catalog Button clicked");

                // Navigate to CatalogActivity
                Intent intent = new Intent(MainActivity.this, EducationalContentActivity.class);
                startActivity(intent);
            }
        });
    }
}
