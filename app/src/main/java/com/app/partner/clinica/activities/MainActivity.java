package com.app.partner.clinica.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.common.SharedPreferencesManager;
import com.app.partner.clinica.fragments.AsistenciaFragment;
import com.app.partner.clinica.fragments.HorarioFragment;
import com.app.partner.clinica.models.request.Asistenciagrupo;
import com.app.partner.clinica.models.request.Pagina;
import com.app.partner.clinica.models.response.ResponseWrapper;
import com.app.partner.clinica.services.instance.IAsistenciagrupo;
import com.app.partner.clinica.services.instance.ITerapiaIndividual;
import com.app.partner.clinica.services.service.AsistenciagrupoService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AsistenciaFragment.AsistenciaFragmentListener {

    public BottomNavigationView btnNav;
    private Fragment horarioFragment, asistenciaFragment;

    public String qr = "";

    private IAsistenciagrupo iAsistenciagrupo;
    private AsistenciagrupoService sAsistenciagrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            setFragmentDefecto();
        }

        retrofitInit();
        obtenerViews();
        buttonNavView();

//        Toolbar toolbar = findViewById(R.id.toolbarMain);
//        setSupportActionBar(toolbar);

//        Empleado empleado = SharedPreferencesManager.getPrefEmpleado();
//        Toast.makeText(this, "Hola " + empleado.getNombres(), Toast.LENGTH_LONG).show();

//        getSupportActionBar().setTitle("Principal");
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Constantes.alertInfo(Constantes.INFORMACION, "No se ha captura ningúun código QR");
            } else {
                this.qr = result.getContents();
                validarAsistencia(result.getContents());
            }
        }
    }

    private void validarAsistencia(String contents) {

        String info[] = contents.split("//"); //[0] - empresa / [1] - grupoHab / [2] - sesion / [3] - fecha sesion / [4] - Nombre grupo

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Asistencia")
                .setMessage("¿Desea marcar asistencia para el grupo " + info[4] + "?")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        marcarAsistencia(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]), Long.parseLong(info[3]));
                        dialog.dismiss();
                    }
                })
                .show();

    }

    private void marcarAsistencia(int iidempresa, int iidgrupohabilidades, int iidsesion, Long fsesion) {
        Asistenciagrupo asistenciagrupo = new Asistenciagrupo();
        asistenciagrupo.setIidempresa(iidempresa);
        asistenciagrupo.setIidgrupohabilidades(iidgrupohabilidades);
        asistenciagrupo.setIidsesion(iidsesion);
        asistenciagrupo.setTfechasesion(fsesion);
        asistenciagrupo.setIflagasistencia(1);
        //TODO: Quitar información en duro
        asistenciagrupo.setIidpaciente(8);

        Call<ResponseWrapper> call = sAsistenciagrupo.insertaAsistenciaGrupoMovil(asistenciagrupo);
        call.enqueue(new Callback<ResponseWrapper>() {
            @Override
            public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                if (response.isSuccessful()) {
                    if (response.body().getEstado() == Constantes.VALTRANSOK)
                        Constantes.alertSuccess(Constantes.NOTIFICACION, "Se marcó asistencia correctamente");
                    else if (response.body().getEstado() == Constantes.VALTRANSERR)
                        Constantes.alertInfo(Constantes.NOTIFICACION, "Usted no pertenece a este grupo");
                    else
                        Constantes.alertInfo(Constantes.NOTIFICACION, "Usted ya marcó asistencia anteriormente");
                } else {
                    Constantes.alertWarning(Constantes.NOTIFICACION, "Error al marcar asistencia");
                }
            }

            @Override
            public void onFailure(Call<ResponseWrapper> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, "COMPRUEBE QUE TENGA CONEXIÓN A INTERNET");
            }
        });
    }

    private void obtenerViews() {
        btnNav = findViewById(R.id.buttonNavMain);
    }

    private void buttonNavView() {
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.navHorario:
                        if (horarioFragment == null) {
                            horarioFragment = new HorarioFragment();
                        }
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
        //     /citas
        //     /asistencia
        Pagina accessPagina = SharedPreferencesManager.getPrefPagina();

        switch (accessPagina.getUrl()) {
            case Constantes.URL_CITAS:
                horarioFragment = new HorarioFragment();
                cambiarFragment(horarioFragment);
                break;
            case Constantes.URL_ASISTENCIA:
                asistenciaFragment = new AsistenciaFragment();
                cambiarFragment(asistenciaFragment);
                break;
            default:
                Constantes.alertWarning(Constantes.SIN_PERMISO, "No cuenta con alguna página asignada");
                SharedPreferencesManager.setPreferences((Pagina) null);
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
        }
    }

    public void cambiarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
    }

//    public void cambiarFragment(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_rigth_to_left, R.anim.exit_left_to_rigth).replace(R.id.fragmentMain, fragment).commit();
//    }

    private void retrofitInit() {
        iAsistenciagrupo = IAsistenciagrupo.getInstance();
        sAsistenciagrupo = iAsistenciagrupo.getService();
    }

    @Override
    public String recibirQr() {
        return qr;
    }
}