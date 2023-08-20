package com.example.safesteps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button cirLoginButton;
    private TextView forgotPasswordTextView;
    private TextView registerTextView;
    private LinearLayout otherMethodsLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        cirLoginButton = findViewById(R.id.cirLoginButton);
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        registerTextView = findViewById(R.id.registerTextView);
//        otherMethodsLinearLayout = findViewById(R.id.otherMethodsLinearLayout);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString();
                if (email.isEmpty()) {
                    editTextEmail.setError("Please enter your email");
                    editTextEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    editTextPassword.setError("Please enter a password");
                    editTextPassword.requestFocus();
                    return;
                }

                String toastMessage = "Login Successful\nEmail: " + email + "\nPassword: " + password;
                Toast.makeText(login.this, toastMessage, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), forgotPassword.class));
            }
        });

    }


    public void onLoginClick(View View){
        startActivity(new Intent(this,register.class));

    }

}