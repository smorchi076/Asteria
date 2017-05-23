package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
/**
 * Represents boss 5
 * @author morchi
 *
 */
public class Boss5 extends MovingImage{

	private Projectile[] blasts = new Projectile[40];
	private int shootClock;
	private int hp;
	/**
	 * Creates boss 5
	 * @param x x coord
	 * @param y y coord
	 * @param img image
	 * @param width width
	 * @param height height
	 */
	public Boss5(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 30;
	}
	
	/**
	 * Draws
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
	  * Shoots
	  */
	public void shoot(){
		if(shootClock==0){
			for(int i=0; i<blasts.length; i++){
				if(blasts[i]==null || blasts[i].isFizzled()){
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI, "resources/Boss3Projectile.png", 2,20,15);
					shootClock = 1;
					break;
				}
			}
		}
		
	}
	
	/**
	 * Movement of boss 5
	 * @param ship ship
	 */
	public void act(Ship ship) {
		super.turnToward((int)((ship.getCenterX() - ship.width/2)), (int)((ship.getCenterY() - ship.height/2)));
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
