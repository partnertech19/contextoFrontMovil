package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.request.AgrupadorModulos;
import com.app.partner.clinica.models.response.ResponseAgrupadorModulos;
import com.app.partner.clinica.models.response.ResponseEmpleado;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AgrupadorModulosService {

    @POST("api/agrupadormodulos/listmodulosusuario")
    Call<ResponseAgrupadorModulos> getAgrupadormodulos(@Body AgrupadorModulos agrupadorModulos);
}
