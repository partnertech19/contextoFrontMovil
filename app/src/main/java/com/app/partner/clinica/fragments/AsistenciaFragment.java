package com.app.partner.clinica.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.activities.MainActivity;
import com.app.partner.clinica.common.MyApp;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AsistenciaFragment extends Fragment {

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
        iniciarQr();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        txtQr.setText(listener.recibirQr());
    }

//    private void setToolBar() {
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Asistencia");
////        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.user);
////        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }

    private void obtenerViews(View view) {
        txtQr = view.findViewById(R.id.txtQr);
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