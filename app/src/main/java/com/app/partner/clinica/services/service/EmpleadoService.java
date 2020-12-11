package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.request.AgrupadorModulos;
import com.app.partner.clinica.models.response.ResponseAgrupadorModulos;
import com.app.partner.clinica.models.response.ResponseEmpleado;
import com.app.partner.clinica.models.response.ResponseModulo;
import com.app.partner.clinica.models.response.ResponseWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EmpleadoService {

    @POST("api/empleado/getuser")
    Call<ResponseEmpleado> getUser();

    @POST("api/empleado/accesosdesdemodulo")
    Call<ResponseModulo> retornarAcceso(@Body AgrupadorModulos agrupadorModulos);

}
