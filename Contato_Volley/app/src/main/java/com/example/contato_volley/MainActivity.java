package com.example.contato_volley;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProdutos;
    private ContatosAdapter adapter;
    private final List<Contatos> listaContatos = new ArrayList<>(); // Lista para dados

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

        lvProdutos = findViewById(R.id.lvProdutos);
        adapter = new ContatosAdapter(this, listaContatos); // Inicializa adapter vazio
        lvProdutos.setAdapter(adapter);

        buscarContatos();
    }

    private void buscarContatos() {
        RequestQueue fila = Volley.newRequestQueue(this);
        String url = "https://api-contacts-qfxx.onrender.com/contacts";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> new Thread(() -> { // Processamento em background
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Contatos>>() {}.getType();
                    List<Contatos> novosContatos = gson.fromJson(response, listType);

                    runOnUiThread(() -> { // Atualização na UI thread
                        listaContatos.clear();
                        listaContatos.addAll(novosContatos);
                        adapter.notifyDataSetChanged();
                    });
                }).start(),
                error -> Toast.makeText(getApplicationContext(), "Erro: " + error.getMessage(), Toast.LENGTH_LONG).show()
        );

        // Configurar timeout maior
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        fila.add(stringRequest);
    }
}