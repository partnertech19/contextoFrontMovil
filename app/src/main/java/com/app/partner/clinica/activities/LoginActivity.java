package com.app.partner.clinica.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.app.partner.clinica.R;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.common.SharedPreferencesManager;
import com.app.partner.clinica.models.request.AgrupadorModulos;
import com.app.partner.clinica.models.request.Empleado;
import com.app.partner.clinica.models.request.Modulo;
import com.app.partner.clinica.models.request.Pagina;
import com.app.partner.clinica.models.request.Perfiles;
import com.app.partner.clinica.models.response.ResponseAgrupadorModulos;
import com.app.partner.clinica.models.response.ResponseEmpleado;
import com.app.partner.clinica.models.response.ResponseModulo;
import com.app.partner.clinica.models.response.ResponseToken;
import com.app.partner.clinica.services.instance.IAgrupadorModulos;
import com.app.partner.clinica.services.instance.IEmpleado;
import com.app.partner.clinica.services.instance.IToken;
import com.app.partner.clinica.services.service.AgrupadorModulosService;
import com.app.partner.clinica.services.service.EmpleadoService;
import com.app.partner.clinica.services.service.TokenService;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btnIngresar;
    EditText edtUsuario, edtPassword;
    Switch swtRecordar;

    IToken iToken;
    TokenService sToken;
    IEmpleado iEmpleado;
    EmpleadoService sEmpleado;
    IAgrupadorModulos iAgrupadorModulos;
    AgrupadorModulosService sAgrupadorModulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        retrofitInit();

        obtenerViews();
        eventosViews();

        if (SharedPreferencesManager.getPrefBoolean(Constantes.KEY_RECORDAR)) {
            edtUsuario.setText(SharedPreferencesManager.getPrefString(Constantes.KEY_USER));
            edtPassword.setText(SharedPreferencesManager.getPrefString(Constantes.KEY_PASSWORD));
        } else {
            edtUsuario.setText("atorres@partnertech.pe");
            edtPassword.setText("GNNZ8VYB65");
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

    private void obtenerViews() {
        btnIngresar = findViewById(R.id.btnNuevaCita);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        swtRecordar = findViewById(R.id.swtRecordar);
    }

    private void eventosViews() {
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIngresar.setEnabled(false);
                valigarLogin();
            }
        });
    }

    private void valigarLogin() {
        String usuario = edtUsuario.getText().toString();
        String password = edtPassword.getText().toString();

        if (!usuario.isEmpty() && !password.isEmpty()) {
            getToken(usuario, password);
        } else {
            btnIngresar.setEnabled(true);
            Constantes.alertWarning(Constantes.VALIDACION, "Complete los campos");
        }

    }

    private void getToken(String usuario, String password) {
        String base = "clientIdPassword:secret";
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        Call<ResponseToken> call = sToken.login(authHeader, "password", "clientIdPassword", usuario, password);
        call.enqueue(new Callback<ResponseToken>() {
            @Override
            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                if (response.isSuccessful()) {
                    ResponseToken resp = response.body();

                    SharedPreferencesManager.setPreferences(Constantes.KEY_USER, usuario);
                    SharedPreferencesManager.setPreferences(Constantes.KEY_PASSWORD, password);
                    SharedPreferencesManager.setPreferences(Constantes.KEY_RECORDAR, swtRecordar.isChecked());

                    SharedPreferencesManager.setPreferences(Constantes.KEY_TOKEN, resp.getAccess_token());
                    SharedPreferencesManager.setPreferences(Constantes.KEY_REFRESH_TOKEN, resp.getRefresh_token());

                    listarAgrupadorModulos();
                    getEmpleado();
                } else {
                    btnIngresar.setEnabled(true);
                    Constantes.alertInfo(Constantes.VALIDACION, "Verifique que su usuario o contraseña sean correctos");
                }
            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                btnIngresar.setEnabled(true);
                Constantes.alertError(Constantes.PROBLEMA, t.getMessage());
            }
        });
    }

    private void getEmpleado() {
        Call<ResponseEmpleado> call = sEmpleado.getUser();
        call.enqueue(new Callback<ResponseEmpleado>() {
            @Override
            public void onResponse(Call<ResponseEmpleado> call, Response<ResponseEmpleado> response) {
                if (response.isSuccessful()) {
                    Empleado empleado = response.body().getDefaultObj();
                    Perfiles perfiles = empleado.getPerfiles();
                    SharedPreferencesManager.setPreferences(empleado);
                    SharedPreferencesManager.setPreferences(perfiles);
                } else {
                    Constantes.alertError(Constantes.PROBLEMA, "No se logró conseguir información del usuario");
                }
            }

            @Override
            public void onFailure(Call<ResponseEmpleado> call, Throwable t) {
                Constantes.alertError(Constantes.PROBLEMA, t.getMessage());
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
                }
            }

            @Override
            public void onFailure(Call<ResponseAgrupadorModulos> call, Throwable t) {
                btnIngresar.setEnabled(true);
                Constantes.alertError(Constantes.PROBLEMA, t.getMessage());
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

                                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(main);
                                finish();
                            } else {
                                Constantes.alertInfo(Constantes.SIN_PERMISO, "No cuenta con alguna página asignada");
                            }
                        } else {
                            Constantes.alertInfo(Constantes.SIN_PERMISO, "No cuenta con alguna página asignada");
                        }
                    } else {
                        Constantes.alertWarning(Constantes.SIN_PERMISO, "No cuenta con permisos para ingresar a la app");
                    }

                } else {
                    Constantes.alertError(Constantes.PROBLEMA, "No se logró conseguir información de los accesos");
                }
                btnIngresar.setEnabled(true);
            }

            @Override
            public void onFailure(Call<ResponseModulo> call, Throwable t) {
                btnIngresar.setEnabled(true);
                Constantes.alertError(Constantes.PROBLEMA, t.getMessage());
            }
        });
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
}