package com.app.partner.clinica.services.instance;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.services.TokenInterceptor;
import com.app.partner.clinica.services.service.EmpleadoService;
import com.app.partner.clinica.services.service.TokenService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IEmpleado {

    private static IEmpleado instance = null;
    private EmpleadoService empleadoService;
    private Retrofit retrofit;

    public IEmpleado() {

        OkHttpClient.Builder okHttpCliente = new OkHttpClient.Builder();
        okHttpCliente.addInterceptor(new TokenInterceptor());

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL_BACK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente.build())
                .build();
        empleadoService = retrofit.create(EmpleadoService.class);
    }

    public static IEmpleado getInstance() {
        if (instance == null) {
            instance = new IEmpleado();
        }
        return instance;
    }

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }
}
