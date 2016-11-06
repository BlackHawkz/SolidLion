package ninja.komula.solidlion.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by luke on 11/4/2016.
 */
public class Renderer {

    private int width = 0;
    private int height = 0;
    private int[] pixels;
    private BufferedImage image;
    private Random rand = new Random();
    private Window window;
    private  GraphicsEnvironment env;
    private GraphicsDevice device;
    private GraphicsConfiguration config ;

    public Renderer(Window gameWindow, int width, int height){
        this.window = gameWindow;
        setHeight(window.getHeight());
        setWidth(window.getWidth());

        env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = env.getDefaultScreenDevice();
        config = device.getDefaultConfiguration();

        image = config.createCompatibleImage(getWidth(),getHeight());
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        System.out.println("asdfasdfasd: "+pixels.length);

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * setDefaultWindow provides a way to pass a {@link ninja.komula.solidlion.gfx.Window} to the renderer
     * @param window A {@link ninja.komula.solidlion.gfx.Window} object
     */

    public void setDefaultWindow(Window window){
        this.window = window;
        window.getGraphics().translate(3, 22);
        setHeight(window.getHeight());
        setWidth(window.getWidth());
    }
    public Window getDefaultWindow(){
        return this.window;
    }

    public void setDeviceFullscreen(boolean enabled){
        if(enabled) {
            device.setFullScreenWindow(window.getFrame());
            setWidth(device.getFullScreenWindow().getWidth());
            setHeight(device.getFullScreenWindow().getHeight());
            image = device.getFullScreenWindow().getGraphicsConfiguration().createCompatibleImage(getWidth(),getHeight());
            pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        }else{
            device.setFullScreenWindow(null);
            setWidth(window.getWidth());
            setHeight(window.getHeight());
            image = config.createCompatibleImage(getWidth(),getHeight());
            pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        }
        System.out.println(pixels.length);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GraphicsEnvironment getEnv() {
        return env;
    }

    public GraphicsDevice getDevice() {
        return device;
    }

    public GraphicsConfiguration getConfig() {
        return config;
    }

    public int[] getPixels(){
        return pixels;
    }

    /**
     * Doesn't do anything,  deprecated.
     * @param p
     */

    public void setPixels(int[] p){
        for(int i=0; i< p.length; i++){
            pixels[i] = p[i];
        }
    }
    public void clearScreen(){
        for(int i=0; i<pixels.length;i++){
            pixels[i] = 0;
        }
    }

    public void render(){

        window.getGraphics().drawImage(image,3,26,getWidth(),getHeight(),null);
    }
}
