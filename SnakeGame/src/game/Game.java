package game;

import java.util.ArrayList;
import java.util.Random;
/*
 * Klasa gry.
 * 
 * 
 * 
 */
public class Game implements Runnable {
    private GameFrame frame;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private Snake snake;
    private Board board;

    private int tick = 0;
    private ArrayList<Cell> apples;
    private Frog frog;
    private Random r;
    public static final int DELAY = 50;

    public Game(GamePanel gamePanel, ScorePanel scorePanel, Snake snake, GameFrame frame, Board board) {
        this.frame = frame;
        this.snake = snake;
        this.gamePanel = gamePanel;
        this.scorePanel = scorePanel;
        this.board = board;

        r = new Random();

        apples = new ArrayList<>();
        apples.add(new Cell(r.nextInt(49), r.nextInt(49), CellType.FOOD));
        board.drawApples(apples);

        frog = new Frog();
        board.drawFrog(frog);
        board.drawSnake(snake);
    }

    /* 
     * Ca³a "logika gry" rozgrywa siê w metodzie tick()
     * 
     * Po wszystkim gra jest rysowana na nowo
     */
    @Override
    public void run() {
        while (true) {
            tick();
            gamePanel.repaint();
            scorePanel.repaint();
        }
    }

    public void tick() {
        tick++;
        if (tick > 250000) {   /// warunek ¿eby nie chodzi³o za szybko
            tick = 0;

            // Na pocz¹tku czyœcimy wszystko
            board.clearMovingObjects(snake, frog);
            // wykonujemy nowy ruch
            snake.update();
            frog.update();
          
            // sprawdzanie czy snake wypad³ poza planszê
            if(snake.checkBorder(49, 49))
                stop();

            // rysoawnie Snake'a na planszy
            board.drawSnake(snake);

            /*
             * Sprawdzenie kolizji z jab³kiem
             * Jeœli tak - rysuj nowe jab³ko
             */
            if(snake.checkFood(apples, scorePanel)) {
                apples.add(new Cell(r.nextInt(49), r.nextInt(49), CellType.FOOD));
                board.drawApples(apples);
            }

            // to samo dla ¿aby
           if(snake.checkFrog(frog, scorePanel)) {
        	   	board.clearFrog(frog);
        	   	frog = new Frog();
                board.drawFrog(frog);
            }
            
            // rysowanie ¿aby
            board.drawFrog(frog);
            
            // sprawdzania czy Snake nie zjad³ samego siebie
            if(snake.checkCrash())
                stop();
        }
    }

    public void stop() {
        try {
            frame.gameOver();
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
