public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Pipes pipes = new Pipes();
        Bird bird = new Bird(pipes);

        game.addRenderable(pipes);
        game.addUpdatable(pipes);

        game.addRenderable(bird);
        game.addUpdatable(bird);
        //Game Start
        game.start();
    }
}
