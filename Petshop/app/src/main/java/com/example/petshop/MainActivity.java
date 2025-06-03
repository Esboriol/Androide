package com.example.petshop;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvProdutos;
    private ProdutoAdapter adapter;

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
        buscarDados();

    }

    private void buscarDados() {
        RequestQueue fila = Volley.newRequestQueue(this);
        String url = "https://api-petshop-cdzf.onrender.com/products";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Produto>>() {}.getType();
                    List<Produto> productList = gson.fromJson(response, listType);

                    adapter = new ProdutoAdapter(MainActivity.this, productList);
                    lvProdutos.setAdapter(adapter);
                },
                error -> {
                    Toast.makeText(MainActivity.this, "Erro na requisição", Toast.LENGTH_LONG).show();
                });
        fila.add(stringRequest);
    }
}