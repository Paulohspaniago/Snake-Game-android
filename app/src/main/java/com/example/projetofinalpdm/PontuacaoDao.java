package com.example.projetofinalpdm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PontuacaoDao {

    @Insert
    void inserir(Pontuacao p);

    @Query("SELECT * FROM pontuacoes ORDER BY pontuacao DESC LIMIT 10")
    List<Pontuacao> buscarRanking();

    @Query("DELETE FROM pontuacoes")
    void limparRanking();

}
