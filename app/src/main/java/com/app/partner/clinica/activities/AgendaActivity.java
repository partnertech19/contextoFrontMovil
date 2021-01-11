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
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.adapters.RecyclerAdapterAgenda;
import com.app.partner.clinica.fragments.AgendaDialogFragment;
import com.app.partner.clinica.models.request.TerapiaEntrevista;
import com.app.partner.clinica.models.request.Terapiaindividual;
import com.app.partner.clinica.models.response.ResponseTerapiaIndividual;
import com.app.partner.clinica.services.instance.ITerapiaIndividual;
import com.app.partner.clinica.services.service.TerapiaIndividualService;

import java.util.List;

public class AgendaActivity extends AppCompatActivity {

    private Spinner spnnFiltro;
    private RecyclerView rcyAgenda;
    private RecyclerView.Adapter adtAgenda;
    private RecyclerView.LayoutManager lymAgenda;

//    private List<String> lsAgenda = new ArrayList<>(Arrays.asList("Adrian Mauricio Torres Morales", "Juan Jose Carrascal Trigueros", "Angel Eduardo Arce Ramos", "Jesus Picardo Picardo", "Adrian Mauricio Torres Morales", "Juan Jose Carrascal Trigueros", "Angel Eduardo Arce Ramos", "Jesus Picardo Picardo"));

    ITerapiaIndividual iTerapiaIndividual;
    TerapiaIndividualService sTerapiaIndividual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        retrofitInit();
        Bundle extras = getIntent().getExtras();
        obtenerViews();
        eventosViews();

        listarAgenda(extras.getLong("fecha"));
    }

    private void listarAgenda(Long fechaSeleccionada) {
        Terapiaindividual terapiaIndividual = new Terapiaindividual();
        terapiaIndividual.setIiddoctor(9);
        terapiaIndividual.setTfechaterapia(fechaSeleccionada);
        Call<ResponseTerapiaIndividual> call = sTerapiaIndividual.listarPorDoctorFecha(terapiaIndividual);
        call.enqueue(new Callback<ResponseTerapiaIndividual>() {
            @Override
            public void onResponse(Call<ResponseTerapiaIndividual> call, Response<ResponseTerapiaIndividual> response) {
                if (response.isSuccessful()){
                    crearRecycler(response.body().getAaData());
                }else {
                    Toast.makeText(AgendaActivity.this, "ERROR INTERNO", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTerapiaIndividual> call, Throwable t) {
                Toast.makeText(AgendaActivity.this, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void crearRecycler(List<TerapiaEntrevista> lsAgenda) {
        lymAgenda = new LinearLayoutManager(AgendaActivity.this);
        adtAgenda = new RecyclerAdapterAgenda(lsAgenda, new RecyclerAdapterAgenda.OnItemClickListener() {
            @Override
            public void onItemClick(TerapiaEntrevista terapiaEntrevista, int position) {
//                Toast.makeText(AgendaActivity.this, agenda, Toast.LENGTH_SHORT).show();
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