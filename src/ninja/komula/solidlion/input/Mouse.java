package ninja.komula.solidlion.input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by luke on 11/7/2016.
 */
public class Mouse implements MouseListener, MouseMotionListener {

    private int BUTTON_COUNT = 3;
    private Point mousePos;
    private Point currentPos;
    private boolean[] state;
    private MouseState[] poll;

    public enum MouseState{
        RELEASED,
        PRESSED,
        ONCE
    }

    public Mouse(){
        mousePos = new Point(0,0);
        currentPos = new Point(0,0);
        state = new boolean[BUTTON_COUNT];
        poll = new MouseState[BUTTON_COUNT];

        for(int i=0; i < BUTTON_COUNT; i ++){
            poll[i] = MouseState.RELEASED;
        }
    }
    public synchronized void poll(){
        mousePos = currentPos ;

        for( int i = 0; i < BUTTON_COUNT; ++i ) {

            if( state[ i ] ) {

                if( poll[ i ] == MouseState.RELEASED )

                    poll[ i ] = MouseState.ONCE;

                else

                    poll[ i ] = MouseState.PRESSED;

            } else {


                poll[ i ] = MouseState.RELEASED;

            }

        }
    }

    public Point getPosition() {

        return mousePos;

    }



    public boolean buttonDownOnce( int button ) {

        return poll[ button-1 ] == MouseState.ONCE;

    }



    public boolean buttonDown( int button ) {

        return poll[ button-1 ] == MouseState.ONCE ||

                poll[ button-1 ] == MouseState.PRESSED;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        state[ e.getButton()-1 ] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        state[ e.getButton()-1 ] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentPos = e.getPoint();
    }
}
