package com.app.partner.clinica.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.common.SharedPreferencesManager;
import com.app.partner.clinica.fragments.AsistenciaFragment;
import com.app.partner.clinica.fragments.HorarioFragment;
import com.app.partner.clinica.models.request.Empleado;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements AsistenciaFragment.AsistenciaFragmentListener {

    public BottomNavigationView btnNav;
    public Fragment fragment;

    private Fragment horarioFragment;
    private Fragment asistenciaFragment;

    public String qr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            setFragmentDefecto();
        }

//        Toolbar toolbar = findViewById(R.id.toolbarMain);
//        setSupportActionBar(toolbar);

        Empleado empleado = SharedPreferencesManager.getPrefEmpleado();
        Toast.makeText(this, "Hola " + empleado.getNombres(), Toast.LENGTH_LONG).show();

        obtenerViews();
        buttonNavView();

//        getSupportActionBar().setTitle("Principal");
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        txtInformacionQr = findViewById(R.id.txtInformacionQr);
//        txtFecha = findViewById(R.id.txtFecha);
//        cldFecha = findViewById(R.id.cldFecha);
//
//        cldFecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
//                String fecha = i2 + "-" + (i1 + 1) + "-" + i;
//                txtFecha.setText(fecha);
//            }
//        });
//        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                btnNav.setSelectedItemId(R.id.navHorario);
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {
                this.qr = result.getContents();
            }
        } else {
            btnNav.setSelectedItemId(R.id.navHorario);
        }
    }

    private void obtenerViews() {
        btnNav = findViewById(R.id.buttonNavMain);
    }

    private void buttonNavView() {
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    fragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.navHorario:
                            fragment = horarioFragment;
                            break;
                        case R.id.navAsistencia:
                            if (asistenciaFragment == null) {
                                asistenciaFragment = new AsistenciaFragment();
                            }
                            fragment = asistenciaFragment;
                            break;
                    }
                    cambiarFragment(fragment);
                return true;
            }
        });
    }

    private void setFragmentDefecto() {
        horarioFragment = new HorarioFragment();
        cambiarFragment(horarioFragment);
    }

    public void cambiarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
    }

//    public void cambiarFragment(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_rigth_to_left, R.anim.exit_left_to_rigth).replace(R.id.fragmentMain, fragment).commit();
//    }

    @Override
    public String recibirQr() {
        return qr;
    }
}