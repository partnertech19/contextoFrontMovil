package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.request.Terapiaindividual;
import com.app.partner.clinica.models.response.ResponseTerapiaIndividual;
import com.app.partner.clinica.models.response.ResponseTerapiaIndividualFecha;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TerapiaIndividualService {

    @POST("api/terapiaIndividual/retornarFechaTeraEntr")
    Call<ResponseTerapiaIndividual> retornarFechas(@Body Terapiaindividual terapiaIndividual);

    @POST("api/terapiaIndividual/listarTeraEntreDoctorFecha")
    Call<ResponseTerapiaIndividual> listarPorDoctorFecha(@Body Terapiaindividual terapiaIndividual);

    @POST("api/terapiaIndividual/insertar")
    Call<ResponseTerapiaIndividual> insertar(@Body Terapiaindividual terapiaIndividual);

    @PUT("api/terapiaIndividual/actualizar")
    Call<ResponseTerapiaIndividual> actualizar(@Body Terapiaindividual terapiaIndividual);

    @DELETE("api/terapiaIndividual/eliminar/{iidterapiaindiv}")
    Call<ResponseTerapiaIndividual> eliminar(@Path("iidterapiaindiv") Integer iidterapiaindiv);
}
