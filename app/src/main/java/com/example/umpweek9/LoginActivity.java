package com.example.umpweek9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login;
    TextView goToRegister;  // Make sure it's a TextView
    FirebaseAuth auth;

    SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "LoginPrefs";  // SharedPreferences file name
    private static final String KEY_USERNAME = "username";  // SharedPreferences key for username

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        goToRegister = findViewById(R.id.goToRegister);  // Correct ID
        auth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Check if there's a stored username and autofill
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
        if (!TextUtils.isEmpty(savedUsername)) {
            username.setText(savedUsername);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(un) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "Input username and password", Toast.LENGTH_LONG).show();
                } else {
                    loginUser(un, pass);
                }
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser(String username, String password) {
        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

                    // Save the username in SharedPreferences after successful login
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_USERNAME, username);  // Save the username
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
