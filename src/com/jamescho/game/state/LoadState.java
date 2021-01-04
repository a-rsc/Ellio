package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

public class LoadState extends State {

    @Override
    public void init() {
        System.out.println("init() " + getClass().getCanonicalName());
        Resources.load();
    }

    @Override
    public void update(float delta) {
        System.out.println("update() " + getClass().getCanonicalName());
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Graphics g) {
        System.out.println("render() " + getClass().getCanonicalName());
    }

    @Override
    public void onClick(MouseEvent e) {
        System.out.println("onClick() " + getClass().getCanonicalName());
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        System.out.println("onKeyPress() " + getClass().getCanonicalName());

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ESCAPE) {
            GameMain.sGame.exit();
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        System.out.println("onKeyRelease() " + getClass().getCanonicalName());
    }
}
