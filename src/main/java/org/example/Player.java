package org.example;

import java.awt.*;

public class Player extends GameObject {

    public Player(int x, int y) {
        //Width and height final in GameObject
        super(x, y, 30, 30);
    }

    @Override
    public void draw(Graphics graphics) {
        // Player color green
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void update() {

    }

    public void move(int mx, int my) {
        this.x += mx;
        this.y += my;
    }
}