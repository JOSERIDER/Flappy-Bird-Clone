import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bird implements Renderable, Updatable {

    private int x, y;
    private float yVel;
    private float baseYVel = -6.0f;
    private float gravity = .25f;

    private Pipes pipes;
    private int scoredPipe = 0;
    private int score = 0;


    private Font gameFont = new Font("Arial", Font.BOLD, 30);

    private BufferedImage flapUp;
    private BufferedImage flapDown;


    public Bird(Pipes pipes) {
        resetBird();
        this.pipes = pipes;


        try {
            flapUp = Stripe.getStripe("bird_up.png");
            flapDown = Stripe.getStripe("bird_down.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetBird() {
        int INITIAL_POSITION = 100;

        x = INITIAL_POSITION;
        y = INITIAL_POSITION;
        yVel = baseYVel;
    }

    private void flap() {
        yVel = baseYVel;
    }

    @Override
    public void render(Graphics2D graphics2D, float interpolation) {
        graphics2D.setColor(Color.BLUE);

        graphics2D.drawImage(yVel <= 0 ? flapUp : flapDown, x, (int) (y + (yVel * interpolation)), null);

        graphics2D.setFont(gameFont);
        graphics2D.drawString("Score: " + score, 20, 50);
    }

    @Override
    public void update(Input input) {

        float[] pipeCoords = pipes.getCurrentPipe();
        float pipeX = pipeCoords[0];
        float pipeY = pipeCoords[1];

        y += yVel;
        yVel += gravity;

        if (y < 0) {
            y = 0;
            yVel = 0;
        }

        if (input.isSpacePressed()) flap();


        if ((x >= pipeX && x <= pipeX + pipes.getPipeWidth()
                && (y <= pipeY || y >= pipeY + pipes.getPipeVerticalSpacing()))
                || y >= Game.HEIGHT) {
            pipes.resetPipes();
            resetBird();
            score = 0;
        } else {
            int currentPipeId = pipes.getCurrentPipeId();
            score = (scoredPipe != currentPipeId) ? score + 1 : score;
            scoredPipe = currentPipeId;
        }
    }
}
