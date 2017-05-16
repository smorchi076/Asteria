package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Ship extends MovingImage {
	
	private double vY;
	private int shootClock, hp;
	private Projectile[] blasts = new Projectile[20];
	private int willSlow;
	private int speed;
	private int sj;
	
	public Ship(int x, int y, String img, int width, int height, int hp, int maxSpeed, double dir) {
		super(img, x, y, width, height, 0);
		vY = 0;
		willSlow = 0;
		this.hp = hp;
		speed = maxSpeed;
		sj = 0;
	}

	// METHODS
	public void move(double dir) {
		// JUMP!
		if(vY > -speed && vY < speed) {
			if(dir < 0)
				vY -= dir*.1;
				
				
			if(dir > 0)
				vY -= dir*.1;
			
		}
		
	}
			
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
		
		
		for(int i = 0; i<blasts.length; i++){
			if(blasts[i]!=null && !blasts[i].isFizzled()){
				blasts[i].draw(g, io);
			}
		}
	}
		
		

	public void act(Ship ship) {
		double dir = getDirection();
		moveByAmount(1*vY*Math.cos(dir),1*vY*Math.sin(dir));
		if(shootClock>0)
			shootClock--;
		for(int i = 0; i<blasts.length; i++){
			if(blasts[i]!=null){
				blasts[i].act(ship);
			}
		}
		if(ship != null) {
			for(Projectile p : ship.getBullets()) {
				if(p!=null && p.intersects(this) && !p.isFizzled()) {
					dropHp(1);
					p.fizzle();
				}
			}
		}
		if(vY > .2 && willSlow == 0) {
			vY -= vY/5.0 + .1;
		} else if(vY < -.1 && willSlow == 0) {
			vY -= vY/5.0 - .1;
		} else if(vY<.1 && vY>-.1) {
			vY = 0;
		}
		if(willSlow == 0)
			willSlow = 50;
		willSlow--;
	}
	
	public void turn(Graphics g, ImageObserver io){
		
	}
	
	public void shoot(){
		if(shootClock==0){
			for(int i=0; i<blasts.length; i++){
				if(blasts[i]==null || blasts[i].isFizzled()){
					blasts[i] = new Projectile((int)(getX()), (int)(getY()+getHeight()/2 - 5), super.getDirection());
					shootClock = 20;
					break;
				}
			}
		}
		
	}
	
	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int amount) {
		hp = amount;
	}
	
	public Projectile[] getBullets() {
		return blasts;
	}
	
	public void addSpaceJunk() {
		sj++;
	}
	
	public int getSpaceJunk() {
		return sj;
	}
}
