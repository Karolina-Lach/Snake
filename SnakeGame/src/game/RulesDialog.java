package game;

import javax.swing.*;
import java.awt.*;

public class RulesDialog extends JDialog {
    public RulesDialog(JFrame owner) {
        super(owner, "Zasady", true);
        setPreferredSize(new Dimension(200,200));
        var textArea = new JTextArea("Zbieraj jab³ka ¿eby zdobywaæ punkty. Nie wychodŸ poza granice planszy. ¯eby rozpocz¹æ wciœnij jedn¹ ze strza³ek.");
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        add(textArea, BorderLayout.CENTER);

        var ok = new JButton("OK");
        ok.addActionListener(event -> setVisible(false));

        var panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);

        pack();
    }

}
