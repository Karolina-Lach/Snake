package game;

import java.awt.EventQueue;
/*
 * 
 * Main
 * Wywo�uje g��wne okno gry
 * 
 * TODO
 *  	zapisywanie do plikuu
 * 		generowanie przeszk�d na planszy
 * 		zmiana ruchu "�aby" - najlepiej tak �eby posowa�o pod interfejs MovableObject
 * 
 * 		UWAGA - "jedzenie �aby" nie zawsze dzia�a (ale czasami tak)
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
