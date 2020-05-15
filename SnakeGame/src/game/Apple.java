package game;

import javax.swing.*;
import java.util.Random;

public class Apple {
    private int [] possiblePosX = new int[100];
    private int [] possiblePosY = new int[100];

    int maxX;
    int maxY;

    private ImageIcon appleImage;
    private Random random = new Random();
    private int posX;
    private int posY;

    public Apple()
    {
        int i = 0;
        int a = 25;
        for(i = 0; a <= 850; i++)
        {
            possiblePosX[i] = a;
            a = a + 25;
        }
        maxX = i;
        posX = random.nextInt(maxX);

        a = 25;
        for(i = 0; a <= 575; i++)
        {
            possiblePosY[i] = a;
            a = a + 25;
        }
        maxY = i;
        posY = random.nextInt(maxY);

        appleImage = new ImageIcon("enemy.png");
    }

    public int[] getPossiblePosX() {
        return possiblePosX;
    }

    public void setPossiblePosX(int[] possiblePosX) {
        this.possiblePosX = possiblePosX;
    }

    public int[] getPossiblePosY() {
        return possiblePosY;
    }

    public void setPossiblePosY(int[] possiblePosY) {
        this.possiblePosY = possiblePosY;
    }

    public ImageIcon getAppleImage() {
        return appleImage;
    }

    public void setAppleImage(ImageIcon appleImage) {
        this.appleImage = appleImage;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX() {
        this.posX = random.nextInt(maxX);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY() {
        this.posY = random.nextInt(maxY);
    }

    public boolean isCollision(SnakeUser snake)
    {
        return (possiblePosX[posX] == snake.getSnakeLengthX()[0] && possiblePosY[posY] == snake.getSnakeLengthY()[0]);
    }

    public int getCurrentPosX()
    {
        return possiblePosX[posX];
    }

    public int getCurrentPoxY()
    {
        return possiblePosY[posY];
    }
}
