package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
/**
 * Represents first boss
 * @author morchi
 *
 */
public class Boss1 extends MovingImage{

	private BossProjectile[] blasts = new BossProjectile[40];
	private int shootClock;
	private int hp;
	private int movingLeft, movingRight, waiting;
	/**
	 * Boss 1
	 * @param x x coord
	 * @param y y coord
	 * @param img image
	 * @param width width
	 * @param height height
	 */
	public Boss1(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 30;
		movingLeft = 100;
		movingRight = 0;
		waiting = 0;
	}
	
	/**
	 * Draws on graphics g
	 */
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
	/**
	 * shoots
	 */
	public void shoot(){
		if(shootClock==0){
			for(int i=0; i<blasts.length; i++){
				if(blasts[i]==null || blasts[i].isFizzled()){
					blasts[i] = new BossProjectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI, "resources/Bullet.png");
					shootClock = 100;
					break;
				}
			}
		}
		
	}
	
	/**
	 * Movement of boss 1
	 * @param ship ship
	 */
	public void act(Ship ship) {
		super.turnToward((int)ship.getCenterX(), (int)ship.getCenterY());
		if(Math.abs(ship.getCenterX() - this.getCenterX()) > 300 || Math.abs(ship.getCenterY() - this.getCenterY()) > 300)
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
					dropHp(ship.getDmg());
					//System.out.println(ship.getDmg());
					p.fizzle();
				}
			}
		}
	}
	
	/**
	 * drops hp
	 * @param amount hp drop
	 */
	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	/**
	 * Gets hp
	 * @return hp
	 */
	public int getHp(){
		return hp;
	}
}
