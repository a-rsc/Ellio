package com.jamescho.game.main;

import javax.swing.JFrame;

public class GameMain {
    private static final String GAME_TITLE = "Ellio (Chapter 6) by Álvaro Rodríguez Santa Cruz";
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;
    // https://www.ecodeup.com/metodos-variables-y-bloques-static-en-java-con-ejemplos/
    // Una variable estática (static) en Java es una variable que pertenece a la clase en que fue declarada y se inicializa solo una vez al inicio de la ejecución del programa, la característica principal de este tipo de variables es que se puede acceder directamente con el nombre de la clase sin necesidad de crear un objeto.
    public static Game sGame, s2Game;

    public static void main(String[] args) {
        JFrame frame = new JFrame(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Evita el redimensionado manual de la ventana
        sGame = new Game(GAME_WIDTH, GAME_HEIGHT);
        frame.add(sGame);
        frame.pack();
        frame.setVisible(true);
        frame.setIconImage(Resources.iconimage);
    }
}
