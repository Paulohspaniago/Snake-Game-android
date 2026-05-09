package com.example.projetofinalpdm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class RankingActivity extends AppCompatActivity {

    private RecyclerView recyclerRanking;
    private PontuacaoDao pontuacaoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        recyclerRanking = findViewById(R.id.recyclerRanking);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "snake-db")
                .allowMainThreadQueries()
                .build();

        pontuacaoDao = db.pontuacaoDao();

        List<Pontuacao> listaPontuacoes = pontuacaoDao.buscarRanking();

        recyclerRanking.setLayoutManager(new LinearLayoutManager(this));
        recyclerRanking.setAdapter(new PontuacaoAdapter(listaPontuacoes));
    }
}
