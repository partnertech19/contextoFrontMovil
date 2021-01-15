package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.request.AgendaClonada;
import com.app.partner.clinica.models.request.Terapiaindividual;
import com.app.partner.clinica.models.response.ResponseTerapiaEntrevista;
import com.app.partner.clinica.models.response.ResponseTerapiaindividual;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TerapiaIndividualService {

    @POST("api/terapiaIndividual/retornarFechaTeraEntr")
    Call<ResponseTerapiaEntrevista> retornarFechas(@Body Terapiaindividual terapiaIndividual);

    @POST("api/terapiaIndividual/listarTeraEntreDoctorFecha")
    Call<ResponseTerapiaEntrevista> listarPorDoctorFecha(@Body Terapiaindividual terapiaIndividual);

    @POST("api/terapiaIndividual/insertar")
    Call<ResponseTerapiaEntrevista> insertar(@Body Terapiaindividual terapiaIndividual);

    @PUT("api/terapiaIndividual/actualizar")
    Call<ResponseTerapiaEntrevista> actualizar(@Body Terapiaindividual terapiaIndividual);

    @DELETE("api/terapiaIndividual/eliminar/{iidterapiaindiv}")
    Call<ResponseTerapiaEntrevista> eliminar(@Path("iidterapiaindiv") Integer iidterapiaindiv);

    @POST("api/terapiaIndividual/clonarAgenda")
    Call<ResponseTerapiaindividual> clonarAgenda(@Body AgendaClonada agendaClonada);
}
