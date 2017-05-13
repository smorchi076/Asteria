package GameplayElements;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Ship extends MovingImage {
	
	public static final int SHIP_WIDTH = 40;
	public static final int SHIP_HEIGHT = 60;
	private double vY;
	private int shootClock, hp;
	private Projectile[] blasts = new Projectile[20];
	
	public Ship(int x, int y) {
		super("resources/spaceship.png", x, y, SHIP_WIDTH, SHIP_HEIGHT);
		vY = 0;
		
	}

	// METHODS
	public void move(int dir) {
		// JUMP!
		if(dir < 0 && vY > -5)
			vY -= dir*.1;
		if(dir > 0 && vY < 5)
			vY -= dir*.1;
	}
			
		
		
		

	public void act(ArrayList<Shape> obstacles) {
		double dir = getDirection();
		moveByAmount(50*vY*Math.cos(dir),50*vY*Math.sin(dir));
		if(shootClock>0)
			shootClock--;
		for(int i = 0; i<blasts.length; i++){
			if(blasts[i]!=null){
				blasts[i].act(null);
			}
		}
		if(vY > 3) {
			vY -= (1.0/vY);
		}
		else if(vY < -3) {
			vY -= (1.0/vY);
		} else {
			vY = 0;
		}
	}
	
	public void turn(Graphics g, ImageObserver io){
		
	}

}
