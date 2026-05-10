package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Crazy Road");
        Game game = new Game();

        window.add(game);
        // Adjusts the window to the size of the JPanel (500x600)
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Center the window ont the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        game.startGame();
    }
}