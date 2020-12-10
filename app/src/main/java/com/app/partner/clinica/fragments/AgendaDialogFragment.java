package com.app.partner.clinica.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.partner.clinica.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AgendaDialogFragment extends BottomSheetDialogFragment {

    Button btnCompletado, btnReprogramar, btnEliminar;

    public AgendaDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                new AlertDialog.Builder(getContext())
                        .setCancelable(false)
                        .setTitle("Confirmar")
                        .setMessage("¿Desea marcar la cita como completada?")
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Completar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        btnReprogramar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                            }
                        })
                        .show();
            }
        });
    }

}