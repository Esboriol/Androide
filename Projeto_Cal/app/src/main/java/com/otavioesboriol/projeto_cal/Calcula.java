package com.otavioesboriol.projeto_cal;

import android.content.Intent;
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

import org.w3c.dom.Text;

public class Calcula extends AppCompatActivity implements View.OnClickListener {

    private String titulo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcula);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);

        recuperarParametro();
    }

    private void recuperarParametro() {
        Intent telaHome = getIntent();
        titulo = telaHome.getStringExtra("parametro");

        TextView tvTITULO = findViewById(R.id.tvTitulo);
        tvTITULO.setText(titulo);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCalcular) {

            EditText edPrimeiro = findViewById(R.id.edPrimeiro);
            EditText edSegundo = findViewById(R.id.edSegundo);

            int numero1 = Integer.parseInt(edPrimeiro.getText().toString());
            int numero2 = Integer.parseInt(edSegundo.getText().toString());
            int resultado = 0;

            if (titulo.equals("Somar Números")) {
                //Soma dos numeros
                resultado = numero1 + numero2;
            } else if (titulo.equals("Subtrair Números")) {
                resultado = numero1 - numero2;

            } else if (titulo.equals("Multiplicar Números")) {
                resultado = numero1 * numero2;

            } else if (titulo.equals("Dividir Números")) {
                resultado = numero1 / numero2;

            }

            Toast.makeText(Calcula.this, "O resultado é " + resultado, Toast.LENGTH_LONG).show();
        }
    }
}