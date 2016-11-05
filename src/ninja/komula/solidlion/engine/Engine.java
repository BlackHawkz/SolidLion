package ninja.komula.solidlion.engine;
import ninja.komula.solidlion.gfx.Renderer;
import ninja.komula.solidlion.gfx.Window;

/**
 * Created by luke on 11/3/2016.
 */
public class Engine implements Runnable {
    private boolean isRunning = true;
    private Renderer renderer = null;
    private IEngine game;
    private Window gameWindow;
    private Thread thread;
    private boolean debugFPS = false;
    private int frames=0, updates=0;

    /**
     * The start method provides the initial setup for the engine, automatically creates a new window and initializes the renderer.
     * @param game A reference to any class that extends IEngine
     * @param width Width of the desired window
     * @param height Height of the desired window
     */

    public void start(IEngine game, int width, int height){
        renderer = new Renderer(width,height);
        this.game = game;
        gameWindow = new Window();
        gameWindow.create_window(width,height);
        renderer.setDefaultWindow(gameWindow);
        thread = new Thread(this);
        thread.start();
    }

    private void run(IEngine game){

        double previousMili = System.currentTimeMillis();
        double previous = System.nanoTime();
        double lag = 0.0;
        double NS_PER_UPDATE = 1000000000.0/60.0;
        while(isRunning){
            double current = System.nanoTime();
            double elapsed = current-previous;
            previous = current;
            lag += elapsed;
            while (lag >= NS_PER_UPDATE){
                game.update();
                lag -= NS_PER_UPDATE;
                updates++;
            }

            renderer.render();
            frames++;

            if(System.currentTimeMillis()-previousMili >=1000){
                if(debugFPS) {
                    System.out.println(String.format("FPS: %d Updates: %d", frames, updates));
                }
                previousMili = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }
        }
    }

    public boolean getDebugFPS(){
        return this.debugFPS;
    }
    public void setDebugFPS(boolean enabled){
        this.debugFPS = enabled;
    }
    public Window getGameWindow(){
        return gameWindow;
    }

    @Override
    public void run() {
        run(this.game);
    }
}
