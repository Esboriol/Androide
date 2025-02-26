package com.otavioesboriol.entre_via;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Escolher extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_escolher);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btngas =findViewById(R.id.btn_Gaso);
        btngas.setOnClickListener(this);
        Button btne = findViewById(R.id.btn_Eta);
        btne.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent telaCalcular = new Intent(Escolher.this, Calcu.class);
        if (view.getId() == R.id.btn_Gaso) {
            telaCalcular.putExtra("parametro", "Valor Gasolina");
        } else if (view.getId() == R.id.btn_Eta) {
            telaCalcular.putExtra("parametro", "Valor Etanol");
        }

        startActivity(telaCalcular);
    }

}