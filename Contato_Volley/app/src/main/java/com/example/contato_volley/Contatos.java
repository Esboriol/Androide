package com.example.contato_volley;

public class Contatos {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact_name() {
        return name;
    }

    public void setContact_name(String contact_name) {
        this.name = contact_name;
    }

    public Boolean getContact_fav() {
        return favorite;
    }

    public void setContact_fav(Boolean contact_favorite) {
        this.favorite = contact_favorite;
    }

    public String getContact_phone(){
        return phone;  // Isso está correto?
    }

    public void setContact_phone(String contact_number) {
        this.phone = contact_number;
    }

    private String name;
    private Boolean favorite;
    private String phone;
}
