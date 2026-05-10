package org.example;

import java.awt.*;

public class Car extends GameObject {
    private int speed;
    private Color color;
    private final int SCREEN_WIDTH;

    public Car(int x, int y, int width, int height, int speed, Color color, int screenWidth) {
        super(x, y, width, height);
        this.speed = speed;
        this.color = color;
        this.SCREEN_WIDTH = screenWidth;
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void update() {
        // Automatic movement: the speed is added to the X position
        this.x += speed;
        //The car disappears from the screen and reappears on the left.
        if (speed > 0 && x > SCREEN_WIDTH) {
            x = -WIDTH;
        }
        //The car disappears from the screen and reappears on the right.
        else if (speed < 0 && x < -WIDTH) {
            x = SCREEN_WIDTH;
        }
    }
}