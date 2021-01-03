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
        // Cuando el personaje colisiona con un bloque, debe retroceder 30 píxeles. Esto se gestionará desde el
        // método pushBack() del objeto Player. Cuando esto sucede, el bloque que ha empujado al personaje no debería
        // poder volver a colisionar con él. Hay muchas formas de implementar este comportamiento. Podríamos adoptar
        // el enfoque tradicional de los juegos clásicos de plataformas: hacer que el personaje parpadee y volverlo
        // inmune a las colisiones durante un tiempo breve. Sin embargo, el juego gestiona esto volviendo invisible
        // el bloque para que no pueda dañar al personaje en repetidas ocasiones.
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
