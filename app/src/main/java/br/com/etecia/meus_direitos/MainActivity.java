package br.com.etecia.meus_direitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Area_Atuacao> lstAreas;
    MaterialToolbar toolbar;


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

        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Entrar_Como.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navegacao, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.ListaAdvogados:
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