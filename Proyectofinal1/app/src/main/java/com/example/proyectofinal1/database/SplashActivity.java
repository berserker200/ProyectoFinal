package com.example.proyectofinal1.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.proyectofinal1.MainActivity;
import com.example.proyectofinal1.R;

public class SplashActivity extends MainActivity {
    //determina el tiempo de la duracion del splash
private static final int SPLASH_DURATION=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       //Aquí se crea una nueva instancia
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            //Código que se ejecutará después de un cierto período de tiempo
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },SPLASH_DURATION);
    }
}