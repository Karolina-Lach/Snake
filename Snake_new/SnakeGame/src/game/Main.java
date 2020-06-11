package game;

import java.awt.EventQueue;
/*
 * 
 * Main
 * Wywo³uje g³ówne okno gry
 * 
 * TODO
 *  	zapisywanie do plikuu
 * 		generowanie przeszkód na planszy
 * 		zmiana ruchu "¿aby" - najlepiej tak ¿eby posowa³o pod interfejs MovableObject
 * 
 * 		UWAGA - "jedzenie ¿aby" nie zawsze dzia³a (ale czasami tak)
 */
public class Main {
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GameFrame();
            }
        });
    }
}
