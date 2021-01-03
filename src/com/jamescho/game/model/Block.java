package com.jamescho.game.model;

import com.jamescho.framework.util.RandomNumberGenerator;

import java.awt.Rectangle;

public class Block {
    private float x;
    private float y;
    private int width;
    private int height;
    private Rectangle rect;
    private boolean visible;

    private static final int UPPER_Y = 275;
    private static final int LOWER_Y = 355;

    public Block(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle((int)x, (int)y, width, height);
        visible = false;
    }

    // El valor de la velocidad vendrá desde PlayState
    public void update(float delta, float velX) {
        x += velX*delta;
        if(x <= -50) {
            reset();
        }
        updateRect();
    }

    private void updateRect() {
        rect.setBounds((int)x, (int)y, width, height);
    }

    private void reset() {
        visible = true;

        // Una probabilidad de 1 entre 3 de ser un bloque alto
        if(RandomNumberGenerator.getRandInt(3) == 0) {
            y = UPPER_Y;
        }else {
            y = LOWER_Y;
        }

        x += 1000;
    }

    public void onCollide(Player p) {
        visible = false;
        p.pushBack(30);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isVisible() {
        return visible;
    }
}
