package com.app.partner.clinica.services.service;

import com.app.partner.clinica.models.response.ResponseToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UsuarioService {

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/token")
    Call<ResponseToken> login(@Header("Authorization") String authHeader,
                              @Field("grant_type") String grant_type,
                              @Field("client_id") String client_id,
                              @Field("username") String username,
                              @Field("password") String password);
}
