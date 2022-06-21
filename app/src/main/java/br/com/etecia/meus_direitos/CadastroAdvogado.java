package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.etecia.meus_direitos.objetos.API_IBGE;
import br.com.etecia.meus_direitos.objetos.Advogados;
import br.com.etecia.meus_direitos.objetos.Cidades;
import br.com.etecia.meus_direitos.objetos.Estados;
import br.com.etecia.meus_direitos.objetos.Subdistritos;

public class CadastroAdvogado extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    Button btnCadastrarAdvogado;
    ImageView voltar;
    EditText edtNomeAdvogado, edtEmail, edtTelefone, edtRegistroOAB, edtSenha;
    Spinner spinnerEstados, spinnerCidades, spinnerSubdistritos;

    Cidades[] municipios = null;
    boolean isUpdating = false;
    List<Advogados> AdvogadosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_advogado);

        btnCadastrarAdvogado = findViewById(R.id.btncadastrarAdvogado);
        voltar = findViewById(R.id.imgVoltar);
        edtNomeAdvogado = findViewById(R.id.nomeAdvogado);
        edtEmail = findViewById(R.id.email);
        edtTelefone = findViewById(R.id.telefone);
        spinnerCidades = findViewById(R.id.cidade);
        spinnerEstados = findViewById(R.id.estado);
        edtRegistroOAB = findViewById(R.id.registroOAB);
        edtSenha = findViewById(R.id.senha);

        spinnerEstados = findViewById(R.id.filtrarEstado);
        spinnerCidades = findViewById(R.id.filtrarCidade);
        spinnerCidades.setEnabled(false);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroAdvogado.this, Login.class);
                startActivity(intent);
                CadastroAdvogado.this.finish();
            }
        });

        findViewById(R.id.btncadastrarAdvogado).setOnClickListener(view -> {
            //se o usuário pressionou o botão registrar
            //aqui vamos registrar o usuário no servidor
            Intent intent = new Intent(getApplicationContext(), Chip_filtro_areas.class);
            registerUser();
            startActivity(intent);
            finish();
        });

        String respostaEstados = executaApiIBGE("estado");

        Gson gsonEstados = new GsonBuilder().setPrettyPrinting().create();
        final Estados[] estados = gsonEstados.fromJson(String.valueOf(respostaEstados), Estados[].class);

        final ArrayList<String> estadosParaSpinner = new ArrayList<>();

        for (Estados estado: estados){
            estadosParaSpinner.add(estado.getNome());
        }

        //Ordena em ordem alfabética
        Collections.sort(estadosParaSpinner);

        estadosParaSpinner.add(0, "Selecione o Estado");

        //Criando um array de estados no spinner
        ArrayAdapter<String> adapterEstados = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estadosParaSpinner);
        spinnerEstados.setAdapter(adapterEstados);

        spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (Estados estado : estados) {
                    if (estado.getNome().equals(spinnerEstados.getSelectedItem().toString())) {
                        solicitarMunicipios(estado.getSigla());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (Cidades cidade : municipios) {
                    if (cidade.getNome().equals(spinnerCidades.getSelectedItem().toString())) {
                        solicitarSubdistritos(String.valueOf(cidade.getId()));
                    }
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void solicitarMunicipios(String siglaEstado) {
        String respostaMunicipios = executaApiIBGE("municipio", siglaEstado);

        Gson gsonMunicipios = new GsonBuilder().setPrettyPrinting().create();
        municipios = gsonMunicipios.fromJson(String.valueOf(respostaMunicipios), Cidades[].class);

        final ArrayList<String> municipiosParaSpinner = new ArrayList<>();
        final ArrayList<String> idMunicipios = new ArrayList<>();

        for (Cidades cidade: municipios){
            municipiosParaSpinner.add(cidade.getNome());
            idMunicipios.add(String.valueOf(cidade.getId()));
        }

        //Ordena em ordem alfabética
        Collections.sort(municipiosParaSpinner);

        municipiosParaSpinner.add(0, "Selecione a Cidade");

        spinnerCidades.setEnabled(true);

        //Criando um array de municipios no spinner
        ArrayAdapter<String> adapterMunicipios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, municipiosParaSpinner);
        spinnerCidades.setAdapter(adapterMunicipios);

    }

    private void solicitarSubdistritos(String idMunicipio) {
        String respostaSubdistritos = executaApiIBGE("subdistrito", idMunicipio);

        Gson gsonSubdistritos = new GsonBuilder().setPrettyPrinting().create();
        Subdistritos[] subdistritos = gsonSubdistritos.fromJson(String.valueOf(respostaSubdistritos), Subdistritos[].class);

        final ArrayList<String> subdistritosParaSpinner = new ArrayList<>();

        for (Subdistritos subdistrito: subdistritos){
            subdistritosParaSpinner.add(subdistrito.getNome());
        }

        //Ordena em ordem alfabética
        Collections.sort(subdistritosParaSpinner);

        subdistritosParaSpinner.add(0, "Selecione o Subdistrito");

        spinnerSubdistritos.setEnabled(true);

        //Criando um array de municipios no spinner
        ArrayAdapter<String> adapterSubdistritos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subdistritosParaSpinner);
        spinnerSubdistritos.setAdapter(adapterSubdistritos);

    }

    private String executaApiIBGE (String... params) {
        API_IBGE api_ibge = new API_IBGE();

        String respostaIBGE = null;

        try {
            respostaIBGE = api_ibge.execute(params).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return respostaIBGE;
    }

    private void registerUser() {
        final String usuario = edtNomeAdvogado.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String senha = edtSenha.getText().toString().trim();
        final String estado = spinnerEstados.getSelectedItem().toString();
        final String cidade = spinnerCidades.getSelectedItem().toString();
        final String numeroOAB = edtRegistroOAB.getText().toString().trim();
        final String telefone = edtTelefone.getText().toString().trim();


        //primeiro faremos as validações

        if (TextUtils.isEmpty(usuario)) {
            edtNomeAdvogado.setError("Por favor insira seu nome completo");
            edtNomeAdvogado.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Por favor insira seu email");
            edtEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Email inválido");
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(senha)) {
            edtSenha.setError("Insira uma senha");
            edtSenha.requestFocus();
            return;
        }

      /*  if (TextUtils.isEmpty(cidade)) {
            spinnerCidades.setError("Selecione a cidade onde mora");
            spinnerCidades.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(estado)) {
            spinnerEstados.setError("Selecione um estado onde mora");
            spinnerEstados.requestFocus();
            return;
        }*/

        if (TextUtils.isEmpty(numeroOAB)) {
            edtRegistroOAB.setError("Insira seu registro na OAB");
            edtRegistroOAB.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(telefone)) {
            edtTelefone.setError("Insira o número do seu whatsapp");
            edtTelefone.requestFocus();
            return;
        }

        //se passar em todas as validações

      /*  class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //criando objeto manipulador de requisição
                RequestHandler requestHandler = new RequestHandler();*/

                //criando parâmetros de requisição
                HashMap<String, String> params = new HashMap<>();
                params.put("usuario", usuario);
                params.put("email", email);
                params.put("senha", senha);
                params.put("cidade", cidade);
                params.put("estado", estado);
                params.put("numeroOAB", numeroOAB);
                params.put("telefone", telefone);

                //Essa parte é nova, baseada no projeto hero
        PerformNetworkRequest request = new PerformNetworkRequest(URLs.URL_REGISTER, params, CODE_POST_REQUEST);
        request.execute();

        btnCadastrarAdvogado.setText("Adicionar");

        edtNomeAdvogado.setText("");
        edtEmail.setText("");
        edtTelefone.setText("");
        edtRegistroOAB.setText("");
        edtSenha.setText("");
        spinnerEstados.setSelection(0);
        spinnerCidades.setSelection(0);
        isUpdating = false;


        //retornando a resposta
        //return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
    }

    private void refreshUserList(JSONArray usuario) throws JSONException {
        AdvogadosList.clear();

        for (int i = 0; i < usuario.length(); i++) {
            JSONObject obj = usuario.getJSONObject(i);

            AdvogadosList.add(new Advogados(
                    obj.getInt("imagem"),
                    obj.getString("username"),
                    obj.getString("cidade"),
                    obj.getString("estado"),
                    obj.getString("area_atuacao")
            ));
        }
    }
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        String url;
        HashMap<String, String> params;
        int requestCode;

        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    refreshUserList(object.getJSONArray("usuario"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }
           /* @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    //convertendo a resposta para o objeto json
                    JSONObject obj = new JSONObject(s);

                    //se não houver erro na resposta
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //pega o usuário da resposta
                        JSONObject userJson = obj.getJSONObject("usuario");

                        //criando um novo objeto usuário
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("usuario"),
                                userJson.getString("email"),
                                userJson.getString("cidade"),
                                userJson.getString("estado"),
                                userJson.getString("numero_oab"),
                                userJson.getString("telefone_cel")
                        );

                        //armazenando o usuário nas preferências compartilhadas
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //iniciando a atividade do perfil
                        //startActivity(new Intent(getApplicationContext(), PerfilAdvogado_Adv.class));
                        //finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ocorreu algum erro", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        //executando a tarefa assíncrona
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }*/
}