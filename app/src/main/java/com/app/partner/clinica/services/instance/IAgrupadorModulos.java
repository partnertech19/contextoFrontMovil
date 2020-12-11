package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.TokenInterceptor;
import com.app.partner.clinica.services.service.AgrupadorModulosService;
import com.app.partner.clinica.services.service.EmpleadoService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IAgrupadorModulos {

    private static IAgrupadorModulos instance = null;
    private AgrupadorModulosService agrupadorModulosService;
    private Retrofit retrofit;

    public IAgrupadorModulos() {

        OkHttpClient.Builder okHttpCliente = new OkHttpClient.Builder();
        okHttpCliente.addInterceptor(new TokenInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente.build())
                .build();
        agrupadorModulosService = retrofit.create(AgrupadorModulosService.class);
    }

    public static IAgrupadorModulos getInstance() {
        if (instance == null) {
            instance = new IAgrupadorModulos();
        }
        return instance;
    }

    public AgrupadorModulosService getAgrupadorModulosService() {
        return agrupadorModulosService;
    }
}
