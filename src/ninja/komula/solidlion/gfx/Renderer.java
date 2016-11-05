package ninja.komula.solidlion.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

/**
 * Created by luke on 11/4/2016.
 */
public class Renderer {

    private int width = 0;
    private int height = 0;
    private int[] pixels;
    private BufferedImage image;
    private Random rand = new Random();

    public Renderer(int width, int height){
        setHeight(height);
        setWidth(width);
        pixels = new int[width*height];

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        image = config.createCompatibleImage(getWidth(),getHeight());
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();


    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void render(){
        for(int i=0; i < pixels.length; i++){
            pixels[i] = rand.nextInt(0xFFFFFF);
        }
    }
}