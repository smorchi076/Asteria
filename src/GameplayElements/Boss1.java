package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Boss1 extends MovingImage{

	private Boss1Projectile[] blasts = new Boss1Projectile[40];
	private int shootClock;
	private int hp;
	private int movingLeft, movingRight, waiting;
	
	public Boss1(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 30;
		movingLeft = 100;
		movingRight = 0;
		waiting = 0;
	}
	
	
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
		
		g.setColor(Color.RED);
		g.fillRect((int)getX(), (int)getY(), (int)getWidth(), 2);
		g.setColor(Color.GREEN);
		g.fillRect((int)getX(), (int)getY(), (int)(hp/30.0 * getWidth()), 2);
		
		for(int i = 0; i<blasts.length; i++){
			if(blasts[i]!=null && !blasts[i].isFizzled()){
				blasts[i].draw(g, io);
			}
		}
	}
	public void shoot(){
		if(shootClock==0){
			for(int i=0; i<blasts.length; i++){
				if(blasts[i]==null || blasts[i].isFizzled()){
					blasts[i] = new Boss1Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI, "resources/Bullet.png");
					shootClock = 100;
					break;
				}
			}
		}
		
	}
	
	public void act(Ship ship) {
		super.turnToward((int)ship.getCenterX(), (int)ship.getCenterY());
		if(Math.abs(ship.getCenterX() - this.getCenterX()) > 300 || ship.getCenterY() - this.getCenterY() > 300)
			moveByAmount(2*Math.cos((super.getDirection())),2*Math.sin(super.getDirection()));
		else if(movingLeft > 1) {
			moveByAmount(2*Math.cos((super.getDirection() + Math.PI/2)),2*Math.sin(super.getDirection()+Math.PI/2));
			movingLeft--;
		}
		else if(movingRight > 1) {
			moveByAmount(2*Math.cos(super.getDirection() - Math.PI/2),2*Math.sin(super.getDirection()-Math.PI/2));
			movingRight--;
		}
		else if(waiting > 1) {
			waiting--;
		}
		else if(movingLeft == 1) {
			movingLeft = 0;
			waiting = 50;
		}
		else if(waiting == 1 && movingRight == 0) {
			waiting = 0;
			movingRight = 200;
		}
		else if(waiting == 1 && movingRight!= 0) {
			waiting = 0; 
			movingRight = 0;
			movingLeft = 200;
		}
		else if(movingRight == 1) {
			waiting = 50;
		}
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
	}
	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	
	public int getHp(){
		return hp;
	}
}
