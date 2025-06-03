package com.example.contatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView lvCadastro;
    private CadrastroAdapter adapter;

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

        ImageView ivCadastrar = findViewById(R.id.ivCadas);

        ivCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }

        });

        lvCadastro = findViewById(R.id.lvCadastro);
        buscarContatos();
    }

    private void buscarContatos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-contacts-qfxx.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Cadastroapi api = retrofit.create(Cadastroapi.class);
        Call<List<Cadastro>> call = api.getCadastros();

        call.enqueue(new Callback<List<Cadastro>>() {
            @Override
            public void onResponse(Call<List<Cadastro>> call, Response<List<Cadastro>> response) {
                if (response.isSuccessful()) {
                    List<Cadastro> cadastros =response.body();
                    System.out.println(cadastros);
                    adapter = new CadrastroAdapter(MainActivity.this, cadastros);
                    lvCadastro.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Cadastro>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Erro ao buscar tarefas", Toast.LENGTH_SHORT).show();
            }
        });

    }
}