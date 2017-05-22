package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Boss7 extends MovingImage {
	
	private Projectile[] blasts = new Projectile[40];
	private int shootClock;
	private int hp;
	private int waiting;
	private double dx, dy;
	
	public Boss7(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 30;
		waiting = 50;
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
					blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + 2*Math.PI, "resources/Bullet.png");
					shootClock = 50;
					break;
				}
			}
		}
		
	}
	
	public void act(Ship ship) {
		if(this.x - ship.x > 0) {
			dx = -1;
		} else {
			dx = 1;
		}
		if(this.y - ship.y > 0) {
			dy = 1;
		} else {
			dy = -1;
		}
		if(waiting > 1) {
			waiting--;
		} else if(waiting == 1) {
			waiting = 50;
			super.moveToLocation(Math.random()*10 + getX() * dx, Math.random()*10 + getY() * dy);
		}
		super.turnToward((int)(ship.x - ship.width/2), (int)(ship.y - ship.height/2));
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
	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	
	public int getHp(){
		return hp;
	}

}