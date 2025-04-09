package com.example.convertor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ConversorViewModel extends ViewModel {

    private MutableLiveData<String> resultado = new MutableLiveData<>();
    private MutableLiveData<Boolean> carregando = new MutableLiveData<>();
    private MutableLiveData<String> erro = new MutableLiveData<>();

    // Cache das taxas de câmbio
    private Map<String, Double> taxas = new HashMap<>();
    private String[] moedas = {"BRL", "EUR", "USD", "CAD", "ARS"};

    // Getters para o LiveData
    public MutableLiveData<String> getResultado() { return resultado; }
    public MutableLiveData<Boolean> getCarregando() { return carregando; }
    public MutableLiveData<String> getErro() { return erro; }

    // Busca as taxas da API
    public void buscarTaxas(String moedaOrigem) {
        carregando.setValue(true);

        // Monta os pares de moedas (ex: USD-BRL,USD-EUR...)
        StringBuilder pares = new StringBuilder();
        for (String moeda : moedas) {
            if (!moeda.equals(moedaOrigem)) {
                pares.append(moedaOrigem).append("-").append(moeda).append(",");
            }
        }
        String paresStr = pares.length() > 0 ? pares.substring(0, pares.length() - 1) : "";

        // Chamada à API usando Retrofit
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        Call<JsonObject> call = api.getTaxas(paresStr);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                carregando.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject json = response.body();
                    for (String moeda : moedas) {
                        if (!moeda.equals(moedaOrigem)) {
                            String chave = moedaOrigem + moeda; // Ex: "USDBRL"
                            if (json.has(chave)) {
                                double taxa = json.getAsJsonObject(chave).get("bid").getAsDouble();
                                taxas.put(moedaOrigem + "-" + moeda, taxa);
                            }
                        }
                    }
                } else {
                    erro.setValue("Erro na API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                carregando.setValue(false);
                erro.setValue("Falha na rede: " + t.getMessage());
            }
        });
    }

    // Realiza a conversão
    public void converter(String origem, String destino, double valor) {
        if (origem.equals(destino)) {
            resultado.setValue("Moedas iguais!");
            return;
        }

        Double taxa = taxas.get(origem + "-" + destino);
        if (taxa != null) {
            double resultadoCalc = valor * taxa;
            resultado.setValue(String.format("%.2f %s = %.2f %s", valor, origem, resultadoCalc, destino));
        } else {
            erro.setValue("Taxa não encontrada");
        }
    }
}
