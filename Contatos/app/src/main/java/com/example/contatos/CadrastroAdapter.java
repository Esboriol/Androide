package com.example.contatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CadrastroAdapter extends BaseAdapter {
    private Context context;
    private List<Cadastro> cadastros;

    public CadrastroAdapter(Context context, List<Cadastro> cadastros) {
        this.context = context;
        this.cadastros = cadastros;
    }

    @Override
    public int getCount() {
        return cadastros.size();
    }

    @Override
    public Object getItem(int position) {
        return cadastros.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup  parent) {
        convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_cadrastro, parent, false);

        Cadastro cadrastro = cadastros.get(position);

        TextView tvNome = convertView.findViewById(R.id.tvNome);
        TextView tvTelefone = convertView.findViewById(R.id.tvTelefone);
        TextView tvFavorito = convertView.findViewById(R.id.tvFavorito);

        tvNome.setText(cadrastro.getNome());
        tvTelefone.setText(cadrastro.getNumero());
        tvFavorito.setText(cadrastro.isFavorito()? "Sim" : "Não");

        return convertView;
    }

}
