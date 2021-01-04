package com.jamescho.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;
import com.sun.istack.internal.NotNull;

public class GameOverState extends State {
    private String playerScore;
    private Font font = new Font("SansSerif", Font.BOLD, 50);

    public GameOverState(int playerScore) {
        this.playerScore = Integer.toString(playerScore);
    }

    @Override
    public void init() {
        System.out.println("init() " + getClass().getCanonicalName());
    }

    @Override
    public void update(float delta) {
        System.out.println("update() " + getClass().getCanonicalName());
    }

    @Override
    public void render(Graphics g) {
        System.out.println("render() " + getClass().getCanonicalName());

        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        g.setColor(Color.DARK_GRAY);
        g.setFont(font);
        g.drawString("GAME OVER", 257, 175);
        g.drawString(playerScore, 385, 250);
        g.drawString("Press any key", 240, 350);
    }

    @Override
    public void onClick(MouseEvent e) {
        System.out.println("onClick() " + getClass().getCanonicalName());
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        System.out.println("onKeyPress() " + getClass().getCanonicalName());
        setCurrentState(new MenuState());
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        System.out.println("onKeyRelease() " + getClass().getCanonicalName());
    }
}
