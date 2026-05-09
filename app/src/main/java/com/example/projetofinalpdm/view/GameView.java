package com.example.projetofinalpdm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends View {
    private final int numCells = 20; // número de células por linha/coluna
    private int cellSize;            // tamanho de cada célula em pixels
    private final List<Point> snake = new ArrayList<>();
    private Point food;
    private String direction = "RIGHT";
    private final Handler handler = new Handler();
    private final int delay = 300;  // tempo entre atualizações (ms)
    private boolean isGameOver = false;
    private boolean isPaused = false;

    private final Paint paintSnake = new Paint();
    private final Paint paintFood = new Paint();

    private OnGameOverListener gameOverListener;
    private OnScoreChangeListener scoreChangeListener;

    private int score = 0;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintSnake.setColor(Color.GREEN);
        paintFood.setColor(Color.RED);

        resetGame();
        startGameLoop();
    }

    private void resetGame() {
        snake.clear();
        snake.add(new Point(5, 10));
        snake.add(new Point(4, 10));
        snake.add(new Point(3, 10));
        generateFood();
        direction = "RIGHT";
        score = 0;
        isGameOver = false;
    }

    private void generateFood() {
        Random r = new Random();
        food = new Point(r.nextInt(numCells), r.nextInt(numCells));
    }

    private void startGameLoop() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isGameOver && !isPaused) {
                    moveSnake();
                    invalidate(); // redesenha
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cellSize = getWidth() / numCells;

        // desenha comida
        canvas.drawRect(
                food.x * cellSize,
                food.y * cellSize,
                (food.x + 1) * cellSize,
                (food.y + 1) * cellSize,
                paintFood
        );

        // desenha a cobra
        for (Point p : snake) {
            canvas.drawRect(
                    p.x * cellSize,
                    p.y * cellSize,
                    (p.x + 1) * cellSize,
                    (p.y + 1) * cellSize,
                    paintSnake
            );
        }
    }

    private void moveSnake() {
        Point head = new Point(snake.get(0));
        switch (direction) {
            case "UP": head.y--; break;
            case "DOWN": head.y++; break;
            case "LEFT": head.x--; break;
            case "RIGHT": head.x++; break;
        }

        // colisão com parede
        if (head.x < 0 || head.y < 0 || head.x >= numCells || head.y >= numCells) {
            isGameOver = true;
            if (gameOverListener != null) gameOverListener.onGameOver(score);
            return;
        }

        // colisão com a própria cobra
        for (Point part : snake) {
            if (head.equals(part)) {
                isGameOver = true;
                if (gameOverListener != null) gameOverListener.onGameOver(score);
                return;
            }
        }

        // come a comida
        if (head.equals(food)) {
            snake.add(0, head); // cresce
            score++;
            generateFood();
            if (scoreChangeListener != null) scoreChangeListener.onScoreChanged(score);
        } else {
            snake.add(0, head);             // move
            snake.remove(snake.size() - 1); // apaga o último
        }
    }

    public void setDirection(String newDirection) {
        // impede reversão direta (tipo direita ↔ esquerda)
        if ((direction.equals("LEFT") && newDirection.equals("RIGHT")) ||
                (direction.equals("RIGHT") && newDirection.equals("LEFT")) ||
                (direction.equals("UP") && newDirection.equals("DOWN")) ||
                (direction.equals("DOWN") && newDirection.equals("UP"))) {
            return;
        }
        direction = newDirection;
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    public void setOnGameOverListener(OnGameOverListener listener) {
        this.gameOverListener = listener;
    }

    public void setOnScoreChangeListener(OnScoreChangeListener listener) {
        this.scoreChangeListener = listener;
    }

    // Interfaces para callbacks
    public interface OnGameOverListener {
        void onGameOver(int score);
    }

    public interface OnScoreChangeListener {
        void onScoreChanged(int newScore);
    }
}
