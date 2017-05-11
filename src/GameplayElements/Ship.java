package GameplayElements;

import java.awt.Shape;
import java.util.ArrayList;

public class Ship extends MovingImage {
	
	public static final int SHIP_WIDTH = 40;
	public static final int SHIP_HEIGHT = 60;
	
	public Ship(int x, int y) {
		super("resources/spaceship.png", x, y, SHIP_WIDTH, SHIP_HEIGHT);
	}

	// METHODS
	public void moveHorizontally(int dir) {
		// WALK!\
		super.x += dir*5;
	}

	public void moveVertically(int dir) {
		// JUMP!
		
		super.y -= dir*5;
		
			
		
		
		
	}

	public void act(ArrayList<Shape> obstacles) {
		// FINISH ME!
		//super.y++;
	}
	
	public void turn(){
		
	}

}
