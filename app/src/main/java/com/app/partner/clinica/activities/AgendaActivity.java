package com.app.partner.clinica.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.partner.clinica.R;
import com.app.partner.clinica.adapters.RecyclerAdapterAgenda;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.fragments.AgendaDialogFragment;
import com.app.partner.clinica.models.request.TerapiaEntrevista;
import com.app.partner.clinica.models.request.Terapiaindividual;
import com.app.partner.clinica.models.response.ResponseTerapiaEntrevista;
import com.app.partner.clinica.services.instance.ITerapiaIndividual;
import com.app.partner.clinica.services.service.TerapiaIndividualService;

import java.util.ArrayList;
import java.util.List;

public class AgendaActivity extends AppCompatActivity {

    private Spinner spnnFiltro;
    private RecyclerView rcyAgenda;
    private RecyclerView.Adapter adtAgenda;
    private RecyclerView.LayoutManager lymAgenda;

    ITerapiaIndividual iTerapiaIndividual;
    TerapiaIndividualService sTerapiaIndividual;

    private Long fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        retrofitInit();
        obtenerViews();
        eventosViews();

        Bundle extras = getIntent().getExtras();
        this.fechaSeleccionada = extras.getLong("fecha");
        listarAgenda();
    }

    public void listarAgenda() {
        Terapiaindividual terapiaIndividual = new Terapiaindividual();
        terapiaIndividual.setIiddoctor(9);
        terapiaIndividual.setTfechaterapia(this.fechaSeleccionada);
        Call<ResponseTerapiaEntrevista> call = sTerapiaIndividual.listarPorDoctorFecha(terapiaIndividual);
        call.enqueue(new Callback<ResponseTerapiaEntrevista>() {
            @Override
            public void onResponse(Call<ResponseTerapiaEntrevista> call, Response<ResponseTerapiaEntrevista> response) {
                if (response.isSuccessful()) {
                    List<TerapiaEntrevista> lsTerapiaEntrevista = response.body().getAaData();
                    if (lsTerapiaEntrevista != null)
                        crearRecycler(lsTerapiaEntrevista);
                    else {
                        crearRecycler(new ArrayList<>());
                        Constantes.alertInfo(Constantes.NOTIFICACION, "No hay eventos para este día");
                    }
                } else {
                    Constantes.alertWarning(Constantes.NOTIFICACION, "No se logró listar la agenda del día");
                }
            }

            @Override
            public void onFailure(Call<ResponseTerapiaEntrevista> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private void crearRecycler(List<TerapiaEntrevista> lsAgenda) {
        lymAgenda = new LinearLayoutManager(AgendaActivity.this);
        adtAgenda = new RecyclerAdapterAgenda(lsAgenda, new RecyclerAdapterAgenda.OnItemClickListener() {
            @Override
            public void onItemClick(TerapiaEntrevista terapiaEntrevista, int position) {
                AgendaDialogFragment dialogFragment = new AgendaDialogFragment(terapiaEntrevista.getTerapiaindividual());
                dialogFragment.show(getSupportFragmentManager(), "agendaDialog");
            }
        });
        rcyAgenda.setLayoutManager(lymAgenda);
        rcyAgenda.setAdapter(adtAgenda);
    }

    private void obtenerViews() {
        spnnFiltro = findViewById(R.id.spnnFiltro);
        rcyAgenda = findViewById(R.id.rcyAgenda);
        String filtro[] = {"Todos", "Pendiente", "Realizado", "Cancelado"};
        ArrayAdapter adapter = new ArrayAdapter(AgendaActivity.this, android.R.layout.simple_spinner_dropdown_item, filtro);
        spnnFiltro.setAdapter(adapter);
    }

    private void eventosViews() {
    }

    private void retrofitInit() {
        iTerapiaIndividual = ITerapiaIndividual.getInstance();
        sTerapiaIndividual = iTerapiaIndividual.getService();
    }
}