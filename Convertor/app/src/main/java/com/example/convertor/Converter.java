package com.example.convertor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

public class Converter extends AppCompatActivity {
    private ConversorViewModel viewModel;
    private Spinner spinnerOrigem, spinnerDestino;
    private TextInputEditText etValor;
    private ProgressBar progressBar;
    private final String[] moedas = {"BRL", "EUR", "USD", "CAD", "ARS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        spinnerOrigem = findViewById(R.id.spinner_origem);
        spinnerDestino = findViewById(R.id.spinner_destino);
        etValor = findViewById(R.id.et_valor);
        Button btnConverter = findViewById(R.id.btn_converter);
        TextView tvResultado = findViewById(R.id.tv_resultado);
        progressBar = findViewById(R.id.progressBar);

        viewModel = new ViewModelProvider(this).get(ConversorViewModel.class);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigem.setAdapter(adapter);
        spinnerDestino.setAdapter(adapter);

        viewModel.getResultado().observe(this, tvResultado::setText);
        viewModel.getCarregando().observe(this, isLoading ->
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        viewModel.getErro().observe(this, mensagem ->
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show());

        spinnerOrigem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.buscarTaxas(moedas[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnConverter.setOnClickListener(v -> {
            String valorStr = etValor.getText().toString();
            if (!valorStr.isEmpty()) {
                double valor = Double.parseDouble(valorStr);
                String origem = spinnerOrigem.getSelectedItem().toString();
                String destino = spinnerDestino.getSelectedItem().toString();
                viewModel.converter(origem, destino, valor);
            } else {
                Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
