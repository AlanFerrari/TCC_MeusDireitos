package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Entrar_Como extends AppCompatActivity {

    public static int DefinirUsuario;
    CardView entrarCliente, entrarAdvogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_como);

        entrarAdvogado = findViewById(R.id.EntrarAdvogado);
        entrarCliente = findViewById(R.id.EntrarCliente);


        entrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cardCliente = new Intent(Entrar_Como.this, MainActivity.class);
                startActivity(cardCliente);
                Entrar_Como.this.finish();
                DefinirUsuario = 1;
                cardCliente.putExtra("Usuário", DefinirUsuario);
            }
        });

        entrarAdvogado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cardAdvogado = new Intent(Entrar_Como.this, Login.class);
                startActivity(cardAdvogado);
                Entrar_Como.this.finish();
                DefinirUsuario = 2;
                cardAdvogado.putExtra("Usuário", DefinirUsuario);
            }
        });


    }
}