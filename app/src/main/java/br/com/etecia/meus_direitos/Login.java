package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtSenha;
    private Button buttonLogin;
    private boolean loggedIn = false;
    private SharedPreferences.Editor editor;
    ImageView voltar;
    TextView esqueciSenha, IrCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        voltar = findViewById(R.id.imgVoltar);
        esqueciSenha = findViewById(R.id.txtesqueciSenha);
        IrCadastrar = findViewById(R.id.txtIrCadastrar);

        SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        edtUsuario = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);


        buttonLogin = findViewById(R.id.btnLogar);
        buttonLogin.setOnClickListener(v -> login());

        String usuario = getIntent().getStringExtra("email");
        edtUsuario.setText(usuario);
        String senha = getIntent().getStringExtra("senha");
        edtSenha.setText(senha);
        String novaSenha = getIntent().getStringExtra("novaSenha");

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Entrar_Como.class);
                startActivity(intent);
                finish();
            }
        });

        esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RedefinirSenha.class);
                startActivity(intent);
                finish();
            }
        });

        IrCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CadastroAdvogado.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        if (loggedIn) {
            // SE ESTIVER LOGADO ENTÃO AO ENTRAR NA APLICAÇÃO VAI PARA TELA SEGUINTE
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void login() {
        final String usuario = edtUsuario.getText().toString().trim();
        final String senha = edtSenha.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                response -> {
                    if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                        editor.putString(Config.USUARIO_SHARED_PREF, usuario);
                        editor.commit();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        //SE O LOGIN E SENHA FOR IGUAL AO QUE CONSTA NA TABELA DO BANCO DE DADOS ENTÃO VAI PARA OUTRA TELA
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Config.USUARIO, usuario);
                params.put(Config.SENHA, senha);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ((RequestQueue) requestQueue).add(stringRequest);
    }
}