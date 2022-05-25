package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler splash = new Handler();
        splash.postDelayed((Runnable) this, 3000);
    }


    public void run() {
        startActivity(new Intent(this, Entrar_Como.class));
        Splash.this.finish();
    }
}