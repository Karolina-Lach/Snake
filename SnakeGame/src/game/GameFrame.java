package game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    final private int WIDTH = 1200;
    final private int HEIGHT = 750;

    private JPanel menuPanel;
    private JPanel headerPanel;
    private JPanel gamePanel;
    private JPanel content;

    public GameFrame()
    {
        menuPanel = new Menu();
        menuPanel.setPreferredSize(new Dimension(200, 650));

        gamePanel = new Gameplay();
        gamePanel.setPreferredSize(new Dimension(850, 750));

        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(1050, 50));
        JLabel label = new JLabel("SNAKE");
       // headerPanel.add(label);

        content = new JPanel();
        content.setLayout(new BorderLayout());

        content.add(headerPanel , BorderLayout.NORTH);
        content.add(menuPanel  , BorderLayout.EAST);
        content.add(gamePanel, BorderLayout.CENTER);

        setContentPane(content);
        setTitle("BorderTest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

    }
}

class Menu extends JPanel
{
    Menu()
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        Box box = new Box(BoxLayout.Y_AXIS);
        add(box);

        JButton button = new JButton("Top Score");
        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 70));

        JButton button1 = new JButton("Zasady");
        button1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button1.setPreferredSize(new Dimension(200, 70));

        add( Box.createVerticalStrut(100) );
        //add(menuText);
        add( Box.createVerticalStrut(10) );
        add(button);
        add( Box.createVerticalStrut(10) );
        add(button1);
        add( Box.createVerticalStrut(10) );

    }
}

class Header extends JPanel
{
    Header()
    {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        Box box = new Box(BoxLayout.X_AXIS);
        add(box);

        JLabel label = new JLabel("SNAKE");
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(label);
    }
}