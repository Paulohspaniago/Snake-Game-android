package com.example.projetofinalpdm;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetofinalpdm.view.GameView;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private GestureDetector gestureDetector;
    private TextView tvScore;
    private ImageButton btnPause;
    private boolean isPaused = false;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = findViewById(R.id.gameView);
        tvScore = findViewById(R.id.tvScore);
        btnPause = findViewById(R.id.btnPause);

        // Música
        mediaPlayer = MediaPlayer.create(this, R.raw.arcadesong);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        btnPause.setOnClickListener(v -> {
            isPaused = !isPaused;
            gameView.setPaused(isPaused);
            btnPause.setImageResource(
                    isPaused ? android.R.drawable.ic_media_play : android.R.drawable.ic_media_pause
            );
        });

        gameView.setOnScoreChangeListener(score ->
                runOnUiThread(() -> tvScore.setText("Score: " + score))
        );

        gameView.setOnGameOverListener(score -> {
            pararMusica();
            Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
            intent.putExtra("pontuacao", score);
            startActivity(intent);
            finish();
        });

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            private final int SWIPE_THRESHOLD = 100;

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                                   float velocityX, float velocityY) {
                float dx = e2.getX() - e1.getX();
                float dy = e2.getY() - e1.getY();

                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > SWIPE_THRESHOLD) {
                        gameView.setDirection("RIGHT");
                    } else if (dx < -SWIPE_THRESHOLD) {
                        gameView.setDirection("LEFT");
                    }
                } else {
                    if (dy > SWIPE_THRESHOLD) {
                        gameView.setDirection("DOWN");
                    } else if (dy < -SWIPE_THRESHOLD) {
                        gameView.setDirection("UP");
                    }
                }
                return true;
            }
        });
    }

    private void pararMusica() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pararMusica();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}
