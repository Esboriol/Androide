package com.example.contatos;

import com.google.gson.annotations.SerializedName;

public class Cadastro {
    @SerializedName("name")
    private String nome;
    @SerializedName("phone")
    private String numero;
    @SerializedName("favorite")
    private Boolean favorito;

    public Cadastro(String nome, String numero, Boolean favorito) {
        this.nome = nome;
        this.numero = numero;
        this.favorito = favorito;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public Boolean isFavorito() {
        return favorito;
    }

}