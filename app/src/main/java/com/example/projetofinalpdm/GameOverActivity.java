package com.example.projetofinalpdm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;

public class GameOverActivity extends AppCompatActivity {

    private TextView tvPontuacaoFinal;
    private EditText etNomeJogador;
    private Button btnSalvar;
    private int pontuacaoFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        tvPontuacaoFinal = findViewById(R.id.tvPontuacaoFinal);
        etNomeJogador = findViewById(R.id.etNomeJogador);
        btnSalvar = findViewById(R.id.btnSalvarPontuacao);

        pontuacaoFinal = getIntent().getIntExtra("pontuacao", 0);
        tvPontuacaoFinal.setText("Pontuação: " + pontuacaoFinal);

        btnSalvar.setOnClickListener(v -> {
            String nome = etNomeJogador.getText().toString().trim();

            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show();
                return;
            }

            Pontuacao pontuacao = new Pontuacao(nome, pontuacaoFinal);
            PontuacaoDao dao = AppDatabase.getInstance(this).pontuacaoDao();

            Executors.newSingleThreadExecutor().execute(() -> {
                dao.inserir(pontuacao);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Pontuação salva!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                });
            });
        });
    }
}
