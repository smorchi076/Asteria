package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Boss2 extends MovingImage{

	private Projectile[] blasts = new Projectile[80];
	private int hp;
	private int shooting, waiting, moving;
	
	public Boss2(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 30;
		shooting = 0;
		waiting = 500;
		moving = 0;
	}
	
	
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
		
		g.setColor(Color.RED);
		g.fillRect((int)getX(), (int)getY(), (int)getWidth(), 2);
		g.setColor(Color.GREEN);
		if(waiting != 0){
			g.setColor(Color.yellow);
		}
		g.fillRect((int)getX(), (int)getY(), (int)(hp/30.0 * getWidth()), 2);
		
		for(int i = 0; i<blasts.length; i++){
			if(blasts[i]!=null && !blasts[i].isFizzled()){
				blasts[i].draw(g, io);
				
			}
		}
	}
	public void shoot(){
		if(shooting>0){
			for(int i=0; i<blasts.length; i++){
				if(blasts[i]==null || blasts[i].isFizzled()){
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI + (Math.random()*Math.PI/2 - Math.PI/4), "resources/Bullet.png");
					break;
				}
			}
		}
		
	}
	
	public void act(Ship ship) {
		if(waiting > 1) {
			waiting--;
			moveByAmount(1*Math.cos((super.getDirection()+ Math.PI/2) * (waiting/500.0*(Math.PI*2))),1*Math.sin((super.getDirection()+ Math.PI/2 * (waiting/500.0*(Math.PI*2)))));
		}
		else if(moving > 1) {
			moving--;
			moveByAmount(15*Math.cos((super.getDirection())),15*Math.sin(super.getDirection()));
		}
		else if(shooting > 1) {
			shooting--;
		}
		else if(waiting == 1) {
			super.turnToward((int)ship.getCenterX(), (int)ship.getCenterY());
			waiting = 0;
			moving = 20;
		} else if(moving == 1) {
			super.turnToward((int)ship.getCenterX(), (int)ship.getCenterY());
			moving = 0;
			shooting = 200;
		} else if(shooting == 1) {
			shooting = 0;
			waiting = 500;
		}
		for(int i = 0; i<blasts.length; i++){
			if(blasts[i]!=null){
				blasts[i].act(ship);
			}
		}
		if(waiting == 0) {
			if(ship != null) {
				for(Projectile p : ship.getBullets()) {
					if(p!=null && p.intersects(this) && !p.isFizzled()) {
						dropHp(1);
						p.fizzle();
					}
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
