package com.example.projetofinalpdm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRanking;
    private Button buttonJogar, buttonLimpar;
    private PontuacaoAdapter adapter;
    private PontuacaoDao pontuacaoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewRanking = findViewById(R.id.recyclerViewRanking);
        buttonJogar = findViewById(R.id.buttonJogar);
        buttonLimpar = findViewById(R.id.buttonLimpar);

        pontuacaoDao = AppDatabase.getInstance(this).pontuacaoDao();

        recyclerViewRanking.setLayoutManager(new LinearLayoutManager(this));
        carregarRanking();

        buttonJogar.setOnClickListener(v -> {
            startActivity(new Intent(this, GameActivity.class));
        });

        buttonLimpar.setOnClickListener(v -> {
            pontuacaoDao.limparRanking();
            carregarRanking();
        });
    }

    private void carregarRanking() {
        List<Pontuacao> lista = pontuacaoDao.buscarRanking();
        adapter = new PontuacaoAdapter(lista);
        recyclerViewRanking.setAdapter(adapter);
    }
}
