package br.com.etecia.meus_direitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import br.com.etecia.meus_direitos.objetos.Advogados;
import br.com.etecia.meus_direitos.objetos.Cidades;
import br.com.etecia.meus_direitos.objetos.Estados;

public class ListaAdvogados extends AppCompatActivity {

    MaterialToolbar toolbar;
    ArrayList<Advogados> lstAdvogados;
    Spinner spinnerAreas, spinnerEstados, spinnerCidades;
    ImageView filtro;
    CardView cardView;
    Cidades[] municipios = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_advogados);

        spinnerAreas = findViewById(R.id.filtrarArea);
        spinnerEstados = findViewById(R.id.filtrarEstado);
        spinnerCidades = findViewById(R.id.filtrarCidade);
        spinnerCidades.setEnabled(false);

        filtro = findViewById(R.id.filtrar);
        cardView = findViewById(R.id.cardFiltro);

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
        ArrayAdapter<String> adapterEstados = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estadosParaSpinner);
        spinnerEstados.setAdapter(adapterEstados);

        spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (Estados estado: estados){
                    if (estado.getNome().equals(spinnerEstados.getSelectedItem().toString())){
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
                for (Cidades cidade: municipios){
                    if (cidade.getNome().equals(spinnerCidades.getSelectedItem().toString())){
                        solicitarMunicipios(String.valueOf(cidade.getId()));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Criando um array de areas de trabalho no spinner
        ArrayAdapter<CharSequence> adapterAreas = ArrayAdapter.createFromResource(this, R.array.areas_atuacao, android.R.layout.simple_spinner_item);
        adapterAreas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAreas.setAdapter(adapterAreas);

        spinnerAreas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                } else if (position == 3) {

                } else if (position == 4) {

                } else if (position == 5) {

                } else if (position == 6) {

                } else if (position == 7) {

                } else if (position == 8) {

                } else if (position == 9) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       /* ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstados.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.cidades, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCidades.setAdapter(adapter3); */

        boolean clique = false;

        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setVisibility(clique ? View.VISIBLE : View.GONE);
            }
        });


        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAdvogados.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lstAdvogados = new ArrayList<>();

        lstAdvogados.add(new Advogados(R.drawable.exemplo, "Luis Abelardo Pachoal da Costa", "São Paulo", "SP", "Direito Civil, Direito do Consumidor e Direito Trabalhista"));

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view_lista);
        RecyclerAdapter mAdapter = new RecyclerAdapter(getApplicationContext(), lstAdvogados);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    private void solicitarMunicipios(String siglaEstado) {
        String respostaMunicipios = executaApiIBGE("municipio", siglaEstado);

        Gson gsonMunicipios = new GsonBuilder().setPrettyPrinting().create();
        municipios = gsonMunicipios.fromJson(String.valueOf(respostaMunicipios), Cidades[].class);
        final ArrayList<String> municipiosParaSpinner = new ArrayList<>();

        for (Cidades cidade: municipios){
            municipiosParaSpinner.add(cidade.getNome());
        }

        //Ordena em ordem alfabética
        Collections.sort(municipiosParaSpinner);

        municipiosParaSpinner.add(0, "Selecione a Cidade");

        spinnerCidades.setEnabled(true);

        //Criando um array de municipios no spinner
        ArrayAdapter<String> adapterMunicipios = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, municipiosParaSpinner);
        spinnerCidades.setAdapter(adapterMunicipios);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navegacao_cliente, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.advogados:
                Intent intent = new Intent(getApplicationContext(), ListaAdvogados.class);
                startActivity(intent);
                break;

            case R.id.informacoes:
                Intent intent2 = new Intent(getApplicationContext(), InformacoesC.class);
                startActivity(intent2);
                break;
        }
        return true;
    }
}