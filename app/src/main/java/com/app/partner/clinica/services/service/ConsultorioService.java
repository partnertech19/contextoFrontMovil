package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.request.Consultoriodoctor;
import com.app.partner.clinica.models.response.ResponseConsultorio;
import com.app.partner.clinica.models.response.ResponseConsultoriodoctor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ConsultorioService {

    @POST("api/consultorio/listarPorDoctor")
    Call<ResponseConsultorio> listarPorDoctor(@Body Integer iiddoctor);

    @POST("api/consultorioDoctor/listarPorFechaMovil")
    Call<ResponseConsultoriodoctor> listarConsulDoctorPorFecha(@Body Consultoriodoctor consultoriodoctor);
}
