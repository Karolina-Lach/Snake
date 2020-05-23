package game;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    final int ROW_COUNT, COL_COUNT;
    final int cellSize;
    private Cell[][] cells;

    Random r;
    public Board(int rowCount, int columnCount, int cellSize)
    {
        r = new Random();
        ROW_COUNT = rowCount;
        COL_COUNT = columnCount;
        this.cellSize = cellSize;

        cells = new Cell[ROW_COUNT][COL_COUNT];
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COL_COUNT; column++) {
                cells[row][column] = new Cell(row, column);
            }
        }
    }

    public synchronized void clearBoard() {

            for (int i = 0; i < ROW_COUNT; i++) {
                for (int j = 0; j < COL_COUNT; j++) {
                    cells[i][j].setCellType(CellType.EMPTY);
                }
            }
    }

    public int getCellSize() {
        return cellSize;
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public synchronized void drawSnake(Snake snake) throws  InterruptedException {
        try {
            for (int i = 0; i < snake.getSnakeSize(); i++) {
                Cell temp = snake.getBody()[i];
                cells[temp.getRow()][temp.getCol()].setCellType(CellType.SNAKE_NODE);
            }
        } catch (Exception e)
        {
            System.out.println("Thread  interrupted.");
        }
    }

    public synchronized void drawApples(ArrayList<Cell> apples)  throws  InterruptedException {
        try {
            for (int i = 0; i < apples.size(); i++) {
                Cell temp = apples.get(i);
                cells[temp.getRow()][temp.getCol()].setCellType(CellType.FOOD);
            }
        } catch (Exception e)
        {
            System.out.println("Thread  interrupted.");
        }
    }

    public synchronized void drawFrog(ArrayList<Frog> frogs) throws  InterruptedException {
        try {
            for (int i = 0; i < frogs.size(); i++) {
                Cell temp = frogs.get(i).getHead();
                cells[temp.getRow()][temp.getCol()].setCellType(CellType.FROG);
            }
        } catch (Exception e)
        {
            System.out.println("Thread  interrupted.");
        }
    }
    public synchronized void clearSnake(Snake snake) throws  InterruptedException  {
        try {
            for (int i = 0; i < snake.getSnakeSize(); i++) {
                Cell temp = snake.getBody()[i];
                if (temp.getRow() >= 0 && temp.getCol() >= 0)
                    cells[temp.getRow()][temp.getCol()].setCellType(CellType.EMPTY);
            }
        } catch (Exception e)
        {
            System.out.println("Thread  interrupted.");
        }
    }

    public synchronized void clearFrog(ArrayList<Frog> frogs) throws  InterruptedException {
        try {
            for (int i = 0; i < ROW_COUNT; i++) {
                for (int j = 0; j < COL_COUNT; j++) {
                    if(cells[i][j].getCellType() == CellType.FROG)
                        cells[i][j].setCellType(CellType.EMPTY);
                }
            }
        } catch (Exception e)
        {
            System.out.println("Thread  interrupted.");
        }
    }
}