package com.example.umpweek9;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

    @Nullable
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
        btnLogout.setOnClickListener(v -> showConfirmationDialog("Logout", "Are you sure you want to logout?", this::logout));

        // Exit button action
        btnExit.setOnClickListener(v -> showConfirmationDialog("Exit", "Are you sure you want to exit?", this::exit));

        // Change password button action
        btnChangePassword.setOnClickListener(v -> changePassword());

        return view;
    }

    private void logout() {
        if (getActivity() != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();

            // Navigate back to the login screen
            Intent intent = new Intent(getActivity(), LoginActivity.class);  // Replace with your LoginActivity
            startActivity(intent);
            getActivity().finish();  // Finish the current activity to prevent user from navigating back
        }
    }

    private void exit() {
        if (getActivity() != null) { // Ensure the activity is valid and attached
            getActivity().finishAffinity(); // Close all activities in the stack
            System.exit(0); // Optional, but could be used to ensure complete app termination
        }
    }

    private void changePassword() {
        String newPassword = editPassword.getText().toString();
        if (newPassword.isEmpty()) {
            Toast.makeText(getContext(), "Enter a new password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate password strength (optional)
        if (newPassword.length() < 6) {
            Toast.makeText(getContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Password updated", Toast.LENGTH_SHORT).show();
                            editPassword.setText("");  // Clear input
                        } else {
                            Toast.makeText(getContext(), "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getContext(), "User not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void showConfirmationDialog(String title, String message, Runnable onConfirm) {
        if (getContext() != null) {
            new AlertDialog.Builder(getContext())
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Yes", (dialog, which) -> onConfirm.run())
                    .setNegativeButton("No", null)
                    .show();
        }
    }
}
