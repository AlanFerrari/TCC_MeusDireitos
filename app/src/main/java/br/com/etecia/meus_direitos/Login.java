package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtSenha;
    ImageView voltar;
    TextView esqueciSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);


        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, PerfilAdvogado.class));
            return;
        }

        //if user presses on login
        //calling the method login
        findViewById(R.id.btnLogar).setOnClickListener(view -> userLogin());

        //if user presses on not registered
        findViewById(R.id.txtIrCadastrar).setOnClickListener(view -> {
            //open register screen
            finish();
            startActivity(new Intent(getApplicationContext(), CadastroAdvogado.class));
        });

        voltar.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Entrar_Como.class);
            startActivity(intent);
            finish();
        });

        esqueciSenha.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, RedefinirSenha.class);
            startActivity(intent);
            finish();
        });

    }



    private void userLogin() {
        //first getting the values
        final String email = edtUsuario.getText().toString();
        final String password = edtSenha.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(email)) {
            edtUsuario.setError("Please enter your email");
            edtUsuario.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtSenha.setError("Please enter your password");
            edtSenha.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            //    @Override
            //    protected void onPreExecute() {
            //        super.onPreExecute();
            //        progressBar = (ProgressBar) findViewById(R.id.progressBar);
            //        progressBar.setVisibility(View.VISIBLE);
            //    }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
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
                        Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
}