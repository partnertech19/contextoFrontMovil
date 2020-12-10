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
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.common.SharedPreferencesManager;
import com.app.partner.clinica.models.request.Empleado;
import com.app.partner.clinica.models.response.ResponseEmpleado;
import com.app.partner.clinica.models.response.ResponseToken;
import com.app.partner.clinica.models.response.ResponseWrapper;
import com.app.partner.clinica.services.instance.IEmpleado;
import com.app.partner.clinica.services.instance.IToken;
import com.app.partner.clinica.services.service.EmpleadoService;
import com.app.partner.clinica.services.service.TokenService;

public class LoginActivity extends AppCompatActivity {

    Button btnIngresar;
    EditText edtUsuario, edtPassword;
    Switch swtRecordar;

    IToken iToken;
    TokenService sToken;
    IEmpleado iEmpleado;
    EmpleadoService sEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        retrofitInit();

        obtenerViews();
        eventosViews();

        if (SharedPreferencesManager.getPrefBoolean(Constantes.KEY_RECORDAR)) {
            String user = SharedPreferencesManager.getPrefString(Constantes.KEY_USER);
            String password = SharedPreferencesManager.getPrefString(Constantes.KEY_PASSWORD);
            edtUsuario.setText(user);
            edtPassword.setText(password);
        }
    }

    private void retrofitInit() {
        iToken = IToken.getInstance();
        sToken = iToken.getTokenService();

        iEmpleado = IEmpleado.getInstance();
        sEmpleado = iEmpleado.getEmpleadoService();
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
            Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
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
                    login();
                } else {
                    Toast.makeText(LoginActivity.this, "Verifique que su usuario o contraseña sean correctos", Toast.LENGTH_SHORT).show();
                }
                btnIngresar.setEnabled(true);
            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                btnIngresar.setEnabled(true);
                Toast.makeText(LoginActivity.this, "SUPER ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        Call<ResponseEmpleado> call = sEmpleado.getUser();
        call.enqueue(new Callback<ResponseEmpleado>() {
            @Override
            public void onResponse(Call<ResponseEmpleado> call, Response<ResponseEmpleado> response) {
                if (response.isSuccessful()) {
                    Empleado empleado = (Empleado) response.body().getDefaultObj();
                    SharedPreferencesManager.setPreferences(empleado);
                    Intent loguin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(loguin);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "ERROR INTERNO", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEmpleado> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "SUPER ERROR INTERNO: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}