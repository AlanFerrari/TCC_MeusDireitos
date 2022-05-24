package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RedefinirSenha extends AppCompatActivity {

    ImageView voltar;
    Button RedefinirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        voltar = findViewById(R.id.imgVoltar);
        RedefinirSenha = findViewById(R.id.btnRedefinirSenha);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RedefinirSenha.this, Login.class);
                startActivity(intent);
                RedefinirSenha.this.finish();
            }
        });

        RedefinirSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RedefinirSenha.this, Login.class);
                startActivity(intent);
                RedefinirSenha.this.finish();
            }
        });
    }
}