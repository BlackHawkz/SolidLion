package ninja.komula.solidlion.gfx;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by luke on 11/3/2016.
 */
public class Window implements IWindow {

    private int height,width;
    private String title;
    private Frame frame;


    /**
     * Creates and sets the basic properties of a window. Uses {@link Frame} as the base class.
     * @param width The desired width of the window.
     * @param height The desired height of the window.
     */

    @Override
    public void create_window(int width, int height) {
        frame  = new Frame();
        setWidth(width);
        setHeight(height); //g.translate(0,22) needs to be accounted for
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(getWidth(),getHeight()));

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setTitle("Title");
        frame.toFront();
        frame.setVisible(true);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        frame.setTitle(title);
    }

    public Graphics getGraphics(){
        Graphics g = frame.getGraphics();

        return g;
    }

    public Frame getFrame(){
        return frame;
    }
}
