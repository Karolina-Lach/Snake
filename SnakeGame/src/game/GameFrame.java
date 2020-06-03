package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/* Klasa GameFrame
 * 
 * G³ówna frame gry
 * Rozpoczyna w¹tek gry, zatrzymuje w¹tek gry, jeœli pojawi siê b³¹d
 * 
 */
public class GameFrame extends JFrame {
    private ScorePanel scorePanel;
    private GamePanel gamePanel;
    private Thread thread;
    private Snake snake;
    private Board board;
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
        addKeyListener(new KeyboardHandler());      // dodaje listener wejœcia u¿ytkownika

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
        Runnable r = new Game(gamePanel, scorePanel, snake, this, board);
        thread = new Thread(r);
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
        thread.start();
    }

    /*
     * Jeœli gra skoñczona - pokazuje siê okienko
     */
    public void gameOver() {
        int returnValue = JOptionPane.showConfirmDialog(this,
                "Do you want to start a new game?", "GAME OVER!", JOptionPane
                        .OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        switch (returnValue) {
        /*
         * Jeœli gramy od nowa
         */
            case JOptionPane.OK_OPTION:
                started = false;
                snake = new Snake(new Cell(10,10, CellType.SNAKE_NODE));
                board.clearBoard();
                scorePanel.clear();
                scorePanel.repaint();
                gamePanel.repaint();
                Runnable r = new Game(gamePanel, scorePanel, snake, this, board);
                thread = null;
                thread = new Thread(r);
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

    
/*
 * Przetwarza wejœcia u¿ytkownika
 */
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