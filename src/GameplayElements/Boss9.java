package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Boss9 extends MovingImage {
	
	private Projectile[] blasts = new Projectile[40];
	private int shootClock;
	private int hp;
	
	
	public Boss9(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 30;
		shootClock = 150;
	}
	
	
 	
	
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);

		g.setColor(Color.RED);
		g.fillRect((int)getX(), (int)getY(), (int)getWidth(), 2);
		g.setColor(Color.GREEN);
		g.fillRect((int)getX(), (int)getY(), (int)((double)hp/30 * getWidth()), 2);

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
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI, "resources/Bullet.png",30,40,30);
					shootClock = 150;
					break;
				}
			}	
		}
	}
	
	public void act(Ship ship) {
		super.turnToward((int)(ship.x - ship.width/2), (int)(ship.y - ship.height/2));
		moveByAmount(.5*Math.cos((super.getDirection())),.5*Math.sin(super.getDirection()));
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
	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	
	public int getHp(){
		return hp;
	}
}
