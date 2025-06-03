package com.example.contato_volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContatosAdapter extends BaseAdapter {
    private Context context;
    private List<Contatos> contatos;

    public ContatosAdapter(Context context, List<Contatos> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int i) {
        return contatos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.contatos, parent, false);

        Contatos contato = contatos.get(i);

        TextView tvNome =convertView.findViewById(R.id.tvNome);
        TextView tvTelefone = convertView.findViewById(R.id.tvTelefone);
        TextView tvFav = convertView.findViewById(R.id.tvFavorito);

        tvNome.setText(contato.getContact_name());
        tvTelefone.setText(contato.getContact_phone());
        tvFav.setText(contato.getContact_fav()? "Sim" : "Não");

        return convertView;
    }
}
