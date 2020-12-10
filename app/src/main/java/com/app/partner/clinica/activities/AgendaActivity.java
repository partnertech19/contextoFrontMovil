package com.app.partner.clinica.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.adapters.RecyclerAdapterAgenda;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.fragments.AgendaDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgendaActivity extends AppCompatActivity {

    private Spinner spnnFiltro;
    private RecyclerView rcyAgenda;
    private RecyclerView.Adapter adtAgenda;
    private RecyclerView.LayoutManager lymAgenda;

    private List<String> lsAgenda = new ArrayList<>(Arrays.asList("Adrian Mauricio Torres Morales", "Juan Jose Carrascal Trigueros","Angel Eduardo Arce Ramos", "Jesus Picardo Picardo","Adrian Mauricio Torres Morales", "Juan Jose Carrascal Trigueros","Angel Eduardo Arce Ramos", "Jesus Picardo Picardo"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        obtenerViews();
        crearRecycler();
        eventosViews();
    }

    private void crearRecycler() {

        lymAgenda = new LinearLayoutManager(AgendaActivity.this);
        adtAgenda = new RecyclerAdapterAgenda(this.lsAgenda, new RecyclerAdapterAgenda.OnItemClickListener() {
            @Override
            public void onItemClick(String agenda, int position) {
//                Toast.makeText(AgendaActivity.this, agenda, Toast.LENGTH_SHORT).show();
                AgendaDialogFragment dialogFragment = new AgendaDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "agendaDialog");
            }
        });
        rcyAgenda.setLayoutManager(lymAgenda);
        rcyAgenda.setAdapter(adtAgenda);
    }

    private void obtenerViews() {
        spnnFiltro = findViewById(R.id.spnnFiltro);
        rcyAgenda = findViewById(R.id.rcyAgenda);
        String filtro [] ={"Todos", "Pendiente", "Realizado", "Cancelado"};
        ArrayAdapter adapter = new ArrayAdapter(AgendaActivity.this,android.R.layout.simple_spinner_dropdown_item, filtro);
        spnnFiltro.setAdapter(adapter);
    }

    private void eventosViews() {
    }
}