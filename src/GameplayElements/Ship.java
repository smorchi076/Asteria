package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import GUI.ShopPanel;
/**A class modeling a ship object
 * 
 * @author Garrett Cotter
 *
 */
public class Ship extends MovingImage {
	
	private double vY;
	private int shootClock, hp;
	private Projectile[] blasts = new Projectile[20];
	private int willSlow;
	private int speed;
	private int sj;
	private int invul;
	private int dmg;
	private int shield;
	private int maxShield;
	private int rof;
	private int dmgTaken;
	private int upgrades[] = {0,0,0,0,0};
	private final int STARTING_HP = 100;
	private final int STARTING_SPEED = 5;
	private final int STARTING_DAMAGE = 1;
	private final int STARTING_SHIELD = 0;
	private final int STARTING_ROF = 0;
	private static int kills = 0;
	
	/**Creates a new ship object
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param img the filename for the image to be used
	 * @param width the width of the ship
	 * @param height the height of the ship
	 * @param hp the hp of the ship
	 * @param maxSpeed the maximum speed of the ship
	 * @param dir the starting direction of the ship
	 */
	public Ship(int x, int y, String img, int width, int height, int hp, int maxSpeed, double dir) {
		super(img, x, y, width, height, 0);
		//setUpgrades(upgrades);
		vY = 0;
		willSlow = 0;
		this.hp = hp;
		rof= 0; 
		speed = maxSpeed;
		sj = 0;
		invul = 0;
		dmgTaken = 0;
		System.out.println("test");
	}

	

	// METHODS
	/**Increases the velocity of the ship
	 * 
	 * @param dir the direction of the velocity, positive for forward, negative for backwards
	 */
	public void move(double dir) {
		// JUMP!
		if(vY > -speed && vY <= 0) {
			if(dir < 0 && vY <= .1)
				vY -= dir*.1;
				
				
			if(dir > 0)
				vY -= dir*.1;
			
		}
		
	}
	/**
	 * Draws the moving image
	 * @param g graphics used to draw image
	 * @param io receives information about image as it is being updated
	 */
	public void draw(Graphics g, ImageObserver io) {
		super.draw(g, io);
		
		
		for(int i = 0; i<blasts.length; i++){
			if(blasts[i]!=null && !blasts[i].isFizzled()){
				blasts[i].draw(g, io);
			}
		}
	}
		
		
	/**Acts the ship
	 * 
	 * @param ship the player's ship
	 */
	public void act(Ship ship) {
		if(dmgTaken == 0 && shield < maxShield) {
			shield += 1;
		}
		if(invul > 0)
			invul--;
		double dir = getDirection();
		moveByAmount(1*vY*Math.cos(dir),1*vY*Math.sin(dir));
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
					dropHp(dmg + STARTING_DAMAGE);
					ship.setDmgTaken();
					p.fizzle();
				}
			}
		}
		if(vY > .2 && willSlow == 0) {
			vY -= vY/5.0 + .1;
		} else if(vY < -.1 && willSlow == 0) {
			vY -= vY/5.0 - .1;
		} else if(vY<.1 && vY>-.1) {
			vY = 0;
		} else if(vY >= 0) {
			vY = 0;
		}
		if(willSlow == 0)
			willSlow = 50;
		willSlow--;
	}
	
	/**tells the ship to shoot
	 * 
	 */
	public void shoot(){
		if(shootClock==0){
			for(int i=0; i<blasts.length; i++){
				if(blasts[i]==null || blasts[i].isFizzled()){
					blasts[i] = new Projectile((int)(getX()), (int)(getY()+getHeight()/2 - 5), super.getDirection(), "resources/bullet.png");
					shootClock = 20-rof;
					//System.out.println(rof);
					break;
				}
			}
		}
		
	}
	
	
	public void setDmgTaken() {
		dmgTaken = 100;
	}
	
	/**reduces the hp of the ship
	 * 
	 * @param amount the amount to reduce the hp by
	 */
	public void dropHp(int amount)
	{
		if(shield == 0){
			if(invul == 0) {
				hp = hp - amount;
				invul = 10;
			}
		}
		dropShield(amount);
	}
	
	/**
	 * 
	 * @return the current hp of the ship
	 */
	public int getHp() {
		return hp;
	}

	/**sets the hp of the ship
	 * 
	 * @param amount the amount to be set to 
	 */
	public void setHp(int amount) {
		hp = amount;
	}
	
	public void dropShield(int amount)
	{
		if(invul == 0) {
			shield = shield - amount;
			invul = 10;
		}
		if(shield < 0) {
			dropHp(0-shield);
			shield = 0;
		}
	}
	
	public int getShield()
	{
		return shield;
	}
	
	/**
	 * 
	 * @return the projectiles of this object
	 */
	public Projectile[] getBullets() {
		return blasts;
	}
	
	/**increases the money of the ship by one
	 * 
	 */
	public void addSpaceJunk() {
		sj++;
	}
	
	/**
	 * 
	 * @return the amount of money the ship has
	 */
	public int getSpaceJunk() {
		return sj;
	}
	/**
	 * Sets the upgrades of the ship
	 * @param u
	 */
	public void setUpgrades(int[] u){
		u = ShopPanel.getUpgrades();
		for(int i = 0; i < u.length; i++){
			if(i == 0){
				hp = STARTING_HP + (u[0]*2);
				//System.out.print(hp);
			}
			if(i == 1){
				speed = STARTING_SPEED + (u[1]*2);
				//System.out.print(speed);
			}
			if(i == 2){
				dmg = STARTING_DAMAGE + (u[2]*2);
				//System.out.print(dmg);
			}
			if(i == 3){
				maxShield = STARTING_SHIELD + (u[3]*2);
				//System.out.print(dmg);
			}
			if(i == 4){
				rof = STARTING_ROF + (u[4]*2);
				System.out.print(rof);
			}
		}
	}
}
