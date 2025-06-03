package com.example.contatos;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Cadastroapi {
    @GET("/contacts")
    Call<List<Cadastro>> getCadastros();
}
