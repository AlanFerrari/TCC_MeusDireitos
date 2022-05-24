package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PerfilAdvogado extends AppCompatActivity {

    ImageView voltar;
    Button EditarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_advogado);

        voltar = findViewById(R.id.imgVoltar);
        EditarPerfil = findViewById(R.id.btnEditarPerfil);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilAdvogado.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}