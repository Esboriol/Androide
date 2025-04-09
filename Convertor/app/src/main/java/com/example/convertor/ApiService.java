package com.example.convertor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.google.gson.JsonObject;

public interface ApiService {
    @GET("json/last/{pares}")
    Call<JsonObject> getTaxas(@Path("pares") String pares);
}
