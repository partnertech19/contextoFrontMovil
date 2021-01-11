package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.TokenInterceptor;
import com.app.partner.clinica.services.service.PacienteService;
import com.app.partner.clinica.services.service.TerapiaIndividualService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IPaciente {

    private static IPaciente instance = null;
    private PacienteService service;
    private Retrofit retrofit;

    public IPaciente() {

        OkHttpClient.Builder okHttpCliente = new OkHttpClient.Builder();
        okHttpCliente.addInterceptor(new TokenInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente.build())
                .build();
        service = retrofit.create(PacienteService.class);
    }

    public static IPaciente getInstance() {
        if (instance == null) {
            instance = new IPaciente();
        }
        return instance;
    }

    public PacienteService getService() {
        return service;
    }
}
