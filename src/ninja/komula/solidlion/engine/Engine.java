package ninja.komula.solidlion.engine;
import ninja.komula.solidlion.gfx.Renderer;
import ninja.komula.solidlion.gfx.Window;

/**
 * Created by luke on 11/3/2016.
 */
public class Engine {
    private boolean isRunning = true;
    private Renderer renderer = null;
    private Window gameWindow;



    public void start(IEngine game, int width, int height){
        renderer = new Renderer(width,height);
        gameWindow = new Window();
        gameWindow.create_window(width,height);
        run(game);
    }

    private void run(IEngine game){
        double previous = System.nanoTime();
        double lag = 0.0;
        double NS_PER_UPDATE = 1000000000.0/60.0;
        while(isRunning){
            double current = System.nanoTime();
            double elapsed = current-previous;
            lag += elapsed;
            while (lag >= NS_PER_UPDATE){
                game.update();
                lag -= NS_PER_UPDATE;
            }

            renderer.render();
        }
    }

    public Window getGameWindow(){
        return gameWindow;
    }
}
