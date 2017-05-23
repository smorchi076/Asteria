package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * Represents Boss 8
 * @author Cotter
 *
 */
public class Boss8 extends MovingImage{

	private Projectile[] blasts = new Projectile[40];
	private int shootClock;
	private int hp, waiting;
	private double d,x,y;
	
	/**
	 * Creates boss 8
	 * @param x x coord
	 * @param y y coord
	 * @param img image
	 * @param width width
	 * @param height height
	 */
	public Boss8(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 15;
		waiting = 0;
		d = 1;
	}
	
	/**
	 * Draws
	 */
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);

		g.setColor(Color.RED);
		g.fillRect((int)getX(), (int)getY(), (int)getWidth(), 2);
		g.setColor(Color.GREEN);
		g.fillRect((int)getX(), (int)getY(), (int)((double)hp/15 * getWidth()), 2);

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
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI, "resources/Bullet.png", 3,20,15);
					shootClock = 15;
					break;
				}
			}	
		}
	}
	/**
	 * Movement of boss 8
	 * @param ship ship
	 */
	public void act(Ship ship) {
		super.turnToward((int)(ship.x - ship.width/2), (int)(ship.y - ship.height/2));
		if(waiting > 0) {
			waiting--;
		} else if(waiting == 0) {
			d = (Math.random() * 5 - 2.5);
			if(d >= -1 && d <= 0) {
				d = -1;
			} else if(d <= 1 && d >= 0) {
				d = 1;
			}
			waiting = 300;
		}
		if(x == super.x && y == super.y) {
			d = -d;
		}
		x = super.x;
		y = super.y;
		if(Math.abs(ship.getCenterX() - this.getCenterX()) > 200 || Math.abs(ship.getCenterY() - this.getCenterY()) > 200)
			moveByAmount(2*Math.cos((super.getDirection())),2*Math.sin(super.getDirection()));
		if(Math.abs(ship.getCenterX() - this.getCenterX()) < 150 || Math.abs(ship.getCenterY() - this.getCenterY()) < 150)
			moveByAmount(2*Math.cos((super.getDirection() + Math.PI)),2*Math.sin(super.getDirection() + Math.PI));
		moveByAmount(2*Math.cos((super.getDirection() + Math.PI/2 * d)),2*Math.sin(super.getDirection()+Math.PI/2 * d));
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
