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

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
        }


        txtNomeAdvogado =  findViewById(R.id.nomeAdvogado);
        txtEmail =  findViewById(R.id.email);
        txtTelefone =  findViewById(R.id.telefone);
        txtIdade =  findViewById(R.id.idade);
        txtCidade = findViewById(R.id.cidade);
        txtEstado = findViewById(R.id.estado);
        txtRegistro = findViewById(R.id.registroOAB);
        EditarPerfil = findViewById(R.id.btnEditarPerfil);

        User user = SharedPrefManager.getInstance(this).getUser();


        txtNomeAdvogado.setText(user.getUsername());
        txtIdade.setText(user.getIdade());
        txtEmail.setText(user.getEmail());
        txtTelefone.setText(user.getTelefone_cel());
        txtCidade.setText(user.getcidade());
        txtEstado.setText(user.getestado());
        txtRegistro.setText(user.getnumero_oab());

        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(PerfilAdvogado.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        //when the user presses logout button
        //calling the logout method
        // findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        finish();
        //        SharedPrefManager.getInstance(getApplicationContext()).logout();
        //    }
        // });

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