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

import java.util.ArrayList;

import br.com.etecia.meus_direitos.objetos.Advogados;

public class ListaAdvogados extends AppCompatActivity {

    MaterialToolbar toolbar;
    ArrayList<Advogados> lstAdvogados;
    Spinner spinnerAreas, spinnerEstados, spinnerCidades;
    ImageView filtro;
    CardView cardViewFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_advogados);

        spinnerAreas = findViewById(R.id.filtrarArea);
        spinnerEstados = findViewById(R.id.filtrarEstado);
        spinnerCidades = findViewById(R.id.filtrarCidade);

        filtro = findViewById(R.id.filtrar);
        cardViewFiltro = findViewById(R.id.cardFiltro);

        //Criando um array de areas de trabalho no spinner
        ArrayAdapter<CharSequence> adapterAreas = ArrayAdapter.createFromResource(this, R.array.areas_atuacao, android.R.layout.simple_spinner_item);
        adapterAreas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAreas.setAdapter(adapterAreas);

        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstados.setAdapter(adapterEstado);

        ArrayAdapter<CharSequence> adapterCidades = ArrayAdapter.createFromResource(this, R.array.cidades, android.R.layout.simple_spinner_item);
        adapterCidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCidades.setAdapter(adapterCidades);

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

        final int[] vaiserfalsedepois = {1};

        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vaiserfalsedepois[0] == 1) {
                    cardViewFiltro.setVisibility(View.VISIBLE);
                    vaiserfalsedepois[0] = 0;

                } else {
                    cardViewFiltro.setVisibility(View.GONE);
                    vaiserfalsedepois[0] = 1;
                }
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
                Intent intent2 = new Intent(getApplicationContext(), InformacoesCli.class);
                startActivity(intent2);
                break;
        }
        return true;
    }
}