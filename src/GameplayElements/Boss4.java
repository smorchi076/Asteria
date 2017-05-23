package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Boss4 extends MovingImage{

	private Projectile[] blasts = new Projectile[40];
	private int shootClock;
	private int hp, level, d;
	private int maxHp;
	private boolean isDead;
	private Boss4 mini1, mini2;
	
	
	public Boss4(int x, int y, String img, int width, int height) {
		super(img, x, y, width, height, 0);
		hp = 10;
		maxHp = 10;
		isDead = false;
		d = 1;
		level = 1;
	}
	
	
	public void draw(Graphics g, ImageObserver io) {
		if(mini1 != null) {
			mini1.draw(g, io);
			mini2.draw(g, io);
		}else{
			if(hp > 0) {
				super.draw(g, io);
		
				g.setColor(Color.RED);
				g.fillRect((int)getX(), (int)getY(), (int)getWidth(), 2);
				g.setColor(Color.GREEN);
				g.fillRect((int)getX(), (int)getY(), (int)((double)hp/maxHp * getWidth()), 2);
		
				for(int i = 0; i<blasts.length; i++){
					if(blasts[i]!=null && !blasts[i].isFizzled()){
						blasts[i].draw(g, io);
					}
				}
			}
		}
	}
	public void shoot(){
		if(hp>0) {
			if(shootClock==0){
				for(int i=0; i<blasts.length; i++){
					if(blasts[i]==null || blasts[i].isFizzled()){
						blasts[i] = new Projectile((int)(getCenterX())-10, (int)(getCenterY()), super.getDirection() + Math.PI, "resources/Bullet.png",2,20,15);
						shootClock = 30;
						break;
					}
				}	
			}
		}
	}
	
	public void act(Ship ship) {
		if(hp < 0) {
			hp = 0;
		}
		if(mini1 != null) {
			mini1.act(ship);
			mini1.shoot();
			mini2.act(ship);
			mini2.shoot();
		}
		if(!isDead) {
			super.turnToward((int)(ship.x - ship.width/2), (int)(ship.y - ship.height/2));
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
						//System.out.println(ship.getDmg());
						p.fizzle();
					}
				}
			}
		}
		if(hp <= 0 && !isDead && level < 4) {
			super.toggleVisibility();
			isDead = true;
			mini1 = new Boss4((int)super.x, (int)super.y, "resources/Boss4.png", (int)(super.width/2), (int)(super.height/2));
			mini2 = new Boss4((int)super.x, (int)super.y, "resources/Boss4.png", (int)(super.width/2), (int)(super.height/2));
			mini1.setLevel(getLevel()+1);
			mini2.setLevel(getLevel()+1);
			mini1.setHp(maxHp-level*2);
			mini2.setHp(maxHp-level*2);
			System.out.println(level);
			mini1.setD(1);
			mini2.setD(-1);
		}
		if(hp <= 0) {
			super.toggleVisibility();
			isDead = true;
		}
	}
	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	
	public int getHp(){
		if(hp < 0) {
			hp = 0;
		}
		if(mini2 == null || mini1 == null)
			return hp;
		return (mini2.getHp() + mini1.getHp());
	}
	
	
	private void setHp(int amount) {
		hp = amount;
		maxHp = amount;
	}
	private void setLevel(int l) {
		level = l;
	}
	private int getLevel() {
		return level;
	}
	
	private void setD(int num) {
		d = num;
	}
}
