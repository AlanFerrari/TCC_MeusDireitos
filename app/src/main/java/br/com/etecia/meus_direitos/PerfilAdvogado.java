package br.com.etecia.meus_direitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class PerfilAdvogado extends AppCompatActivity {

    Button EditarPerfil;
    MaterialToolbar toolbar;
    TextView txtNomeAdvogado;
    TextView txtIdade;
    TextView txtEmail;
    TextView txtTelefone;
    TextView txtCidade;
    TextView txtEstado;
    TextView txtRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_advogado);


        EditarPerfil = findViewById(R.id.btnEditarPerfil);

        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilAdvogado.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtNomeAdvogado = findViewById(R.id.nomeAdvogado);
        String nome = getIntent().getStringExtra("nome");
        txtNomeAdvogado.setText(nome);

        txtIdade = findViewById(R.id.idade);
        String idade = getIntent().getStringExtra("idade");
        txtIdade.setText(idade);

        txtEmail = findViewById(R.id.email);
        String email = getIntent().getStringExtra("email");
        txtEmail.setText(email);

        txtTelefone = findViewById(R.id.telefone);
        String telefone = getIntent().getStringExtra("telefone");
        txtTelefone.setText(telefone);

        txtCidade = findViewById(R.id.cidade);
        String cidade = getIntent().getStringExtra("cidade");
        txtCidade.setText(cidade);

        txtEstado = findViewById(R.id.estado);
        String estado = getIntent().getStringExtra("estado");
        txtEstado.setText(estado);

        txtRegistro = findViewById(R.id.registroOAB);
        String registro = getIntent().getStringExtra("registro");
        txtRegistro.setText(registro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navegacao, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.advogados:
                Intent intent = new Intent(this, ListaAdvogados.class);
                startActivity(intent);
                break;

            case R.id.informacoes:
                Intent intent2 = new Intent(this, Informacoes.class);
                startActivity(intent2);
                break;

            default:
                break;
        }

        return true;
    }
}