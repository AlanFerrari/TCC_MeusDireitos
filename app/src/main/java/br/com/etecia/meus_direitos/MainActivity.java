package br.com.etecia.meus_direitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView voltar;
    ArrayList<Area_Atuacao> lstAreas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstAreas = new ArrayList<>();

        lstAreas.add(new Area_Atuacao("Penal", R.drawable.land_rows_vertical));
        lstAreas.add(new Area_Atuacao("Empresarial", R.drawable.office_building));
        lstAreas.add(new Area_Atuacao("Consumidor", R.drawable.human_queue));
        lstAreas.add(new Area_Atuacao("Trabalhista", R.drawable.account_tie));
        lstAreas.add(new Area_Atuacao("Ambiental", R.drawable.pine_tree));
        lstAreas.add(new Area_Atuacao("Civil", R.drawable.ic_baseline_location_city));
        lstAreas.add(new Area_Atuacao("TI", R.drawable.desktop_tower_monitor));
        lstAreas.add(new Area_Atuacao("Contratual", R.drawable.text_box));
        lstAreas.add(new Area_Atuacao("Tributário", R.drawable.scale_unbalanced));

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view_main);
        CustomAdapter mAdapter = new CustomAdapter(getApplicationContext(), lstAreas);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                RecyclerView.HORIZONTAL, false));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);


        voltar = findViewById(R.id.imgVoltar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_navegacao,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.PerfilAdvogados:
                Intent intent = new Intent(this, PerfilAdvogado.class);
                startActivity(intent);
                finish();
                break;

            case R.id.PesquisarAdvogados:
                Intent intent1 = new Intent(this, ListaAdvogados.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.informacoes:
                Intent intent2 = new Intent(this, Informacoes.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.topAppBar:
                Intent intent3 = new Intent(this, Entrar_Como.class);
                startActivity(intent3);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}