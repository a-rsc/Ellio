package com.jamescho.game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

import com.jamescho.framework.util.InputHandler;
import com.jamescho.game.state.*;

@SuppressWarnings("serial")

public class Game extends JPanel implements Runnable {
    private int gameWidth;
    private int gameHeight;
    private Image gameImage;

    private Thread gameThread;

    // https://medium.com/@pablocastelnovo/variables-vol%C3%A1tiles-en-java-f5ae078bf8b9
    private volatile boolean running;
    private volatile State currentState;

    private InputHandler inputHandler;

    public Game(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.BLACK);

/*
        Permiten comenzar a recibir las entradas del usuario en forma de eventos de teclado y ratón. Lo primero que hacemos es indicar que  nuestro juego pueda recibir el enfoque y, luego, pedimos dicho enfoque. Esto solo quiere decir que los eventos del teclado y el ratón estarán ahora disponibles para nuestro objeto Game.
*/

        setFocusable(true);
        requestFocus();
    }

    public void setCurrentState(State newState) {

/*
        El método System.gc() se invoca para limpiar cualquier objeto sin utilizar que quede en memoria. Las letras gc significan garbage collector (o recogedor de basura, aunque es habitual usarlo en inglés en entornos técnicos). Hablaremos más sobre su importancia a lo largo del libro.
*/
//        System.gc(); // Warning: Don't try to be smarter than the JVM, remove this call to run the garbage collector.
        newState.init();
        currentState = newState;
        inputHandler.setCurrentState(currentState);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        initInput();
        setCurrentState(new LoadState());
        initGame();
    }

    private void initInput() {
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);
    }

    private void initGame() {
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    @Override
    public void run() {
        long updateDurationMillis = 0;  // Mide tanto las actualizaciones como los renderizados
        long sleepDurationMillis = 0;           // Mide la latencia

        while (running) {
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = updateDurationMillis + sleepDurationMillis;

            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;
            sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);

            try {
                Thread.sleep(sleepDurationMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0); // El juego finaliza inmediatamente después de que la condición del bucle se hace false
    }

    private void updateAndRender(long deltaMillis) {
        currentState.update(deltaMillis / 1000f);
        prepareGameImage();
        currentState.render(gameImage.getGraphics());
        renderGameImage(getGraphics());
    }


    private void prepareGameImage() {
        if (gameImage == null) {
            gameImage = createImage(gameWidth, gameHeight);
        }
        Graphics g = gameImage.getGraphics();
        g.fillRect(0, 0, gameWidth, gameHeight);
    }

    public void exit() {
        running = false;
    }

    protected void renderGameImage(Graphics g) {
        if (gameImage != null) {
            g.drawImage(gameImage, 0, 0, null);
        }
        g.dispose();
    }
}
