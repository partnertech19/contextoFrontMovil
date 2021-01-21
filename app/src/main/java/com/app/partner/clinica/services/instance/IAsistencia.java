package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.TokenInterceptor;
import com.app.partner.clinica.services.service.AsistenciaService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IAsistencia {

    private static IAsistencia instance = null;
    private AsistenciaService service;
    private Retrofit retrofit;

    public IAsistencia() {

        OkHttpClient.Builder okHttpCliente = new OkHttpClient.Builder();
        okHttpCliente.addInterceptor(new TokenInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente.build())
                .build();
        service = retrofit.create(AsistenciaService.class);
    }

    public static IAsistencia getInstance() {
        if (instance == null) {
            instance = new IAsistencia();
        }
        return instance;
    }

    public AsistenciaService getService() {
        return service;
    }
}
