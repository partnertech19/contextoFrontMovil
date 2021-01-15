package com.app.partner.clinica.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.app.partner.clinica.R;
import com.app.partner.clinica.activities.AgendaActivity;
import com.app.partner.clinica.activities.CitaActivity;
import com.app.partner.clinica.activities.MainActivity;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.models.request.Consultoriodoctor;
import com.app.partner.clinica.services.instance.IConsultorio;
import com.app.partner.clinica.services.instance.ITerapiaIndividual;
import com.app.partner.clinica.services.service.ConsultorioService;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class HorarioDialogFragment extends BottomSheetDialogFragment {

    Button btnVerAgenda, btnNuevaCita;
    CardView cdvDisponibilidad;

    private Calendar fecha;
    private boolean existeEvento;

    IConsultorio iConsultorio;
    ConsultorioService sConsultorio;

    public HorarioDialogFragment(Calendar fecha, boolean existeEvento) {
        this.fecha = fecha;
        this.existeEvento = existeEvento;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retrofitInit();
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_horario_dialog, null);
        obtenerViews(view);
        agregarEventos();
        return view;
//        if (getDialog() != null && getDialog().getWindow() != null) {
//            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        }
    }

    private void obtenerViews(View view) {
        btnVerAgenda = view.findViewById(R.id.btnVerAgenda);
        btnNuevaCita = view.findViewById(R.id.btnNuevaCita);
        cdvDisponibilidad = view.findViewById(R.id.cdvDisponibilidad);

        if (existeEvento) {
            btnVerAgenda.setVisibility(View.VISIBLE);
        }

        validarDisponibilidad();
    }

    private void validarDisponibilidad() {
        Consultoriodoctor consultoriodoctor = new Consultoriodoctor();
        consultoriodoctor.setIiddoctor(9);
        consultoriodoctor.setTfechainicio(fecha.getTime().getTime());
        Call<Boolean> call = sConsultorio.validarDisponibilidad(consultoriodoctor);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body()) {
                        btnNuevaCita.setVisibility(View.VISIBLE);
                    } else {
                        cdvDisponibilidad.setVisibility(View.VISIBLE);
                    }
                } else {
                    Constantes.alertWarning(Constantes.NOTIFICACION, "Error en validación");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private void agregarEventos() {
        btnVerAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agenda = new Intent(getContext(), AgendaActivity.class);
                agenda.putExtra("fecha", fecha.getTime().getTime());
                startActivity(agenda);
                dismiss();
            }
        });

        btnNuevaCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cita = new Intent(getContext(), CitaActivity.class);
                cita.putExtra("fecha", fecha.getTime().getTime());
                startActivity(cita);
                dismiss();
            }
        });
    }

    private void retrofitInit() {
        iConsultorio = IConsultorio.getInstance();
        sConsultorio = iConsultorio.getService();
    }
}