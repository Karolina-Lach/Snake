package game;

import java.util.ArrayList;

public class Snake implements MovableObject {
    private Cell[] body = new Cell[250];
    private int snakeSize = 1;
    private Cell head;
    public volatile boolean hasBeenCheckedForFood;
    public volatile boolean hasBeenCheckedForCrash;
    public volatile boolean isCrashed;

    private boolean right, left, up, down;

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public Snake(Cell initPos)
    {
        right = false;
        left = false;
        up = false;
        down = false;
        init(initPos);
    }


    public void init(Cell initPos) {
        for(int i = 0; i < 250; i++) {
            body[i] = new Cell(-1,-1);
        }
        head = initPos;
        body[0] = head;
        body[1] = new Cell(initPos.getRow() +1, initPos.getCol(), initPos.getCellType());
        body[2] = new Cell(initPos.getRow()+2, initPos.getCol(), initPos.getCellType());
        snakeSize = 3;
    }

    public void grow()
    {
        snakeSize++;
    }

    public void move(Cell nextCell)
    {
        for(int i = snakeSize-1; i >0; i--)
            body[i] = body[i-1];
        body[0] = nextCell;
        head = nextCell;
        //board.setCell(tail.getRow(), tail.getCol(), CellType.EMPTY);
        //board.setCell(head.getRow(), head.getCol(), head.getCellType());
    }

    public void update() {
        if(isRight()) {
            Cell nextCell = new Cell(head.getRow()+1, head.getCol(), CellType.SNAKE_NODE);
            move(nextCell);
        }
        if(isLeft()) {
            Cell nextCell = new Cell(head.getRow()-1, head.getCol(), CellType.SNAKE_NODE);
            move(nextCell);
        }
        if(isUp()) {
            Cell nextCell = new Cell(head.getRow(), head.getCol()-1, CellType.SNAKE_NODE);
            move(nextCell);
        }
        if(isDown()) {
            Cell nextCell = new Cell(head.getRow(), head.getCol()+1, CellType.SNAKE_NODE);
            move(nextCell);
        }

        //System.out.println(head.getCol() + "   " + head.getRow());
    }

    public void moveRight() {
        right = true;
        up = false;
        down = false;
    }

    public void moveLeft() {
        left = true;
        up = false;
        down = false;
    }
    public void moveUp() {
        up = true;
        right = false;
        left = false;
    }
    public void moveDown() {
        down = true;
        right = false;
        left = false;
    }

    public boolean checkCrash()
    {
        for(int i = 1; i < snakeSize; i++) {
            if(body[0].getRow() == body[i].getRow() && body[0].getCol() == body[i].getCol()) {
                if(i != snakeSize - 1) {
                    hasBeenCheckedForCrash = true;
                    isCrashed = true;
                    return true;
                }
            }
        }

        hasBeenCheckedForCrash= true;
        isCrashed = false;
        return false;
    }

    public boolean checkFood(ArrayList<Cell> apples, ScorePanel panel)
    {
        for(Cell apple : apples) {
            if(body[0].getRow() == apple.getRow() && body[0].getCol() == apple.getCol()) {
                grow();
                panel.addPoints();
                apples.remove(apple);
                System.out.println("yum!");
                return true;
            }
        }

        return false;
    }

    public  boolean checkFrog(Frog frog, ScorePanel panel) {
        
            if(body[0].getRow() == frog.getHead().getRow() && body[0].getCol() == frog.getHead().getCol()) {
                grow();
                panel.addPoints();
                
                hasBeenCheckedForFood = true;
                return true;
            }
        

        hasBeenCheckedForFood = true;
        return false;
    }

    public boolean checkBorder(int rows, int cols) {
        if(head.getRow() < 0 || head.getRow() > rows || head.getCol() < 0 || head.getCol() > cols) {
            hasBeenCheckedForCrash = true;
            isCrashed = true;
            return true;
        }
        hasBeenCheckedForCrash = true;
        isCrashed = false;
        return false;
    }
    public void setBody(Cell[] body)
    {
        this.body = body;
    }

    public Cell getHead()
    {
        return head;
    }

    public void setHead(Cell head)
    {
        this.head = head;
    }

    public Cell[] getBody() {
        return body;
    }

    public int getSnakeSize() {
        return snakeSize;
    }
}

