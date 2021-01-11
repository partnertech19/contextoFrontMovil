package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.service.UsuarioService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IUsuario {

    private static IUsuario instance = null;
    private UsuarioService service;
    private Retrofit retrofit;

    public IUsuario() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UsuarioService.class);
    }

    public static IUsuario getInstance() {
        if (instance == null) {
            instance = new IUsuario();
        }
        return instance;
    }

    public UsuarioService getService() {
        return service;
    }
}
