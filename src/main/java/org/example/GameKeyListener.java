package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    private Player player;
    // The distance of each movement
    private final int STEP = 20;

    private Game game;

    public GameKeyListener(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Get the key pressed code
        int code = e.getKeyCode();
        // Game has not started
        if (!game.isHasStarted()) {
            if (code == KeyEvent.VK_SPACE) {
                game.setHasStarted(true);
            }
            return;
        }
        // Game over
        if (game.isGameOver()) {
            if (code == KeyEvent.VK_R) {
                game.resetGame();
            }
            return;
        }
        if (code == KeyEvent.VK_UP) {
            player.move(0, -STEP); // Remove from y to go up
        } else if (code == KeyEvent.VK_DOWN) {
            player.move(0, STEP); // Add to Y to go down
        } else if (code == KeyEvent.VK_RIGHT) {
            player.move(STEP, 0);  //Add to X to go left
        } else if (code == KeyEvent.VK_LEFT) {
            player.move(-STEP, 0); //Remove from to X to go right
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}