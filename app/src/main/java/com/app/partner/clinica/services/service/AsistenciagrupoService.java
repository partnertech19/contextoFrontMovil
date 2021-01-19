package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.request.AgrupadorModulos;
import com.app.partner.clinica.models.request.Asistenciagrupo;
import com.app.partner.clinica.models.response.ResponseAgrupadorModulos;
import com.app.partner.clinica.models.response.ResponseWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AsistenciagrupoService {

    @POST("api/asistenciaGrupo/insertaAsistenciaGrupoMovil")
    Call<ResponseWrapper> insertaAsistenciaGrupoMovil(@Body Asistenciagrupo asistenciagrupo);
}
