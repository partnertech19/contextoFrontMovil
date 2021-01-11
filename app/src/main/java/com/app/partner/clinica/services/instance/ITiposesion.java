package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.models.request.Tiposesion;
import com.app.partner.clinica.services.TokenInterceptor;
import com.app.partner.clinica.services.service.PacienteService;
import com.app.partner.clinica.services.service.TiposesionService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ITiposesion {

    private static ITiposesion instance = null;
    private TiposesionService service;
    private Retrofit retrofit;

    public ITiposesion() {

        OkHttpClient.Builder okHttpCliente = new OkHttpClient.Builder();
        okHttpCliente.addInterceptor(new TokenInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente.build())
                .build();
        service = retrofit.create(TiposesionService.class);
    }

    public static ITiposesion getInstance() {
        if (instance == null) {
            instance = new ITiposesion();
        }
        return instance;
    }

    public TiposesionService getService() {
        return service;
    }
}
