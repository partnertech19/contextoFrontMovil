package com.app.partner.clinica.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.partner.clinica.R;
import com.app.partner.clinica.activities.LoginActivity;
import com.app.partner.clinica.activities.MainActivity;
import com.app.partner.clinica.common.Constantes;
import com.google.zxing.integration.android.IntentIntegrator;

public class AsistenciaFragment extends Fragment {

    ImageView imgCerrarSesion;
    Button btnLeerQR;
    private TextView txtQr;

    private AsistenciaFragmentListener listener;

    public AsistenciaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AsistenciaFragmentListener) {
            this.listener = (AsistenciaFragmentListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asistencia, container, false);
//        setToolBar();
        obtenerViews(view);
        eventosViews();

        return view;
    }

//    private void setToolBar() {
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Asistencia");
////        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.user);
////        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }

    private void obtenerViews(View view) {
        imgCerrarSesion = view.findViewById(R.id.imgCerrarSesionAsistencia);
        btnLeerQR = view.findViewById(R.id.btnLeerQr);
    }


    private void eventosViews() {
        imgCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constantes.limpiarSharedPreferenes();
                Intent login = new Intent(getContext(), LoginActivity.class);
                startActivity(login);
                getActivity().finish();
            }
        });

        btnLeerQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarQr();
            }
        });
    }

    private void iniciarQr() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setPrompt("");
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }

    public static interface AsistenciaFragmentListener {
        String recibirQr();
    }

}