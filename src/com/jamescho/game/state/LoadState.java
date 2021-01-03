package com.jamescho.game.state;

import com.jamescho.game.main.Resources;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LoadState extends State {

    @Override
    public void init() {
        Resources.load();
        System.out.println("Loaded Successfully!");
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void onClick(MouseEvent e) {

    }

    public void onKeyPress(KeyEvent e) {
        super.onKeyPress(e);
    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
}
