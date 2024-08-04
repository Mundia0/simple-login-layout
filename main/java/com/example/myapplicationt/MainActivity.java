package com.example.myapplicationt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputLayout username, password;
    TextView create, change_pass;
    Button login;
    LoginData ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the action bar if you have one
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.login_button);
        create = findViewById(R.id.create);
        change_pass = findViewById(R.id.change_password);

        // Initialize database helper
        ld = new LoginData(this);

        // Setup click listeners
        createUser();
        loginUser();
        changePassword();
    }

    public void loginUser() {
        login.setOnClickListener(this::onClick);
    }

    public void createUser() {
        create.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Create_Acc.class);
            startActivity(intent);
        });
    }

    public void changePassword() {
        change_pass.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChangePass.class);
            startActivity(intent);
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void onClick(View v) {
        String name = Objects.requireNonNull(username.getEditText()).getText().toString();
        String pass = Objects.requireNonNull(password.getEditText()).getText().toString();

        // Display input values (for testing)
        showMessage("Name", name);
        showMessage("Pass", pass);

        // Validate login credentials
        Cursor loginStatus = ld.validate(name, pass);
        if (name.isEmpty() || pass.isEmpty()) {
            showMessage("Error!!", "Empty fields");
        } else if (loginStatus.getCount() > 0) {
            // Login successful
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } else {
            // Login failed
            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
            showMessage("Error", "Try again!");
        }
    }
}
