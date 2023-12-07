package com.example.gestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent start = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(start);
                finish();
            }
        },1000);
    }

    public String recuperarSharedPreferences(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String tokenFinal = preferences.getString("token","No existe el token");
        return tokenFinal;
    }
}