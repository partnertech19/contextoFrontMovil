package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.TokenInterceptor;
import com.app.partner.clinica.services.service.AgrupadorModulosService;
import com.app.partner.clinica.services.service.AsistenciagrupoService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IAsistenciagrupo {

    private static IAsistenciagrupo instance = null;
    private AsistenciagrupoService service;
    private Retrofit retrofit;

    public IAsistenciagrupo() {

        OkHttpClient.Builder okHttpCliente = new OkHttpClient.Builder();
        okHttpCliente.addInterceptor(new TokenInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente.build())
                .build();
        service = retrofit.create(AsistenciagrupoService.class);
    }

    public static IAsistenciagrupo getInstance() {
        if (instance == null) {
            instance = new IAsistenciagrupo();
        }
        return instance;
    }

    public AsistenciagrupoService getService() {
        return service;
    }
}
