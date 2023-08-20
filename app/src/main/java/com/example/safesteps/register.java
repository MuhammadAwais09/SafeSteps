package com.example.safesteps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {


    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextMobile;
    private EditText editTextPassword;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextPassword = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.cirRegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String mobile = editTextMobile.getText().toString().trim();
                String password = editTextPassword.getText().toString();

                if (name.isEmpty()) {
                    editTextName.setError("Please enter your name");
                    editTextName.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    editTextEmail.setError("Please enter your email");
                    editTextEmail.requestFocus();
                    return;
                }

                if (mobile.isEmpty()) {
                    editTextMobile.setError("Please enter your mobile number");
                    editTextMobile.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    editTextPassword.setError("Please enter a password");
                    editTextPassword.requestFocus();
                    return;
                }
                String toastMessage = "Registration Successful\nName: " + name +
                        "\nEmail: " + email +
                        "\nMobile: " + mobile +
                        "\nPassword: " + password;
                Toast.makeText(register.this, toastMessage, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }


    public void onLoginClick(View view){
        startActivity(new Intent(this,login.class));

    }
}