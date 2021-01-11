package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.response.ResponsePaciente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PacienteService {

    @POST("api/pacientes/listarPorDoctor")
    Call<ResponsePaciente> listarPorDoctor(@Body Integer iidterapeuta);
}
