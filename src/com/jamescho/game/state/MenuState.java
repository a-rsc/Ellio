package com.jamescho.game.state;

import com.jamescho.game.main.Resources;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuState extends State {
    @Override
    public void init() {
        System.out.println("Entered MenuState");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Graphics g) {
        // Dibuja Resources.welcome en la pantalla en la posición x= 0, y = 0
        g.drawImage(Resources.welcome, 0, 0, null);
    }

    @Override
    public void onClick(MouseEvent e) {
        System.out.println("On click!");
    }

    public void onKeyPress(KeyEvent e) {
        super.onKeyPress(e);
        System.out.println("On keyPress!");
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        System.out.println("On keyRelease!");
    }
}
