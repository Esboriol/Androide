package com.example.tarefas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TarefaAdapter extends BaseAdapter {

    private Context context;
    private List<Tarefa> tarefas;

    public TarefaAdapter(Context context, List<Tarefa> tarefas) {
        this.context = context;
        this.tarefas = tarefas;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_tarefa, parent, false);

        Tarefa tarefa = tarefas.get(position);

        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvDescricao = convertView.findViewById(R.id.tvDescricao);
        TextView tvFinalizada = convertView.findViewById(R.id.tvFinalizado);

        tvTitulo.setText(tarefa.getTitulo());
        tvDescricao.setText(tarefa.getDescricao());
        tvFinalizada.setText(tarefa.isFinalizado()? "Sim" : "Não");

        return convertView;
    }
}
