/**
 * 
 */
package game;

import javax.swing.*;
import java.awt.*;

/**
 * @author klach
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(() ->
		{
			GameFrame game = new GameFrame();
			game.setVisible(true);
		});

	}

}
