package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.TokenInterceptor;
import com.app.partner.clinica.services.service.TerapiaIndividualService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ITerapiaIndividual {

    private static ITerapiaIndividual instance = null;
    private TerapiaIndividualService service;
    private Retrofit retrofit;

    public ITerapiaIndividual() {

        OkHttpClient.Builder okHttpCliente = new OkHttpClient.Builder();
        okHttpCliente.addInterceptor(new TokenInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente.build())
                .build();
        service = retrofit.create(TerapiaIndividualService.class);
    }

    public static ITerapiaIndividual getInstance() {
        if (instance == null) {
            instance = new ITerapiaIndividual();
        }
        return instance;
    }

    public TerapiaIndividualService getService() {
        return service;
    }
}
