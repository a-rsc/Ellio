package com.jamescho.game.model;

import com.jamescho.framework.util.RandomNumberGenerator;
import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

public class Cloud {
    private float x;
    private float y;

    private static final int VEL_X = -15;
    private static final int CLOUD_RESTART = 200;

    public Cloud(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float delta) {
        x += VEL_X*delta;

        if(x <= -CLOUD_RESTART) {
            // Reiniciar a la derecha
            x += GameMain.GAME_WIDTH + CLOUD_RESTART;
            y = RandomNumberGenerator.getRandIntBetween(Resources.cloud1.getHeight() / 2, 100);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
