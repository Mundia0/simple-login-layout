package com.example.myapplicationt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Hide the action bar if you have one
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize logout button
        Button logoutButton = findViewById(R.id.logout_button);

        // Set click listener for logout button
        logoutButton.setOnClickListener(v -> {
            // Navigate back to MainActivity
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Close the HomeActivity
        });
    }
}
