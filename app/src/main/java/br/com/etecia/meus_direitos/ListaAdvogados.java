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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class ListaAdvogados extends AppCompatActivity {

    MaterialToolbar toolbar;
    ArrayList<Advogados> lstAdvogados;
    Spinner spinner1, spinner2, spinner3;
    ImageView filtro;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_advogados);

        spinner1 = findViewById(R.id.filtrarArea);
        spinner2 = findViewById(R.id.filtrarEstado);
        spinner3 = findViewById(R.id.filtrarCidade);
        filtro = findViewById(R.id.filtrar);
        cardView = findViewById(R.id.cardFiltro);

        String [] opcoes = {"Todas", "Penal", "Empresarial", "Consumidor", "Trabalhista", "Ambiental", "Civil", "TI", "Contratual", "Tributário"};

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.spinner_personalizado, opcoes);
        spinner1.setAdapter(adapter);

        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setVisibility(View.VISIBLE);
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
                Intent intent2 = new Intent(getApplicationContext(), InformacoesC.class);
                startActivity(intent2);
                break;
        }
        return true;
    }
}