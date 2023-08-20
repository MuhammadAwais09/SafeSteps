package com.example.safesteps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lottie;
    TextView App_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottie = findViewById(R.id.lottie);
        App_Name = findViewById(R.id.txtApp);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);
        App_Name.animate().translationY(-1500).setDuration(2100).setStartDelay(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);
            }
        },5000);
    }
}