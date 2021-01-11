package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.response.ResponseTiposesion;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TiposesionService {

    @GET("api/tipoSesion/listar")
    Call<ResponseTiposesion> listar();

}
