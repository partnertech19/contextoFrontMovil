package com.app.partner.clinica.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.activities.CitaActivity;
import com.app.partner.clinica.activities.MainActivity;
import com.app.partner.clinica.models.request.Terapiaindividual;
import com.app.partner.clinica.models.response.ResponseTerapiaIndividual;
import com.app.partner.clinica.services.instance.ITerapiaIndividual;
import com.app.partner.clinica.services.service.TerapiaIndividualService;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaDialogFragment extends BottomSheetDialogFragment {

    Button btnCompletado, btnReprogramar, btnEliminar;
    private Terapiaindividual terapiaindividual;

    private ITerapiaIndividual iTerapiaIndividual;
    private TerapiaIndividualService sterapiaIndividual;

    public AgendaDialogFragment(Terapiaindividual terapiaindividual) {
        this.terapiaindividual = terapiaindividual;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        retrofitInit();
        View view = inflater.inflate(R.layout.fragment_agenda_dialog, container, false);
        obtenerViews(view);
        agregarEventos();
        return view;
    }

    private void obtenerViews(View view) {
        btnCompletado = view.findViewById(R.id.btnCitaCompletado);
        btnReprogramar = view.findViewById(R.id.btnCitaReprogramar);
        btnEliminar = view.findViewById(R.id.btnCitaEliminar);
    }

    private void agregarEventos() {
        btnCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

                Intent cita = new Intent(getActivity(), CitaActivity.class);
                cita.putExtra("terapiaindividual", terapiaindividual);
                cita.putExtra("accion", 3);
                startActivity(cita);
            }
        });

        btnReprogramar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

                Intent cita = new Intent(getActivity(), CitaActivity.class);
                cita.putExtra("terapiaindividual", terapiaindividual);
                cita.putExtra("accion", 2);
                startActivity(cita);
//                dismiss();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmar")
                        .setMessage("¿Desea eliminar la cita?")
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                                eliminarTerapia();
                            }
                        })
                        .show();
            }
        });
    }

    private void eliminarTerapia() {
        Call<ResponseTerapiaIndividual> call = sterapiaIndividual.eliminar(terapiaindividual.getIidterapiaindiv());
        call.enqueue(new Callback<ResponseTerapiaIndividual>() {
            @Override
            public void onResponse(Call<ResponseTerapiaIndividual> call, Response<ResponseTerapiaIndividual> response) {
                if (response.isSuccessful()) {
                    ResponseTerapiaIndividual resp = response.body();
                    Toast.makeText(getActivity(), "Eliminado OK", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(getActivity(), MainActivity.class);
                    startActivity(main);
                } else {
                    Toast.makeText(getActivity(), "ERROR INTERNO", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTerapiaIndividual> call, Throwable t) {
                Toast.makeText(getActivity(), "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void retrofitInit() {
        iTerapiaIndividual = ITerapiaIndividual.getInstance();
        sterapiaIndividual = iTerapiaIndividual.getService();
    }
}