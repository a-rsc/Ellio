package com.jamescho.game.model;

import java.awt.Rectangle;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

public class Player {
    private float x;
    private float y;
    private int width;
    private int height;
    private int velY;
    private Rectangle rect;
    private Rectangle duckRect;
    private Rectangle ground;

    private boolean isAlive;
    private boolean isDucked;
    private float duckDuration = .6f;

    private static final int JUMP_VELOCITY = -600;
    private static final int ACCEL_GRAVITY = 1800;

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        ground = new Rectangle(0, GameMain.GAME_HEIGHT - Resources.grass.getHeight(), GameMain.GAME_WIDTH,
                Resources.grass.getHeight());
        rect = new Rectangle();
        duckRect = new Rectangle();
        isAlive = true;
        isDucked = false;
        updateRects();
    }

    public void update(float delta) {
        if(duckDuration > 0 && isDucked) {
            duckDuration -= delta;
        }else {
            isDucked = false;
            duckDuration = .6f;
        }

        if(!isGrounded()) {
            velY += ACCEL_GRAVITY * delta;
        }else {
            y = 406 - height;
            velY = 0;
        }
        y += velY * delta;
        updateRects();
    }

    public void updateRects() {
        rect.setBounds((int)x+10, (int)y, width - 20, height);
        duckRect.setBounds((int)x, (int)y+20, width, height - 20);
    }

    public void jump() {
        if(isGrounded()) {
            Resources.onjump.play();
            isDucked = false;
            duckDuration = .6f;
            y -= 10;
            velY = JUMP_VELOCITY;
            updateRects();
        }
    }

    public void duck() {
        if(isGrounded()) {
            isDucked = true;
        }
    }

    public void pushBack(int dX) {
        Resources.hit.play();
        x -= dX;
        if(x < -width / 2) {
            isAlive = false;
        }
        rect.setBounds((int)x, (int)y, width, height);
    }

    public boolean isGrounded() {
        return rect.intersects(ground);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getVelY() {
        return velY;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Rectangle getDuckRect() {
        return duckRect;
    }

    public Rectangle getGround() {
        return ground;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isDucked() {
        return isDucked;
    }

    public float getDuckDuration() {
        return duckDuration;
    }
}
