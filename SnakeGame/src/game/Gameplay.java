package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private int delay = 100;

    private ImageIcon titleImage;


    SnakeUser snake;
    Apple apple;
    private int score = 0;
    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setSize(851, 577);

        snake = new SnakeUser();
        apple = new Apple();
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        if(snake.getMoves() == 0)
        {
            snake.initPosition(g);
        }

        g.setColor(Color.black);
        g.fillRect(25 , 75, 850, 575);

        /*
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Scores: " + score, 780, 30);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Length: " + snake.getLengthOfSnake(), 780, 50);
        */
        snake.draw(this, g);

        if(apple.isCollision(snake))
        {
            score++;
            snake.afterFoodCollision();
            apple.setPosX();
            apple.setPosY();
        }

        apple.getAppleImage().paintIcon(this, g, apple.getCurrentPosX(), apple.getCurrentPoxY());

        for(int b = 1; b < snake.getLengthOfSnake(); b++)
        {
            if(snake.getSnakeLengthX()[b] == snake.getSnakeLengthX()[0] && snake.getSnakeLengthY()[b] == snake.getSnakeLengthY()[0])
            {
                snake.resetDirection();

                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.PLAIN, 50));
                g.drawString("GAME OVER", 300, 300);

            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        timer.start();
        snake.update();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            snake.setMoves(0);
            score = 0;
            snake.setLengthOfSnake(3);
            snake.resetDirection();
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            snake.moveRight();
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            snake.moveLeft();
        if(e.getKeyCode() == KeyEvent.VK_UP)
            snake.moveUp();
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            snake.moveDown();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
