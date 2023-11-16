package com.example.safesteps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safesteps.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference fallReference;
    private DatabaseReference heartRateReference;
    private TextView detectionTextView;
    private TextView noDetectionTextView;
    private TextView heartRateTextView;
    private Button stopButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectionTextView = findViewById(R.id.fallDetectionText);
        noDetectionTextView = findViewById(R.id.detectionTextView);
        heartRateTextView = findViewById(R.id.heartRateTextView);
        stopButton = findViewById(R.id.stopButton);
        mediaPlayer = MediaPlayer.create(this, R.raw.beep);

        // Fall detection
        fallReference = FirebaseDatabase.getInstance().getReference("fall");
        fallReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer fallValue = dataSnapshot.getValue(Integer.class);
                if (fallValue != null && fallValue == 1) {
                    detectionTextView.setVisibility(View.VISIBLE);
                    noDetectionTextView.setVisibility(View.GONE);
                    playBeepSound();
                } else {
                    detectionTextView.setVisibility(View.GONE);
                    noDetectionTextView.setVisibility(View.VISIBLE);
                    stopBeepSound();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Failed to read fall detection value: " + databaseError.toException(), Toast.LENGTH_SHORT).show();
            }
        });

        // Heartbeat monitoring
        heartRateReference = FirebaseDatabase.getInstance().getReference("heart_rate");
        heartRateReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer heartRateValue = dataSnapshot.getValue(Integer.class);
                if (heartRateValue != null) {
                    heartRateTextView.setText("Heart Rate: " + heartRateValue);
                    if (heartRateValue > 70) {
                        playBeepSound();
                    } else {
                        stopBeepSound(); // Stop beep if the heart rate is not above 70
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Failed to read heartbeat value: " + databaseError.toException(), Toast.LENGTH_SHORT).show();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fallReference.setValue(0);
                detectionTextView.setVisibility(View.GONE);
                noDetectionTextView.setVisibility(View.VISIBLE);
                stopBeepSound();
            }
        });
    }

    private void playBeepSound() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    // Restart the sound when it completes
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                }
            });
            mediaPlayer.start();
        }
    }

    private void stopBeepSound() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.prepareAsync();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
