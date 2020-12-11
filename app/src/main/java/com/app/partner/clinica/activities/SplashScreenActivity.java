package com.app.partner.clinica.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import com.app.partner.clinica.R;
import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.common.SharedPreferencesManager;
import com.app.partner.clinica.models.request.Empleado;
import com.app.partner.clinica.models.response.ResponseEmpleado;
import com.app.partner.clinica.models.response.ResponseToken;
import com.app.partner.clinica.services.instance.IEmpleado;
import com.app.partner.clinica.services.instance.IToken;
import com.app.partner.clinica.services.service.EmpleadoService;
import com.app.partner.clinica.services.service.TokenService;

public class SplashScreenActivity extends AppCompatActivity {

    IToken iToken;
    TokenService sToken;

    String usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        retrofitInit();

        if (SharedPreferencesManager.getPrefBoolean(Constantes.KEY_RECORDAR)) {
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

                    Intent main = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                } else {
                    irLogin();
                    Toast.makeText(SplashScreenActivity.this, "Upps! Parece que su contraseña cambió", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                irLogin();
                Toast.makeText(SplashScreenActivity.this, "SUPER ERROR: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void irLogin() {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
        finish();
    }

    private void retrofitInit() {
        iToken = IToken.getInstance();
        sToken = iToken.getTokenService();
    }
}