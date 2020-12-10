package com.app.partner.clinica.services;

import com.app.partner.clinica.common.Constantes;
import com.app.partner.clinica.common.SharedPreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SharedPreferencesManager.getPrefString(Constantes.KEY_TOKEN);
        Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).build();
        return chain.proceed(request);
    }
}
