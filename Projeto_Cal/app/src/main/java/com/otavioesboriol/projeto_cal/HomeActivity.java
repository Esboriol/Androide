package com.otavioesboriol.projeto_cal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnadicao = findViewById(R.id.btnAdicao);
        btnadicao.setOnClickListener(this);
        Button btnsubtracao = findViewById(R.id.btnSubtracao);
        btnsubtracao.setOnClickListener(this);
        Button btnmultiplicacao = findViewById(R.id.btnMultiplicacao);
        btnmultiplicacao.setOnClickListener(this);
        Button btnadivissao = findViewById(R.id.btnDivissao);
        btnadivissao.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        Intent telaCalcular = new Intent(HomeActivity.this, Calcula.class);
        if (view.getId() == R.id.btnAdicao) {
            telaCalcular.putExtra("parametro", "Somar Números");
        } else if (view.getId() == R.id.btnSubtracao) {
            telaCalcular.putExtra("parametro", "Subtrair Números");
        } else if (view.getId() == R.id.btnMultiplicacao) {
            telaCalcular.putExtra("parametro", "Multiplicar Números");
        } else if (view.getId() == R.id.btnDivissao) {
            telaCalcular.putExtra("parametro", "Dividir Números");
        }

        startActivity(telaCalcular);
    }
}