package game;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel()
    {
        setMinimumSize(new Dimension(100, 750));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Menu");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        JButton topScore = new JButton("Top Score");
        JButton zasady = new JButton("Zasady");
        add(label);
        add(topScore);
        add(zasady);
    }

}
