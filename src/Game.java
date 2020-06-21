import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game {

    public static int WIDTH = 800, HEIGHT = 600;

    private final String GAME_NAME = "Flappy Bird";

    private final Canvas game = new Canvas();

    private Input input;

    private final ArrayList<Updatable> updatables = new ArrayList<>();
    private final ArrayList<Renderable> renderables = new ArrayList<>();


    public void start() {

        //Init Window
        Dimension gameSize = new Dimension(WIDTH, HEIGHT);
        configureCanvas(gameSize);
        JFrame gameWindow = createJFrame(gameSize);

        //Init Input
        input = new Input();
        game.addMouseListener(input);

        //Game lopp
        final int TICKS_PER_SECONDS = 60;
        final int TIME_PER_TICK = 1000 / TICKS_PER_SECONDS;
        final int MAZ_FRAMESKIPS = 5;

        long nextGameTicks = System.currentTimeMillis();
        int loops;
        float interpolation;

        long timeAsLastFPSCheck = 0;
        int ticks = 0;


        while (true) {
            // Updating
            loops = 0;

            while (System.currentTimeMillis() > nextGameTicks && loops < MAZ_FRAMESKIPS) {
                update();
                ticks++;

                nextGameTicks += TIME_PER_TICK;
                loops++;
            }

            //Rendering
            interpolation = (float) (System.currentTimeMillis() + TIME_PER_TICK - nextGameTicks) / (float) TIME_PER_TICK;
            render(interpolation);

            //FPS check
            if (System.currentTimeMillis() - timeAsLastFPSCheck >= 1000) {
                ticks = 0;
                timeAsLastFPSCheck = System.currentTimeMillis();
            }
        }

        //Game end
    }

    private void configureCanvas(Dimension gameSize) {
        game.setSize(gameSize);
        game.setMinimumSize(gameSize);
        game.setMaximumSize(gameSize);
        game.setPreferredSize(gameSize);
    }

    private JFrame createJFrame(Dimension gameSize) {
        JFrame jf = new JFrame(GAME_NAME);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(gameSize);
        jf.setResizable(false);
        jf.setVisible(true);
        //Add canvas to JFrame
        jf.add(game);
        //To center window in screen
        jf.setLocationRelativeTo(null);
        return jf;
    }


    void addUpdatable(Updatable u ){
        updatables.add(u);
    }
    void removeUpdatable(Updatable u){
        updatables.remove(u);
    }

    void addRenderable(Renderable r){
        renderables.add(r);
    }

    void removeRenderable(Renderable r){
        renderables.remove(r);
    }



    private void update() {
        for (Updatable u : updatables) {
            u.update(input);
        }
    }

    private void render(float interpolation) {
        BufferStrategy bufferStrategy = game.getBufferStrategy();
        if (createBufferStrategy(bufferStrategy)) return;

        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
        g2d.clearRect(0, 0, game.getWidth(), game.getHeight());

        for (Renderable r : renderables) {
            r.render(g2d, interpolation);
        }
        g2d.dispose();
        bufferStrategy.show();
    }


    private boolean createBufferStrategy(BufferStrategy bufferStrategy) {
        if (bufferStrategy == null) {
            game.createBufferStrategy(2);
            return true;
        }
        return false;
    }
}
