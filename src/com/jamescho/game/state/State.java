package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;

public abstract class State {

    /*
        El método init() se invocará cuando el juego cambie de estado. Es un magnífico lugar en el que se inicializa
        cualquier objeto del juego que se utilice durante el mismo estado.
    */
    public abstract void init();

    /*
        El bucle del juego invocará el método update() del estado acutal en cada frame (fotograma). Lo utilizaremos
        para actualizar todos los objetos de cada juego en el interior del mismo estado.
     */
    // public abstract void update();
    public abstract void update(float delta);

    /*
        El método render() del estado actual será llamado por el bucle del juego en cada frame. Lo utilizaremos para
        mostrar las imágenes del juego en la pantalla.
    */
    public abstract void render(Graphics g);

    /*
        El método onClick() del estado actual se invocará cuando el jugador haga clic con el ratón.
    */
    public abstract void onClick(MouseEvent e);

    /*
        El método onKeyRelease() del estado actual se invocará cuando el jugador suelte la tecla que tenga pulsada en el teclado.
        Recibirá información acerca del evento de liberación de la tecla, tal como la identidad de la tecla liberada.
        Utilizaremos este método para hacer cambios en nuestro juego, por ejemplo detener el movimiento de un personaje.
    */
    public abstract void onKeyRelease(KeyEvent e);

    /*
        El método onKeyPress() del estado actual será invocado cuando el jugador pulse una tecla de su teclado.
        Recibirá información sobre el evento de pulsación de la tecla, tal como la identidad de la tecla pulsada. Utilizaremos este método para hacer cambios en nuestro juego, por ejemplo mover el personaje.
    */
    public void onKeyPress(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            GameMain.sGame.exit();
        }
    }

    /*
        El método setCurrentState() acepta un objeto State de destino y lo pasa al método setCurrentState() de nuestro objeto Game, que lo almacena
        en la variable sGame de la clase GameMain.java. Podemos invocar este método desde cualquier clase de estado, por ejemplo LoadState, siempre
        que deseemos realizar la transición a un nuevo estado.
     */
    public void setCurrentState(State newState) {
        GameMain.sGame.setCurrentState(newState);
    }
}
