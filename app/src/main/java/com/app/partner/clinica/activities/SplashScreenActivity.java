package com.app.partner.clinica.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;

import com.app.partner.clinica.R;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.common.SharedPreferencesManager;
import com.app.partner.clinica.models.request.AgrupadorModulos;
import com.app.partner.clinica.models.request.Modulo;
import com.app.partner.clinica.models.request.Pagina;
import com.app.partner.clinica.models.response.ResponseAgrupadorModulos;
import com.app.partner.clinica.models.response.ResponseModulo;
import com.app.partner.clinica.models.response.ResponseToken;
import com.app.partner.clinica.services.instance.IAgrupadorModulos;
import com.app.partner.clinica.services.instance.IEmpleado;
import com.app.partner.clinica.services.instance.IToken;
import com.app.partner.clinica.services.service.AgrupadorModulosService;
import com.app.partner.clinica.services.service.EmpleadoService;
import com.app.partner.clinica.services.service.TokenService;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    IToken iToken;
    TokenService sToken;
    IEmpleado iEmpleado;
    EmpleadoService sEmpleado;
    IAgrupadorModulos iAgrupadorModulos;
    AgrupadorModulosService sAgrupadorModulos;

    String usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        retrofitInit();

        if (SharedPreferencesManager.getPrefPagina() == null) {
            irLogin();
        } else if (SharedPreferencesManager.getPrefBoolean(Constantes.KEY_RECORDAR)) {
            getToken();
        } else {
            irLogin();
        }
    }

    private void getToken() {

        usuario = SharedPreferencesManager.getPrefString(Constantes.KEY_USER);
        password = SharedPreferencesManager.getPrefString(Constantes.KEY_PASSWORD);

        String base = "clientIdPassword:secret";
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        Call<ResponseToken> call = sToken.login(authHeader, "password", "clientIdPassword", usuario, password);
        call.enqueue(new Callback<ResponseToken>() {
            @Override
            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                if (response.isSuccessful()) {
                    ResponseToken resp = response.body();
                    SharedPreferencesManager.setPreferences(Constantes.KEY_TOKEN, resp.getAccess_token());
                    SharedPreferencesManager.setPreferences(Constantes.KEY_REFRESH_TOKEN, resp.getRefresh_token());
                    listarAgrupadorModulos();
//                    Intent main = new Intent(SplashScreenActivity.this, MainActivity.class);
//                    startActivity(main);
//                    finish();
                } else {
                    Constantes.alertWarning(Constantes.CUIDADO, "Upps! Parece que su contraseña cambió");
                    irLogin();
                }
            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, t.getMessage());
                irLogin();
            }
        });
    }

    private void listarAgrupadorModulos() {
        AgrupadorModulos agrupadorModulos = new AgrupadorModulos();
        agrupadorModulos.setAccion("1");
        Call<ResponseAgrupadorModulos> call = sAgrupadorModulos.getAgrupadormodulos(agrupadorModulos);
        call.enqueue(new Callback<ResponseAgrupadorModulos>() {
            @Override
            public void onResponse(Call<ResponseAgrupadorModulos> call, Response<ResponseAgrupadorModulos> response) {
                if (response.isSuccessful()) {
                    AgrupadorModulos resp = response.body().getAaData().get(2);
                    retornarAcceso(resp);
                } else {
                    Constantes.alertError(Constantes.PROBLEMA, "No se logró conseguir información de los permisos");
                    irLogin();
                }
            }

            @Override
            public void onFailure(Call<ResponseAgrupadorModulos> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, t.getMessage());
                irLogin();
            }
        });
    }

    private void retornarAcceso(AgrupadorModulos agrupadorModulos) {
        Call<ResponseModulo> call = sEmpleado.retornarAcceso(agrupadorModulos);
        call.enqueue(new Callback<ResponseModulo>() {
            @Override
            public void onResponse(Call<ResponseModulo> call, Response<ResponseModulo> response) {
                if (response.isSuccessful()) {
                    List<Modulo> lsModulo = response.body().getAaData();
                    if (lsModulo.size() > 0) {
                        if (lsModulo.get(0).getLsPaginas().size() > 0) {
                            Pagina pagina = lsModulo.get(0).getLsPaginas().get(0);
                            if (validarPagina(pagina)) {
                                SharedPreferencesManager.setPreferences(pagina);

                                Intent main = new Intent(SplashScreenActivity.this, MainActivity.class);
                                startActivity(main);
                                finish();
                            } else {
                                Constantes.alertInfo(Constantes.SIN_PERMISO, "No cuenta con alguna página asignada");
                                irLogin();
                            }
                        } else {
                            Constantes.alertInfo(Constantes.SIN_PERMISO, "No cuenta con alguna página asignada");
                            irLogin();
                        }
                    } else {
                        Constantes.alertWarning(Constantes.SIN_PERMISO, "No cuenta con permisos para ingresar a la app");
                        irLogin();
                    }

                } else {
                    Constantes.alertError(Constantes.PROBLEMA, "No se logró conseguir información de los accesos");
                    irLogin();
                }
            }

            @Override
            public void onFailure(Call<ResponseModulo> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, t.getMessage());
                irLogin();
            }
        });
    }

    private void irLogin() {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
        finish();
    }

    private boolean validarPagina(Pagina pagina) {
        switch (pagina.getUrl()) {
            case Constantes.URL_CITAS:
                return true;
            case Constantes.URL_ASISTENCIA:
                return true;
            default:
                return false;
        }
    }

    private void retrofitInit() {
        iToken = IToken.getInstance();
        sToken = iToken.getService();

        iEmpleado = IEmpleado.getInstance();
        sEmpleado = iEmpleado.getService();

        iAgrupadorModulos = IAgrupadorModulos.getInstance();
        sAgrupadorModulos = iAgrupadorModulos.getService();
    }
}