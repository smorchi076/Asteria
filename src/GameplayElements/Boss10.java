package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * Represents Boss 10
 * @author Morchi
 *
 */
public class Boss10 extends MovingImage {
	
	private Projectile[] blasts = new Projectile[15];
	private int shootClock;
	private int hp;
	private int waiting;
	private double dx, dy;
	
	/**
	 * Creates boss 10
	 * @param x x coord
	 * @param y y coord
	 * @param img image
	 * @param width width
	 * @param height height
	 */
	public Boss10(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 30;
		waiting = 300;
	}
	
	/**
	 * Draws
	 */
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
		if(hp > 30){
			g.setColor(Color.BLUE);
			g.fillRect((int)(getX()+getWidth()/8), (int)getY(), (int)(hp/40 * getWidth()), 2);
		}
		g.setColor(Color.RED);
		g.fillRect((int)getX(), (int)getY(), (int)getWidth()/8, 2);
		g.setColor(Color.GREEN);
		g.fillRect((int)getX(), (int)getY(), (int)(hp/240.0 * getWidth()), 2);
		for(int i = 0; i<blasts.length; i++){
			if(blasts[i]!=null && !blasts[i].isFizzled()){
				blasts[i].draw(g, io);
			}
		}
	}
	/**
	 * Shoots
	 */
	public void shoot(){
		if(shootClock==0){
			for(int i=0; i<blasts.length; i++){
				if(blasts[i]==null || blasts[i].isFizzled()){
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), i/15.0*Math.PI*2, "resources/Bullet.png", 20,20,20);
					shootClock = 75;
				}
			}
		}
		
	}
	
	/**
	 * Movement of boss 10
	 * @param ship ship
	 */
	public void act(Ship ship) {
		if(this.x - ship.getCenterX() > 0) {
			dx = -1;
		} else {
			dx = 1;
		}
		if(this.y - ship.y > 0) {
			dy = -1;
		} else {
			dy = 1;
		}
		
		if(waiting > 1) {
			moveByAmount(1*Math.cos((super.getDirection()+ Math.PI/2) * (waiting/500.0*(Math.PI*2))),1*Math.sin((super.getDirection()+ Math.PI/2 * (waiting/500.0*(Math.PI*2)))));
			waiting--;
		} 
		if(waiting > 275){
			hp++;
		}
		else if(waiting == 1) {
			waiting = 275;
			double d = Math.random()*Math.PI*2;
			moveToLocation(ship.x+Math.cos(d)*(100+Math.random()*50), ship.y+Math.sin(d)*(100+Math.random()*50));
		}
		super.turnToward((int)(ship.x - ship.width/2), (int)(ship.y - ship.height/2));
		super.turn(super.getDirection()+Math.PI);
		/*if(Math.abs(ship.getCenterX() - this.getCenterX()) > 200 || Math.abs(ship.getCenterY() - this.getCenterY()) > 200)
			moveByAmount(2*Math.cos((super.getDirection())),2*Math.sin(super.getDirection()));
		if(Math.abs(ship.getCenterX() - this.getCenterX()) < 150 || Math.abs(ship.getCenterY() - this.getCenterY()) < 150)
			moveByAmount(2*Math.cos((super.getDirection() + Math.PI)),2*Math.sin(super.getDirection() + Math.PI));*/
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
					dropHp(ship.getDmg());
					//System.out.println(ship.getDmg());
					p.fizzle();
				}
			}
		}
	}
	/**
	 * drop hp
	 * @param amount amt dropped
	 */
	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	/**
	 * get hp
	 * @return hp
	 */
	public int getHp(){
		return hp;
	}

}
