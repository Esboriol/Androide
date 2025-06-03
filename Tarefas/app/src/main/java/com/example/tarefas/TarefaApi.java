package com.example.tarefas;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TarefaApi {
    @GET("/tasks")
    Call<List<Tarefa>> getTarefas();
}
