package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.TokenInterceptor;
import com.app.partner.clinica.services.service.ConsultorioService;
import com.app.partner.clinica.services.service.EmpleadoService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IConsultorio {

    private static IConsultorio instance = null;
    private ConsultorioService service;
    private Retrofit retrofit;

    public IConsultorio() {

        OkHttpClient.Builder okHttpCliente = new OkHttpClient.Builder();
        okHttpCliente.addInterceptor(new TokenInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente.build())
                .build();
        service = retrofit.create(ConsultorioService.class);
    }

    public static IConsultorio getInstance() {
        if (instance == null) {
            instance = new IConsultorio();
        }
        return instance;
    }

    public ConsultorioService getService() {
        return service;
    }
}
