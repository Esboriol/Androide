package com.example.tarefas;

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

    private ListView lvTarefas;
    private TarefaAdapter adapter;

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

        ImageView ivCadastrar = findViewById(R.id.ivCadastrar);

        ivCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        lvTarefas = findViewById(R.id.lvTarefas);
        buscarTarefas();

    }

    private void buscarTarefas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-tasks-4n17.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TarefaApi api = retrofit.create(TarefaApi.class);
        Call<List<Tarefa>> call = api.getTarefas();

        call.enqueue(new Callback<List<Tarefa>>() {
            @Override
            public void onResponse(Call<List<Tarefa>> call, Response<List<Tarefa>> response) {
                if (response.isSuccessful()) {
                    List<Tarefa> tarefas = response.body();
                    adapter = new TarefaAdapter(MainActivity.this, tarefas);
                    lvTarefas.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Tarefa>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Erro ao buscar tarefas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}