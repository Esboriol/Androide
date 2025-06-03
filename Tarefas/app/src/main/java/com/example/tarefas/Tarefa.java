package com.example.tarefas;

import com.google.gson.annotations.SerializedName;

public class Tarefa {
    @SerializedName("title")
    private String titulo;
    @SerializedName("description")
    private String descricao;
    @SerializedName("done")
    private boolean finalizado;

    public Tarefa(String titulo, String descricao, boolean finalizado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.finalizado = finalizado;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }
}
