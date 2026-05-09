package com.example.projetofinalpdm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PontuacaoAdapter extends RecyclerView.Adapter<PontuacaoAdapter.ViewHolder> {

    private final List<Pontuacao> lista;

    public PontuacaoAdapter(List<Pontuacao> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvScore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvScore = itemView.findViewById(R.id.tvScore);
        }
    }

    @NonNull
    @Override
    public PontuacaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pontuacao, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PontuacaoAdapter.ViewHolder holder, int position) {
        Pontuacao p = lista.get(position);
        holder.tvNome.setText(p.getNome());
        holder.tvScore.setText(String.valueOf(p.getPontuacao()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
