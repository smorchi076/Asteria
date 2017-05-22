package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Boss6 extends MovingImage {
	
	private Projectile[] blasts = new Projectile[40];
	private int shootClock;
	private int hp;
	
	public Boss6(int x, int y, String img, int width, int height) {
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
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + 2*Math.PI, "resources/Boss3Projectile.png");
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI, "resources/Boss3Projectile.png");
					shootClock = 10;
					break;
				}
			}
		}
		
	}
	
	public void act(Ship ship) {
		super.turnToward((int)((ship.getCenterX() - ship.width/2)), (int)((ship.getCenterY() - ship.height/2)));
		if(shootClock>0){
			shootClock--;
			moveByAmount(Math.random()*360,Math.random()*360);
		}
		
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
	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	
	public int getHp(){
		return hp;
	}

}
