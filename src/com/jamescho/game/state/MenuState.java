package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

public class MenuState extends State {
    private int currentSelection = 0;
    private boolean hasChanged = false;

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

        // Dibuja Resources.welcome en la pantalla en la posición x= 0, y = 0
        g.drawImage(Resources.welcome, 0, 0, null);
        if(currentSelection == 0) {
            g.drawImage(Resources.selector, 335, 241, null);
        }else {
            g.drawImage(Resources.selector, 335, 291, null);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        System.out.println("onClick() " + getClass().getCanonicalName());
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        System.out.println("onKeyPress() " + getClass().getCanonicalName());

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
            if(currentSelection == 0) {
                setCurrentState(new PlayState());
            }else if(currentSelection == 1) {
                GameMain.sGame.exit();
            }
        }else if(key == KeyEvent.VK_UP) {
            currentSelection = 0;
        }else if(key == KeyEvent.VK_DOWN) {
            currentSelection = 1;
        }else if(key == KeyEvent.VK_ESCAPE) {
            GameMain.sGame.exit();
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        System.out.println("onKeyRelease() " + getClass().getCanonicalName());
    }
}
