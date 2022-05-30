package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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

        findViewById(R.id.btncadastrarAdvogado).setOnClickListener(view -> {
            //if user pressed on button register
            //here we will register the user to server
            registerUser();
        });
    }

    private void registerUser() {
        final String username = edtNomeAdvogado.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String password = edtSenha.getText().toString().trim();
        final int idade = Integer.parseInt(edtIdade.getText().toString().trim());
        final String cidade = edtCidade.getText().toString().trim();
        final String estado = edtEstado.getText().toString().trim();
        final String numero_oab = edtRegistroOAB.getText().toString().trim();
        final String telefone_cel = edtTelefone.getText().toString().trim();


        //first we will do the validations

        if (TextUtils.isEmpty(username)) {
            edtNomeAdvogado.setError("Please enter username");
            edtNomeAdvogado.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Please enter your email");
            edtEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Enter a valid email");
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtSenha.setError("Enter a password");
            edtSenha.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(idade)) {
            edtIdade.setError("Enter a Idade");
            edtIdade.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(cidade)) {
            edtCidade.setError("Enter a Cidade");
            edtCidade.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(estado)) {
            edtEstado.setError("Enter a Estado");
            edtEstado.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(numero_oab)) {
            edtRegistroOAB.setError("Enter a password");
            edtRegistroOAB.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(telefone_cel)) {
            edtTelefone.setError("Enter a telefone / celular");
            edtTelefone.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                params.put("idade", String.valueOf(idade));
                params.put("cidade", cidade);
                params.put("estado", estado);
                params.put("numero_oab", numero_oab);
                params.put("telefone_cel", telefone_cel);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }

            //          @Override
            //           protected void onPreExecute() {
            //              super.onPreExecute();
            //              //displaying the progress bar while user registers on the server
            //              progressBar = (ProgressBar) findViewById(R.id.progressBar);
            //              progressBar.setVisibility(View.VISIBLE);
            //          }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("username"),
                                userJson.getString("email"),
                                userJson.getInt("idade"),
                                userJson.getString("cidade"),
                                userJson.getString("estado"),
                                userJson.getString("numero_oab"),
                                userJson.getString("telefone_cel")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), PerfilAdvogado.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
}