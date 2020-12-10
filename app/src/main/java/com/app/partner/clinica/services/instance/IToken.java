package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.service.TokenService;
import com.app.partner.clinica.services.service.UsuarioService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IToken {

    private static IToken instance = null;
    private TokenService tokenService;
    private Retrofit retrofit;

    public IToken() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tokenService = retrofit.create(TokenService.class);
    }

    public static IToken getInstance() {
        if (instance == null) {
            instance = new IToken();
        }
        return instance;
    }

    public TokenService getTokenService() {
        return tokenService;
    }
}
