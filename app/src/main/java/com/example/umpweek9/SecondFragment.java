    package com.example.umpweek9;

    import android.os.Bundle;
    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Toast;
    import android.text.Editable;
    import android.text.TextWatcher;
    import android.widget.EditText;



    import java.util.ArrayList;
    import java.util.List;

    public class SecondFragment extends Fragment {

        private EditText searchBar;
        private RecyclerView recyclerView;
        private DestinationAdapter adapter;
        private List<Destination> destinationList;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_second, container, false);

            // Debugging Toast (can remove later)
            Toast.makeText(getContext(), "Fragment 2 loaded", Toast.LENGTH_SHORT).show();

            // Initialize RecyclerView
            recyclerView = view.findViewById(R.id.recycler_view_destinations);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Initialize Destination Data
            searchBar = view.findViewById(R.id.search_bar);
            destinationList = new ArrayList<>();
            loadDestinations();

            // Set Adapter (pass context here)
            adapter = new DestinationAdapter(getContext(), destinationList);
            recyclerView.setAdapter(adapter);

            // Add TextWatcher to handle search
            searchBar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s); // Filter the adapter based on input
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });

            return view;
        }

        private void loadDestinations() {
            // Add some sample destinations
            destinationList.add(new Destination("Bali", "Beautiful beaches and culture", R.drawable.bali1));
            destinationList.add(new Destination("Paris", "The City of Love", R.drawable.paris1));
            destinationList.add(new Destination("New York", "The city that never sleeps", R.drawable.newyork));
            destinationList.add(new Destination("Japan", "Japanese City rule are the best", R.drawable.japan));
        }
    }
