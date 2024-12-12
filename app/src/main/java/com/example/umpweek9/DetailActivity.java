package com.example.umpweek9;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecommendedAdapter adapter;
    private List<RecommendedSpot> recommendedSpots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare data for the list
        recommendedSpots = new ArrayList<>();
        String destinationName = getIntent().getStringExtra("name");

        if ("Bali".equals(destinationName)) {
            recommendedSpots.add(new RecommendedSpot("Beach A", "A beautiful beach with crystal-clear water", R.drawable.bali1));
            recommendedSpots.add(new RecommendedSpot("Temple B", "A historic temple with stunning views", R.drawable.bali1));

        } else if ("Paris".equals(destinationName)) {
            recommendedSpots.add(new RecommendedSpot("Eiffel Tower", "An iconic symbol of love and Paris", R.drawable.paris1));
            recommendedSpots.add(new RecommendedSpot("Louvre Museum", "Home of the Mona Lisa and other masterpieces", R.drawable.paris1));
        } else if ("New York".equals(destinationName)) {
            recommendedSpots.add(new RecommendedSpot("Times Square", "The bustling heart of New York City", R.drawable.newyork));
            recommendedSpots.add(new RecommendedSpot("Central Park", "A green oasis in the middle of the city", R.drawable.newyork));
        } else if ("Japan".equals(destinationName)) {
            recommendedSpots.add(new RecommendedSpot("Times Square", "The bustling heart of New York City", R.drawable.newyork));
            recommendedSpots.add(new RecommendedSpot("Central Park", "A green oasis in the middle of the city", R.drawable.newyork));
        }

        // Set up adapter
        adapter = new RecommendedAdapter(recommendedSpots);
        recyclerView.setAdapter(adapter);
    }
}
