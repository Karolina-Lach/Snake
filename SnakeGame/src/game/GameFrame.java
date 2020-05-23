package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    private ScorePanel scorePanel;
    private GamePanel gamePanel;
    private Thread snakeThread;
    private Thread frogThread;
    private volatile Snake snake;
    private volatile Board board;
    private volatile ArrayList<Cell> apples;
    private volatile ArrayList<Frog> frogs;
    private RulesDialog dialog;

    JMenuBar menuBar;

    private boolean started = false;

    public GameFrame() {
        initComponents();
        initGame();
        initFrame();

    }

    private void initComponents() {
        board = new Board(50,50,10);

        setLayout(new BorderLayout());
        addKeyListener(new KeyboardHandler());

        scorePanel = new ScorePanel();
        add(scorePanel, BorderLayout.CENTER);

        gamePanel = new GamePanel(board);
        add(gamePanel, BorderLayout.SOUTH);

        menuBar = new JMenuBar();
        var optionsMenu = new JMenu("Opcje");

        var rulesItem = new JMenuItem("Zasady");
        var scoreItem = new JMenuItem("Top score");

        rulesItem.addActionListener(event -> {
            if(dialog == null)
                dialog = new RulesDialog(GameFrame.this);
            dialog.setVisible(true);
        });

        optionsMenu.add(rulesItem);
        optionsMenu.add(scoreItem);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
    }

    private void initGame() {
        snake = new Snake(new Cell(10,10, CellType.SNAKE_NODE));
        apples = new ArrayList<>();
        frogs = new ArrayList<>();

        Runnable r = new Game(gamePanel, scorePanel, snake, apples, frogs, this, board);
        snakeThread = new Thread(r, "snake");
        Runnable r1 = new Game(gamePanel, scorePanel, snake, apples, frogs,this, board);
        frogThread = new Thread(r1, "frog");
    }

    private void initFrame() {
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void newGame() {
        started = true;
        snakeThread.start();
        frogThread.start();
    }

    public void gameOver() {
        try
        {
            System.out.println("over?");

            System.out.println("over.");
            int returnValue = JOptionPane.showConfirmDialog(this,
                    "Do you want to start a new game?", "GAME OVER!", JOptionPane
                            .OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            switch (returnValue) {
                case JOptionPane.OK_OPTION:
                    snakeThread.join();
                    frogThread.join();
                    System.out.println("slndsd");
                    snakeThread = null;
                    frogThread = null;

                    started = false;
                    snake = new Snake(new Cell(10,10, CellType.SNAKE_NODE));
                    board.clearBoard();
                    scorePanel.clear();
                    scorePanel.repaint();
                    gamePanel.repaint();

                    Runnable r = new Game(gamePanel, scorePanel, snake, apples, frogs, this, board);

                    snakeThread = new Thread(r, "snake");

                    Runnable r1 = new Game(gamePanel, scorePanel, snake, apples, frogs, this, board);

                    frogThread = new Thread(r1, "frog");

                    newGame();
                    break;

                case JOptionPane.CANCEL_OPTION:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(getParent(),
                            "Something went wrong :( /n Please relunch app");
                    break;
            }
        }
        catch (Exception e)
            {
                System.out.println("Interrupted");
        }
    }

    private class KeyboardHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (snake.isDown()) return;
                if (!started) newGame();
                if (snake != null) {
                    snake.moveUp();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (snake.isUp()) return;
                if (!started) newGame();
                if (snake != null) {
                    snake.moveDown();;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (snake.isRight()) return;
                if (!started) newGame();
                if (snake != null) {
                    snake.moveLeft();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (snake.isLeft()) return;
                if (!started) newGame();
                if (snake != null) {
                    snake.moveRight();
                }
            }
        }
    }
}

