package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CadastroAdvogado extends AppCompatActivity {

    Button bntCadastrarAdvogado;
    ImageView voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_advogado);

        bntCadastrarAdvogado = findViewById(R.id.bntcadastrarAdvogado);
        voltar = findViewById(R.id.imgVoltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}