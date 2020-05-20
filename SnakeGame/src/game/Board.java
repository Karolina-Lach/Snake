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

    public void clearBoard() {
        for(int i = 0; i <ROW_COUNT; i++) {
            for(int j = 0; j <COL_COUNT; j++) {
                cells[i][j].setCellType(CellType.EMPTY);
            }
        }
    }
    public void initSnake(Cell initPos) {
        cells[initPos.getRow()][initPos.getCol()].setCellType(CellType.SNAKE_NODE);
    }
    public int getCellSize() {
        return cellSize;
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public void setCells(Cell[][] cells)
    {
        this.cells = cells;
    }
    public void setCell(int i, int j, CellType type)
    {
        this.cells[i][j].setCellType(type);
    }

    public void drawSnake(Snake snake) {
        for(int i = 0; i < snake.getSnakeSize() ; i++) {
            Cell temp = snake.getBody()[i];
            cells[temp.getRow()][temp.getCol()].setCellType(CellType.SNAKE_NODE);
        }
    }

    public void drawApples(ArrayList<Cell> apples) {
        for(int i = 0; i < apples.size() ; i++) {
            Cell temp = apples.get(i);
            cells[temp.getRow()][temp.getCol()].setCellType(CellType.FOOD);
        }
    }

    public void clearSnake(Snake snake) {
        for(int i = 0; i < snake.getSnakeSize() ; i++) {
            Cell temp = snake.getBody()[i];
            if(temp.getRow() >= 0 &&  temp.getCol() >= 0 )
                cells[temp.getRow()][temp.getCol()].setCellType(CellType.EMPTY);
        }
    }
}