package com.example.umpweek9;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;  // <-- Add this import to resolve the ArrayList issue

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> implements Filterable {

    private final Context context;
    private final List<Destination> destinationList;
    private List<Destination> filteredList;

    public DestinationAdapter(Context context, List<Destination> destinationList) {
        this.context = context;
        this.destinationList = destinationList;
        this.filteredList = destinationList;  // Initially, the filtered list is the same as the full list
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_destination, parent, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        Destination destination = filteredList.get(position);
        holder.nameTextView.setText(destination.getName());
        holder.descriptionTextView.setText(destination.getDescription());
        holder.imageView.setImageResource(destination.getImageResId());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("name", destination.getName());
            intent.putExtra("description", destination.getDescription());
            intent.putExtra("imageResId", destination.getImageResId());
            intent.putExtra("bestLocations", "1. Spot A\n2. Spot B\n3. Spot C");

            // Check if the data is valid before launching the activity
            if (destination.getName() != null && destination.getDescription() != null) {
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Invalid destination data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Destination> filteredResults = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredResults.addAll(destinationList);  // If no filter, return all items
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Destination destination : destinationList) {
                        if (destination.getName().toLowerCase().contains(filterPattern)) {
                            filteredResults.add(destination);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredResults;
                results.count = filteredResults.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                if (results.values != null) {
                    filteredList.addAll((List) results.values);
                }
                notifyDataSetChanged();
            }
        };
    }

    public static class DestinationViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, descriptionTextView;

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
        }
    }
}
