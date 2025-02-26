package com.otavioesboriol.entre_via;

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

public class Calcu extends AppCompatActivity implements View.OnClickListener {

    private String titulo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnCa = findViewById(R.id.btn_cal);
        btnCa.setOnClickListener(this);

        recuperar();
    }

    private void recuperar (){
        Intent tega = getIntent();
        titulo = tega.getStringExtra("parametro");

        TextView tvTITU =findViewById(R.id.tvTitu);
        tvTITU.setText(titulo);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_cal) {

            EditText edDis = findViewById(R.id.edKg);

            float Distancia = Float.parseFloat(edDis.getText().toString());
            float resultado = 0;

            if (titulo.equals("Valor Gasolina")) {
                //Soma dos numeros
                resultado = Distancia * 5.99f;
            } else if (titulo.equals("Valor Etanol")) {
                resultado = Distancia * 3.99f;
            }

            Toast.makeText(Calcu.this, "O total da viagem sera de R$" + resultado, Toast.LENGTH_LONG).show();
        }
    }
}