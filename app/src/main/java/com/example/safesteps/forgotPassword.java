package com.example.safesteps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotPassword extends AppCompatActivity {
    private EditText editTextEmail;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        editTextEmail = findViewById(R.id.editTextEmail);
        submitButton = findViewById(R.id.cirSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user input (email) from the EditText view
                String email = editTextEmail.getText().toString().trim();


                if (email.isEmpty()) {
                    editTextEmail.setError("Please enter your email");
                    editTextEmail.requestFocus();
                    return;
                }
                String toastMessage = "Password reset link sent to: " + email;
                Toast.makeText(forgotPassword.this, toastMessage, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });
    }

    public void onLoginClick(View view){

        startActivity(new Intent(this,login.class));

    }
}