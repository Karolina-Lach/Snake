package game;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ScoreDialog extends JDialog {
    public ScoreDialog(JFrame owner) {
        super(owner, "Top Score", true);
        setPreferredSize(new Dimension(200,200));

        try {
        	   // Uworzenie obiektu FileReader
        	   FileReader fileReader = new FileReader("D:\\Download\\Snake\\SnakeGame\\top_score.txt");
        	   // Utworzenie obiektu bufferReader
        	   BufferedReader bufferReader = new BufferedReader(fileReader);
        	   String linia;
        	   linia = bufferReader.readLine();
               fileReader.close();
        	   
               var textArea = new JTextArea(linia);
               textArea.setLineWrap(true);
               textArea.setEditable(false);

               add(textArea, BorderLayout.CENTER);
        	  }
        	  catch (FileNotFoundException e) {
        	   e.printStackTrace();
        	  } 
        	  catch (IOException e) {
        	   e.printStackTrace();
        	  }
        

        var ok = new JButton("OK");
        ok.addActionListener(event -> setVisible(false));

        var panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);

        pack();
    }

}
