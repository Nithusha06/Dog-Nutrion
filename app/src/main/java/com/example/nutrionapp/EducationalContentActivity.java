package com.example.nutrionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EducationalContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_content);

        // Set up the toolbar as the ActionBar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button in the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Educational Content");
        }

        // Find the Articles button by its ID
        Button articleButton = findViewById(R.id.article_button);

        // Set an OnClickListener to the button
        articleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the ArticlesActivity
                Intent intent = new Intent(EducationalContentActivity.this, ArticlesActivity.class);
                startActivity(intent);
            }
        });

        Button videoButton = findViewById(R.id.videobtn);

        // Set an OnClickListener to the button
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the ArticlesActivity
                Intent intent = new Intent(EducationalContentActivity.this, VideosActivity.class);
                startActivity(intent);
            }
        });

        Button guideButton = findViewById(R.id.Guidesbtn);

        // Set an OnClickListener to the button
        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the ArticlesActivity
                Intent intent = new Intent(EducationalContentActivity.this, GuidesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button press
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back to the previous screen
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
