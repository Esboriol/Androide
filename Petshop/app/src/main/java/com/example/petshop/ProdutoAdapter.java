package com.example.petshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter {

    private Context context;
    private List<Produto> produtos;

    public ProdutoAdapter(Context context,List<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false);

        Produto produto = produtos.get(position);

        TextView tvNome = convertView.findViewById(R.id.tvNome);
        TextView tvDescricao = convertView.findViewById(R.id.tvDescricao);
        TextView tvPreco = convertView.findViewById(R.id.tvPreco);

        tvNome.setText(produto.getProduct_name());
        tvDescricao.setText(produto.getProduct_description());
        tvPreco.setText("R$ " + produto.getProduct_price());

        return convertView;
    }
}
