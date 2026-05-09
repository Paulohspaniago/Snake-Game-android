package com.example.projetofinalpdm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Pontuacao.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PontuacaoDao pontuacaoDao();

    // Singleton para evitar múltiplas instâncias
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "pontuacoes_db"
                    ).allowMainThreadQueries() // use apenas em apps pequenos/simples
                    .build();
        }
        return INSTANCE;
    }
}
