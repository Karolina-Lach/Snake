package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeUser {
    /* Długość węża w pionie i w poziomie. Maksymalna długość to 750 */
    final private int[] snakeLengthX = new int[750];
    final private int[] snakeLengthY = new int[750];

    /* Zmienne informujące, w którą stronę porusza się wąż */
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    /* Przechowują zdjęcia węża */
    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon downMouth;
    private ImageIcon upMouth;
    private ImageIcon snakeImage;

    /* Aktualna długość węża */
    private int lengthOfSnake;

    private int moves;

    public SnakeUser()
    {
        lengthOfSnake = 3;
        moves = 0;

        rightMouth = new ImageIcon("rightmouth.png");
        leftMouth = new ImageIcon("leftmouth.png");
        downMouth = new ImageIcon("downmouth.png");
        upMouth = new ImageIcon("upmouth.png");
        snakeImage = new ImageIcon("snakeimage.png");
    }

    public void initPosition(Graphics g)
    {
        snakeLengthX[2] = 50;
        snakeLengthX[1] = 75;
        snakeLengthX[0] = 100;

        snakeLengthY[2] = 100;
        snakeLengthY[1] = 100;
        snakeLengthY[0] = 100;
    }

    public void resetDirection()
    {
        right = false;
        left = false;
        up = false;
        down = false;
    }

    public void draw(JComponent component, Graphics g)
    {
        rightMouth.paintIcon(component, g, snakeLengthX[0], snakeLengthY[0]);
        for(int a = 0; a < lengthOfSnake; a++)
        {
            if(a == 0 && right)
            {
                rightMouth.paintIcon(component, g, snakeLengthX[0], snakeLengthY[0]);
            }
            if(a == 0 && left)
            {
                leftMouth.paintIcon(component, g, snakeLengthX[0], snakeLengthY[0]);;
            }
            if(a == 0 && down)
            {

                downMouth.paintIcon(component, g, snakeLengthX[0], snakeLengthY[0]);
            }
            if(a == 0 && up)
            {
                upMouth.paintIcon(component, g, snakeLengthX[0], snakeLengthY[0]);
            }
            if(a != 0)
            {
                snakeImage.paintIcon(component, g, snakeLengthX[a], snakeLengthY[a]);
            }
        }
    }

    public void afterFoodCollision()
    {
        lengthOfSnake++;
    }

    public void moveRight()
    {
        moves++;
        if(!left)
            right = true;
        else
        {
            right = false;
            left = true;
        }
        down = false;
        up = false;
    }

    public void moveLeft()
    {
        moves++;
        if(!right)
            left = true;
        else
        {
            left = false;
            right = true;
        }
        down = false;
        up = false;
    }

    public void moveUp()
    {
        moves++;
        if(!down)
            up = true;
        else
        {
            up = false;
            down = true;
        }
        right = false;
        left = false;
    }

    public void moveDown()
    {
        moves++;
        if(!up)
            down = true;
        else
        {
            down = false;
            up = true;
        }
        right = false;
        left = false;
    }

    public void drawMovingRight()
    {
        for(int a = lengthOfSnake - 1; a >= 0; a--)
        {
            snakeLengthY[a+1] = snakeLengthY[a];
        }
        for(int a = lengthOfSnake; a >= 0; a--)
        {
            if(a==0)
                snakeLengthX[a] = snakeLengthX[a] + 25;
            else
                snakeLengthX[a] = snakeLengthX[a-1];

            if(snakeLengthX[a] > 850)
                snakeLengthX[a] = 25;
        }
    }

    public void drawMovingLeft()
    {
        for(int a = lengthOfSnake - 1; a >= 0; a--)
        {
            snakeLengthY[a+1] = snakeLengthY[a];
        }
        for(int a = lengthOfSnake; a >= 0; a--)
        {
            if(a==0)
                snakeLengthX[a] = snakeLengthX[a] - 25;
            else
                snakeLengthX[a] = snakeLengthX[a-1];

            if(snakeLengthX[a] < 25)
                snakeLengthX[a] = 850;
        }
    }

    public void drawMovingUp()
    {
        for(int a = lengthOfSnake - 1; a >= 0; a--)
        {
           snakeLengthX[a+1] = snakeLengthX[a];
        }
        for(int a = lengthOfSnake; a >= 0; a--)
        {
            if(a==0)
                snakeLengthY[a] = snakeLengthY[a] - 25;
            else
                snakeLengthY[a] = snakeLengthY[a-1];

            if(snakeLengthY[a] < 75)
                snakeLengthY[a] = 625;
        }
    }

    public void drawMovingDown()
    {
        for(int a = lengthOfSnake - 1; a >= 0; a--)
        {
           snakeLengthX[a+1] = snakeLengthX[a];
        }
        for(int a = lengthOfSnake; a >= 0; a--)
        {
            if(a==0)
                snakeLengthY[a] = snakeLengthY[a] + 25;
            else
                snakeLengthY[a] = snakeLengthY[a-1];

            if(snakeLengthY[a] > 625)
                snakeLengthY[a] = 75;
        }
    }

    public void update()
    {
        if(right)
            drawMovingRight();
        if(left)
            drawMovingLeft();
        if(up)
            drawMovingUp();
        if(down)
            drawMovingDown();
    }
    public int[] getSnakeLengthX() {
        return snakeLengthX;
    }

    public int[] getSnakeLengthY() {
        return snakeLengthY;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
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

    public ImageIcon getRightMouth() {
        return rightMouth;
    }

    public void setRightMouth(ImageIcon rightMouth) {
        this.rightMouth = rightMouth;
    }

    public ImageIcon getLeftMouth() {
        return leftMouth;
    }

    public void setLeftMouth(ImageIcon leftMouth) {
        this.leftMouth = leftMouth;
    }

    public ImageIcon getDownMouth() {
        return downMouth;
    }

    public void setDownMouth(ImageIcon downMouth) {
        this.downMouth = downMouth;
    }

    public ImageIcon getUpMouth() {
        return upMouth;
    }

    public void setUpMouth(ImageIcon upMouth) {
        this.upMouth = upMouth;
    }

    public ImageIcon getSnakeImage() {
        return snakeImage;
    }

    public void setSnakeImage(ImageIcon snakeImage) {
        this.snakeImage = snakeImage;
    }

    public int getLengthOfSnake() {
        return lengthOfSnake;
    }

    public void setLengthOfSnake(int lengthOfSnake) {
        this.lengthOfSnake = lengthOfSnake;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

}
