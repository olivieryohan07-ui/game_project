package org.example;

import java.awt.*;

public abstract class GameObject {

    // Protected -- visible by children classes only
    protected int x;
    protected int y;
    protected final int WIDTH;
    protected final int HEIGHT;

    //Constructor
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public abstract void draw(Graphics graphics);

    public abstract void update();

    // Build an invisible rectangle around the object.
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}