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
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.app.partner.clinica.R;
import com.app.partner.clinica.activities.AgendaActivity;
import com.app.partner.clinica.activities.CitaActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class HorarioDialogFragment extends BottomSheetDialogFragment {

    Button btnVerAgenda, btnNuevaCita;

    Calendar fecha;
    boolean existeEvento;

    public HorarioDialogFragment(Calendar fecha, boolean existeEvento) {
        this.fecha = fecha;
        this.existeEvento = existeEvento;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

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

        if (existeEvento){
            btnVerAgenda.setVisibility(View.VISIBLE);
        }
    }

    private void agregarEventos() {
        btnVerAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agenda = new Intent(getContext(), AgendaActivity.class);
                agenda.putExtra("fecha", fecha);
                startActivity(agenda);
                dismiss();
            }
        });

        btnNuevaCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cita = new Intent(getContext(), CitaActivity.class);
                startActivity(cita);
                dismiss();
            }
        });

    }

}