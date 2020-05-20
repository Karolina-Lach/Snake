package game;

import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {
    private GameFrame frame;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private Snake snake;
    private Board board;

    private int tick = 0;
    private ArrayList<Cell> apples;
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

        board.drawSnake(snake);
    }

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
        if (tick > 250000) {
            tick = 0;

            board.clearSnake(snake);
            snake.update(board);

            if(snake.checkBorder(49, 49))
                stop();

            board.drawSnake(snake);

            if(snake.checkFood(apples, scorePanel)) {
                apples.add(new Cell(r.nextInt(49), r.nextInt(49), CellType.FOOD));
                board.drawApples(apples);
            }

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
