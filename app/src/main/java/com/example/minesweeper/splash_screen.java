package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class splash_screen extends AppCompatActivity {

    public static final int SPLASH_SCREEN= 6000;
    private LottieAnimationView nothing, explosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        explosion= findViewById(R.id.lottie_explosion);
        nothing= findViewById(R.id.lottie_nothing);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nothing.setVisibility(View.GONE);
                explosion.setVisibility(View.VISIBLE);
            }
        }, SPLASH_SCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(splash_screen.this, MainActivity.class);
                startActivity(intent);
            }
        }, 0000);
    }
}