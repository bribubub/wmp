package com.example.umpweek9;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get references to views
        ImageView imageView = findViewById(R.id.detail_image_view);
        TextView nameTextView = findViewById(R.id.detail_name_text_view);
        TextView descriptionTextView = findViewById(R.id.detail_description_text_view);
        TextView bestLocationsTextView = findViewById(R.id.detail_best_locations_text_view);

        // Retrieve data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String description = intent.getStringExtra("description");
            int imageResId = intent.getIntExtra("imageResId", R.drawable.bali1); // Default image
            String bestLocations = intent.getStringExtra("bestLocations");

            if (name != null && description != null && bestLocations != null) {
                // Set data to views
                imageView.setImageResource(imageResId);
                nameTextView.setText(name);
                descriptionTextView.setText(description);
                bestLocationsTextView.setText(bestLocations);
            } else {
                Toast.makeText(this, "Missing data", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Intent is null", Toast.LENGTH_SHORT).show();
        }
    }
}