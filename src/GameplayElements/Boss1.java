package GameplayElements;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Boss1 extends MovingImage{

	private Projectile[] blasts = new Projectile[20];
	private int shootClock;
	
	public Boss1(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
	}
	
	
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
		
		
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
					blasts[i] = new Projectile((int)(getX()), (int)(getY()+getHeight()/2 - 5), super.getDirection(), "resources/Boss1Projectile.png");
					shootClock = 2;
					break;
				}
			}
		}
		
	}
	
	public void act(Ship ship) {
		super.turnToward((int)ship.getCenterX(), (int)ship.getCenterY());
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
					ship.dropHp(1);
					p.fizzle();
				}
			}
		}
	}
}
