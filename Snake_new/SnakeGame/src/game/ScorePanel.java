package game;

import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class ScorePanel extends JPanel {

    public static final int PANEL_WIDTH = 400;
    public static final int PANEL_HEIGHT = 50;

    private final Font FONT;
    private final String SCORE_LABEL = "SCORE:";
    private String score;
    private int points;

    public ScorePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.DARK_GRAY);

        points = 0;
        score = "0";
        FONT = new Font("SansSerif", Font.BOLD, 20);
    }


    public void addPoints() {
        this.points++;
        score = new String(this.points + "");
        repaint();
    }

    /**
     * Clears the score back to its intial value of 0
     */
    public void clear() {
    	
    	 try {
      	   // Uworzenie obiektu FileReader
      	   FileReader fileReader = new FileReader("D:\\Download\\Snake\\SnakeGame\\top_score.txt");
      	   // Utworzenie obiektu bufferReader
      	   BufferedReader bufferReader = new BufferedReader(fileReader);
      	   String linia;
      	   linia = bufferReader.readLine();
      	   Integer score_max = Integer.valueOf(linia);
           fileReader.close();
          
           if (points > score_max) {
               System.out.println("Nowy najlepszy wynik: "+score);
               FileWriter fileWriter = new FileWriter("D:\\Download\\Snake\\SnakeGame\\top_score.txt");
               BufferedWriter writer = new BufferedWriter(fileWriter);
               writer.write(score);
               writer.close();
        	 }
      	  }
      	  catch (FileNotFoundException e) {
      	   e.printStackTrace();
      	  } 
      	  catch (IOException e) {
      	   e.printStackTrace();
      	  }
  
    	 
    	points = 0;
        score = "0";
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(FONT);
        g2.setPaint(new Color(34, 136, 215)); // BLUE
        g2.drawString(SCORE_LABEL, 15, 32);
        g2.setPaint(new Color(215, 34, 38)); // RED
        g2.drawString(score, 105, 32);
    }
}