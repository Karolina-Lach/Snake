package game;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {
    private GameFrame frame;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private Snake snake;
    private Board board;

    private int score = 0;
    private int tick = 0;
    private ArrayList<Cell> apples;
    private ArrayList<Frog> frogs;
    private Random r;
    public static final int DELAY = 50;

  //  private boolean isRunning = true;

    public Game(GamePanel gamePanel, ScorePanel scorePanel, Snake snake, ArrayList<Cell> apples,
                ArrayList<Frog> frogs, GameFrame frame, Board board) {
        this.frame = frame;
        this.snake = snake;
        this.gamePanel = gamePanel;
        this.scorePanel = scorePanel;
        this.board = board;
        this.apples = apples;
        this.frogs = frogs;

        r = new Random();

        apples.add(new Cell(r.nextInt(49), r.nextInt(49), CellType.FOOD));
        frogs.add(new Frog());
        try {
            board.drawApples(apples);
            board.drawFrog(frogs);
            board.drawSnake(snake);
        } catch (Exception e)
        {
            System.out.println("Thread  interrupted.");
        }
    }


    @Override
    public void run() {
        try {
            while (true) {
                String name = Thread.currentThread().getName();
                if (name == "snake") {
                    runSnake();
                }

                if (name == "frog") {
                    runFrog();


                }


                Thread.sleep(50);
            }
        }
        catch (Exception e)
        {
            System.out.println("Interrupted");
        }
    }

   public synchronized void runSnake() {
        try {
            System.out.println("snake");
            board.clearSnake(snake);
            snake.update(board);


            snake.hasBeenCheckedForCrash = false;
            if (snake.checkBorder(49, 49)) {
                snake.hasBeenCheckedForCrash = true;
                stop();
            }
            snake.hasBeenCheckedForCrash = true;
            board.drawSnake(snake);

            snake.hasBeenCheckedForCrash = false;
            if (snake.checkCrash()) {
                snake.hasBeenCheckedForCrash = true;
                stop();
            }

            snake.hasBeenCheckedForCrash = true;
            if (snake.checkFood(apples, scorePanel)) {
                apples.add(new Cell(r.nextInt(49), r.nextInt(49), CellType.FOOD));
                board.drawApples(apples);
            }

            handleFrogCollision();

            gamePanel.repaint();
            scorePanel.repaint();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
   }

   public synchronized void runFrog() {
       try {
           System.out.println("frog");
           board.clearFrog(frogs);
           for (int i = 0; i < frogs.size(); i++)
               frogs.get(i).update();
           board.drawFrog(frogs);

            while (!snake.hasBeenCheckedForFood)
                wait();

           gamePanel.repaint();
           scorePanel.repaint();

           while (!snake.hasBeenCheckedForCrash)
               wait();

           if(snake.isCrashed)
               stop();
           //wait();
       } catch (InterruptedException e) {
           System.out.println("Interrupted");
       }
   }

   public synchronized void handleFrogCollision() {
       try {
           snake.hasBeenCheckedForFood = false;
           if (snake.checkFrog(frogs, scorePanel)) {
               frogs.remove(0);
               board.clearFrog(frogs);
               System.out.println("ate frog");
               frogs.add(new Frog());
               board.drawFrog(frogs);
               // notifyAll();
           }
           snake.hasBeenCheckedForFood = true;
           notifyAll();
       } catch (InterruptedException e) {
           System.out.println("Interrupted exception");
       }
   }
    public void stop() {
        //try {
            //isRunning = false;
            //Thread.currentThread().join();
            frame.gameOver();
        //} catch (InterruptedException e) {
          //  System.out.println("Interrupted");
        //}

    }
}

