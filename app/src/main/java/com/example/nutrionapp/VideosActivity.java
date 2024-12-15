package com.example.nutrionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class VideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        // Setup the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Videos"); // Set title for the toolbar
        setSupportActionBar(toolbar);

        // Enable the back arrow in the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set toolbar navigation click listener
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        // Find TextView elements
        TextView video1 = findViewById(R.id.video1);
        TextView video2 = findViewById(R.id.video2);
        TextView video3 = findViewById(R.id.video3);

        // Set onClick listeners for each text
        video1.setOnClickListener(view -> openYouTubeLink("https://youtu.be/RM5Lvfk39Dg?si=ko5nCKilofwMmkwW"));
        video2.setOnClickListener(view -> openYouTubeLink("https://youtu.be/RM5Lvfk39Dg?si=ko5nCKilofwMmkwW"));
        video3.setOnClickListener(view -> openYouTubeLink("https://youtu.be/RM5Lvfk39Dg?si=ko5nCKilofwMmkwW"));
    }

    // Helper method to open a YouTube link
    private void openYouTubeLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setPackage("com.google.android.youtube"); // Explicitly set the YouTube app
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Fallback to browser
            intent.setPackage(null);
            startActivity(intent);
        }
    }
}
