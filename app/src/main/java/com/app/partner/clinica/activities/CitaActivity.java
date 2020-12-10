package com.app.partner.clinica.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.adapters.RecyclerAdapterPaciente;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CitaActivity extends AppCompatActivity {

    private EditText edtPacienteNombre, edtCitaFecha, edtCitaHora;
    private Button btnNuevaCita;

    private SearchView srcPaciente;
    private RecyclerView rcyPaciente;
    private RecyclerAdapterPaciente adtPaciente;
    private RecyclerView.LayoutManager lymPaciente;

    private Calendar fecha;

    private List<String> lsPaciente = new ArrayList<>(Arrays.asList("Adrian Mauricio Torres Morales", "Juan Jose Carrascal Trigueros", "Angel Eduardo Arce Ramos", "Jesus Picardo Picardo", "Adrian Mauricio Torres Morales", "Juan Jose Carrascal Trigueros", "Angel Eduardo Arce Ramos", "Jesus Picardo Picardo"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);

        this.fecha = Calendar.getInstance();

        obtenerViews();
        crearRecycler();
        eventosViews();
    }

    private void obtenerViews() {
        srcPaciente = findViewById(R.id.srcPaciente);
        rcyPaciente = findViewById(R.id.rcyPaciente);
        edtPacienteNombre = findViewById(R.id.edtPacienteNombre);
        edtCitaFecha = findViewById(R.id.edtCitaFecha);
        edtCitaHora = findViewById(R.id.edtCitaHora);
        btnNuevaCita = findViewById(R.id.btnNuevaCita);
    }

    private void eventosViews() {
        srcPaciente.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adtPaciente.filter(s);
                return false;
            }
        });

        edtCitaFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Calendar fecha = Calendar.getInstance();
                DatePickerDialog datePicker = new DatePickerDialog(CitaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anno, int mes, int dia) {
                        fecha.set(anno, mes, dia);
                        edtCitaFecha.setText(retornarCero(dia) + "-" + retornarCero(mes + 1) + "-" + anno);
                    }
                }, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
            }
        });

        edtCitaHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePicker = new TimePickerDialog(CitaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                        fecha.set(Calendar.HOUR_OF_DAY, hora);
                        fecha.set(Calendar.MINUTE, minuto);
                        edtCitaHora.setText(retornarCero((hora > 12 ? hora - 12 : (hora == 0) ? 12 : hora)) + ":" + retornarCero(minuto) + " " + ((hora >= 12 && hora != 0) ? "pm" : "am"));
                    }
                }, fecha.get(Calendar.HOUR_OF_DAY), fecha.get(Calendar.MINUTE), false);
                timePicker.show();
            }
        });
    }

    private String retornarCero(int numero) {
        return (numero < 10 ? "0" + numero : "" + numero);
    }

    private void crearRecycler() {
        lymPaciente = new LinearLayoutManager(CitaActivity.this);
        adtPaciente = new RecyclerAdapterPaciente(this.lsPaciente, new RecyclerAdapterPaciente.OnItemClickListener() {
            @Override
            public void onCheckClick(String paciente, int position) {
                edtPacienteNombre.setText(paciente);
            }
        });
        rcyPaciente.setLayoutManager(lymPaciente);
        rcyPaciente.setAdapter(adtPaciente);
    }

}