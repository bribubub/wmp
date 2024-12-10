package com.example.umpweek9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> implements Filterable {

    private List<Destination> destinationList;
    private List<Destination> destinationListFull; // Full list for filtering

    public DestinationAdapter(List<Destination> destinationList) {
        this.destinationList = new ArrayList<>(destinationList);
        this.destinationListFull = new ArrayList<>(destinationList); // Copy the original list
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destination, parent, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        Destination destination = destinationList.get(position);
        holder.nameTextView.setText(destination.getName());
        holder.descriptionTextView.setText(destination.getDescription());
        holder.imageView.setImageResource(destination.getImageResId());
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Destination> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(destinationListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Destination item : destinationListFull) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                destinationList.clear();
                destinationList.addAll((List<Destination>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    static class DestinationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        ImageView imageView;

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
