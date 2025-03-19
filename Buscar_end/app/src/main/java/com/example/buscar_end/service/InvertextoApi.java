package com.example.buscar_end.service;

import com.example.buscar_end.model.Logradolro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InvertextoApi {
    @GET("/v1/cep/{numero}")
    Call<Logradolro> getEndereco(
            @Path("numero") String numero,
            @Query("token") String token
    );
}
