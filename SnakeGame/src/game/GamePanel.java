package game;

import javax.swing.*;
import java.awt.*;

/*
 * Klasa panel kt�ra rysuje poszczeg�lne elementy
 * 
 * Klasa Board sk�ada si� z tablicy dwuwymiarowej, w kt�rej zapisywane s� kom�rki o poszczeg�lnych typach:
 *  EMPTY,
    FOOD,
    SNAKE_NODE,
    FROG
    Na podstawie typu kom�rka wype�niana jest odpowiednim kolorem
 */
public class GamePanel extends JPanel {
    private static final int WIDTH = 500, HEIGHT = 500;
    
    private Board board;
    /**
     * Constructs the game field, which is the rectangular area where snake can
     * move
     */
    public GamePanel(Board board) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setBoard(board);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < board.ROW_COUNT; i++) {
            for (int j = 0; j < board.COL_COUNT; j++) {
                if (board.getCells()[i][j].getCellType() == CellType.EMPTY) {
                    //g.clearRect(i * board.getCellSize(),j * board.getCellSize(),board.getCellSize(), board.getCellSize());
                    g.setColor(Color.black);
                    g.fillRect(i * board.getCellSize(), j * board.getCellSize(), board.getCellSize(), board.getCellSize());
                } else if (board.getCells()[i][j].getCellType() == CellType.FOOD) {
                    // g.clearRect(i * board.getCellSize(),i * board.getCellSize(),board.getCellSize(), board.getCellSize());
                    g.setColor(Color.red);
                    g.fillRect(i * board.getCellSize(), j * board.getCellSize(), board.getCellSize(), board.getCellSize());
                } else if (board.getCells()[i][j].getCellType() == CellType.SNAKE_NODE) {
                    //g.clearRect(i * board.getCellSize(),j * board.getCellSize(),board.getCellSize(), board.getCellSize());
                    g.setColor(Color.green);
                    g.fillRect(i * board.getCellSize(), j * board.getCellSize(), board.getCellSize(), board.getCellSize());
                } else if (board.getCells()[i][j].getCellType() == CellType.FROG) {
                    //g.clearRect(i * board.getCellSize(),j * board.getCellSize(),board.getCellSize(), board.getCellSize());
                    g.setColor(new Color(67, 124, 36));
                    g.fillRect(i * board.getCellSize(), j * board.getCellSize(), board.getCellSize(), board.getCellSize());
                }
            }
        }
    }
}
