package ninja.komula.solidlion.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by luke on 11/7/2016.
 */
public class Keyboard implements KeyListener {

    private int keyCount = 256;

    public enum KeyState{
        RELEASED,
        PRESSED,
        ONCE
    }

    private boolean[] currentKey;
    private KeyState[] keys;

    public Keyboard(){
        currentKey = new boolean[keyCount];
        keys = new KeyState[keyCount];

        for(int i=0; i < keys.length; i ++){
            keys[i] = KeyState.RELEASED;
        }
    }

    /**
     * Setup the key arrays so that the keyboard class can detect key events. Typically placed in the game loop to update the input
     */

    public synchronized void poll(){
        for(int i=0; i < keys.length; i++){
            if(currentKey[i]){
                if( keys[ i ] == KeyState.RELEASED )

                    keys[ i ] = KeyState.ONCE;

                else

                    keys[ i ] = KeyState.PRESSED;
            }else{
                keys[i] = KeyState.RELEASED;
            }
        }
    }

    /**
     * keyDown allows continuous detection of the key being pressed, it's main use is for keyboard input that requires multiple key events.
     * @param keyCode The integer representing which key is being pressed, used in conjunction with {@link KeyEvent}
     * @return
     */
    public synchronized boolean keyDown(int keyCode){
        return keys[keyCode] == KeyState.ONCE || keys[keyCode] == KeyState.PRESSED;
    }

    /**
     * keyDownOnce allows singular detection of the key being pressed, it's main use is for keyboard input that requires one key event.
     * @param keyCode The integer representing which key is being pressed, used in conjunction with {@link KeyEvent}
     * @return
     */
    public synchronized boolean keyDownOnce(int keyCode){
        return keys[keyCode] == KeyState.ONCE;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode > 0 && keyCode < keyCount){
            currentKey[keyCode] = true;
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode > 0 && keyCode < keyCount){
            currentKey[keyCode] = false;
        }
    }
}
