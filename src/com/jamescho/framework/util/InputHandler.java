package com.jamescho.framework.util;

import com.jamescho.game.state.State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandler implements KeyListener, MouseListener{

    private State currentState;

    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    /**
     * Invoked when a key has been typed. See the class description for {@link KeyEvent} for a definition of a key typed
     * event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e){

    }

    /**
     * Invoked when a key has been pressed. See the class description for {@link KeyEvent} for a definition of a key
     * pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e){
        currentState.onKeyPress(e);
    }

    /**
     * Invoked when a key has been released. See the class description for {@link KeyEvent} for a definition of a key
     * released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e){
        currentState.onKeyRelease(e);
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e){
        currentState.onClick(e);
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e){

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e){

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e){

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e){

    }
}
