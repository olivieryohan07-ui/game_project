package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel implements Runnable {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 600;

    private Thread gameThread;
    private boolean isRunning;
    private Player player;
    private Car[] obstacles;

    private int level = 1;
    private int bestScore = 1;
    private boolean isGameOver = false;
    private boolean hasStarted = false;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);

        // Player Initialization
        player = new Player((WIDTH / 2) - 15, HEIGHT - 60);
        //Game Initialization
        initLevel();
        //Create a gameKeyListener to allow control of the player
        GameKeyListener gameKeyListener = new GameKeyListener(player, this);
        //Add the control to the panel
        this.addKeyListener(gameKeyListener);
        // Focus on this window for the keyListener
        this.setFocusable(true);
    }

    public void startGame() {
        isRunning = true;
        this.requestFocusInWindow();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            update();
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (!hasStarted || isGameOver) {
            return;
        }
        for (int i = 0; i < obstacles.length; i++) {
            if (obstacles[i] != null) {
                obstacles[i].update();

                //Collision test
                if (player.getBounds().intersects(obstacles[i].getBounds())) {
                    System.out.println("GAME OVER");
                    isGameOver = true;
                    if (level > bestScore) {
                        bestScore = level;
                    }
                    resetPlayer();
                }
            }
        }
        int winLine = 50;
        if (player.getY() <= winLine) {
            level += 1;
            resetPlayer();
            initLevel();
        }
    }

    private void resetPlayer() {
        player.setX((WIDTH / 2) - 15);
        player.setY(HEIGHT - 60);
    }

    public void resetGame() {
        this.level = 1;
        this.isGameOver = false;
        resetPlayer();
        initLevel();
    }

    private void initLevel() {
        int numOfRows = 8;
        obstacles = new Car[numOfRows];
        Color[] stylishColors = {
                new Color(255, 0, 110),
                new Color(58, 134, 255),
                new Color(131, 56, 236),
                new Color(251, 86, 7),
                new Color(67, 235, 27)
        };
        for (int i = 0; i < obstacles.length; i++) {
            int yPosition = 100 + (i * 55);
            int currentSpeed = 2 + level;

            Random random = new Random();
            Color randomColor = stylishColors[random.nextInt(0, stylishColors.length)];

            int direction;
            int startX;

            if (i % 2 == 0) {
                direction = currentSpeed;
                startX = (i * 120) + random.nextInt(0, 100);
            } else {
                direction = -currentSpeed;
                startX = (i * 120) + random.nextInt(0, 100);
            }
            obstacles[i] = new Car(startX, yPosition, 60, 40, direction, randomColor, WIDTH);

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Clears the previous screen
        super.paintComponent(g);

        if (!hasStarted) {
            initMenu(g);
        } else {
            //Paint a finish line
            g.setColor(Color.WHITE);
            g.fillRect(0, 50, WIDTH, 10);
            //Separation line
            g.setColor(Color.GRAY);
            for (int i = 0; i < obstacles.length; i++) {
                int yLine = 100 + (i * 55) - 5;
                g.drawLine(0, yLine, WIDTH, yLine);
            }
            //Draw player
            player.draw(g);
            //Draw cars
            for (int i = 0; i < obstacles.length; i++) {
                if (obstacles[i] != null) {
                    obstacles[i].draw(g);
                }
            }
            //Score
            g.setColor(Color.WHITE);
            g.drawString("Level: " + level, 20, 30);
            g.drawString("Best score: " + bestScore, 380, 30);

            if (isGameOver) {
                g.setColor(Color.RED);
                g.setFont(new Font("arial", Font.BOLD, 40));
                g.drawString("GAME OVER", 140, 300);
                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.setColor(Color.WHITE);
                g.drawString("PRESS 'R' TO PLAY AGAIN", 130, 350);
            }
        }
    }

    private void initMenu(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("CRAZY ROAD", 150, 100);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("SPACE TO START", 180, 150);
        g.drawString("Game Rules:",50, 200);
        g.drawString("Guide your character from the bottom of the screen to the safety",50, 230);
        g.drawString("zone at the top without getting hit by the oncoming traffic",50, 250);
        g.drawString("Controls: ",50, 280);
        g.drawString("SPACE: Start the game from the main menu.",50, 310);
        g.drawString("ARROW KEYS: Move your character Up, Down, Left, or Right.",50, 330);
        g.drawString("'R' KEY: Restart the game instantly after a Game Over.",50, 350);
        g.drawString("Dynamic Difficulty: Car speed increases after each level.",50, 380);
        g.drawString("Live Scoring: Real-time level tracking.",50, 400);
        g.drawString("High Score: Best Score saved to encourage replayability.",50, 420);
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}