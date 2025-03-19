package com.example.buscar_end;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.buscar_end.model.Logradolro;
import com.example.buscar_end.service.InvertextoApi;

import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity{

    private TextView tvInformacoes;

    private EditText CEP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnFinalizar = findViewById(R.id.btFinalizar);
        CEP = findViewById(R.id.edNumero);
        tvInformacoes = findViewById(R.id.txInfo);

        btnFinalizar.setOnClickListener(v -> {
            String cep = CEP.getText().toString();
            if (!cep.isEmpty()) {
                consultar(cep);
            } else {
                Toast.makeText(this, "Digite um CEP válido!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void consultar(String numeroCep) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InvertextoApi invertextoApi = retrofit.create(InvertextoApi.class);

        Call<Logradolro> call = invertextoApi.getEndereco(numeroCep, Constantes.TOKEN);

        call.enqueue(new Callback<Logradolro>() {
            @Override
            public void onResponse(Call<Logradolro> call, Response<Logradolro> response) {
                if (response.isSuccessful()) {
                    Logradolro logradolro = response.body();
                    tvInformacoes.setText(logradolro.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao converter reposta da Api", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Logradolro> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Erro ao realizar consulta da Api", Toast.LENGTH_LONG).show();
            }
        });


    }
}