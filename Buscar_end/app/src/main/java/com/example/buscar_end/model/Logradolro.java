package com.example.buscar_end.model;

public class Logradolro {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String complement;
    private String ibge;

    // Getters e Setters
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    // Método toString para exibir as informações
    @Override
    public String toString() {
        return "CEP: " + cep + "\n" +
                "Estado: " + state + "\n" +
                "Cidade: " + city + "\n" +
                "Bairro: " + neighborhood + "\n" +
                "Rua: " + street + "\n" +
                "Complemento: " + complement + "\n" +
                "IBGE: " + ibge;
    }
}
