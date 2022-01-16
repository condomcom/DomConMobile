package com.example.condom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.condom.R;

import java.util.concurrent.TimeUnit;

public class LoadingScreen extends AppCompatActivity {
    private TextView logo;
    private LottieAnimationView lottieAnimationView;
    private ImageView imageLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        logo = findViewById(R.id.text_logo);
        lottieAnimationView = findViewById(R.id.lottie_animation);
        imageLoading = findViewById(R.id.image_loading_screen);

        imageLoading.animate().translationY(-2000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(4);
                    Intent intent = new Intent(LoadingScreen.this, NewAuthorizationActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}