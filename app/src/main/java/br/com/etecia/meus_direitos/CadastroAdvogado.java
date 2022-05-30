package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CadastroAdvogado extends AppCompatActivity {

    Button btnCadastrarAdvogado;
    ImageView voltar;
    EditText edtNomeAdvogado;
    EditText edtIdade;
    EditText edtEmail;
    EditText edtTelefone;
    EditText edtCidade;
    EditText edtEstado;
    EditText edtRegistroOAB;
    EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_advogado);

        btnCadastrarAdvogado = findViewById(R.id.btncadastrarAdvogado);
        voltar = findViewById(R.id.imgVoltar);
        edtNomeAdvogado = findViewById(R.id.nomeAdvogado);
        edtIdade = findViewById(R.id.idade);
        edtEmail = findViewById(R.id.email);
        edtTelefone = findViewById(R.id.telefone);
        edtCidade = findViewById(R.id.cidade);
        edtEstado = findViewById(R.id.estado);
        edtRegistroOAB = findViewById(R.id.registroOAB);
        edtSenha = findViewById(R.id.senha);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroAdvogado.this, Login.class);
                startActivity(intent);
                CadastroAdvogado.this.finish();
            }
        });

        btnCadastrarAdvogado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAdvogado.this, PerfilAdvogado.class);

                intent.putExtra("nome", edtNomeAdvogado.getText().toString());
                intent.putExtra("idade", edtIdade.getText().toString());
                intent.putExtra("email", edtEmail.getText().toString());
                intent.putExtra("telefone", edtTelefone.getText().toString());
                intent.putExtra("cidade", edtCidade.getText().toString());
                intent.putExtra("estado", edtEstado.getText().toString());
                intent.putExtra("registro", edtRegistroOAB.getText().toString());


                startActivity(intent);
                finish();
            }
        });

        btnCadastrarAdvogado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAdvogado.this, Login.class);

                intent.putExtra("email", edtEmail.getText().toString());
                intent.putExtra("senha", edtSenha.getText().toString());

                startActivity(intent);
                finish();
            }
        });

        btnCadastrarAdvogado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroAdvogado.this, ListaAdvogados.class);

                intent.putExtra("nome", edtNomeAdvogado.getText().toString());
                intent.putExtra("cidade", edtCidade.getText().toString());
                intent.putExtra("estado", edtEstado.getText().toString());

                startActivity(intent);
                finish();
            }
        });
    }
}