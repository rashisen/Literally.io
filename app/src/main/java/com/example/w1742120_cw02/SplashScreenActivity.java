package com.example.w1742120_cw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



public class SplashScreenActivity extends AppCompatActivity {
    private static final int  SPLASH_SCREEN_TIMEOUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreenActivity.this, MainMenuActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }

}
