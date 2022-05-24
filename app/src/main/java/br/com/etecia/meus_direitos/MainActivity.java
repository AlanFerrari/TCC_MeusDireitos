package br.com.etecia.meus_direitos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView voltar, menu;
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
        menu = findViewById(R.id.imgMenu);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Entrar_Como.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }
}