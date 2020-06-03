package game;

public interface MovableObject {

	void move(Cell nextCell);
	void update();
	void moveRight();
	void moveLeft();
	void moveUp();
	void moveDown();
}
