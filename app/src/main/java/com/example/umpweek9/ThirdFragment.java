package com.example.umpweek9;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ThirdFragment extends Fragment {

    private Button btnLogout, btnExit, btnChangePassword;
    private EditText editPassword;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        // Initialize Buttons and EditTexts
        btnLogout = view.findViewById(R.id.btnLogout);
        btnExit = view.findViewById(R.id.btnExit);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        editPassword = view.findViewById(R.id.editPassword);

        // Logout button action
        btnLogout.setOnClickListener(v -> logout());

        // Exit button action
        btnExit.setOnClickListener(v -> exit());

        // Change password button action
        btnChangePassword.setOnClickListener(v -> changePassword());

        return view;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();

        // Navigate back to the login screen
        Intent intent = new Intent(getActivity(), LoginActivity.class);  // Replace with your LoginActivity
        startActivity(intent);
        getActivity().finish();  // Finish the current activity to prevent user from navigating back
    }

    private void exit() {
        getActivity().finish(); // Exit the application
    }

    private void changePassword() {
        String newPassword = editPassword.getText().toString();
        if (!newPassword.isEmpty()) {
            FirebaseAuth.getInstance().getCurrentUser().updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Password updated", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getContext(), "Enter a new password", Toast.LENGTH_SHORT).show();
        }
    }
}
