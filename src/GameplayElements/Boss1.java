package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Boss1 extends MovingImage{

	private Projectile[] blasts = new Projectile[40];
	private int shootClock;
	private int hp;
	
	public Boss1(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 30;
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
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI, "resources/Boss1Projectile.png");
					shootClock = 1;
					break;
				}
			}
		}
		
	}
	
	public void act(Ship ship) {
		double dir1 = super.getDirection();
		super.turnToward((int)((ship.getCenterX() - 30)), (int)((ship.getCenterY() - 30)));
		double dir2 = super.getDirection();
		if(Math.abs(dir1-dir2) > .01) {
			super.turn(dir1 - dir2);
		}
		double dir3 = super.getDirection();
		System.out.println(dir1 + " " + dir2 + " " + dir3);
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
}
