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
	//private int upgrades[] = {0,0,0,0,0};
	private final int STARTING_HP = 100;
	private final int STARTING_SPEED = 5;
	private final int STARTING_DAMAGE = 1;
	private final int STARTING_SHIELD = 0;
	private final int STARTING_ROF = 0;
	private int twoDirections;
	private int rapid;
	private int maxHp;
	private int cd1,cd2,cd3;
	
	private static int money = 500;
	private final int STARTING_MONEY = 500; 
	
	
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
	public Ship(int x, int y, String img, int width, int height, int hp, int maxSpeed, double dir, int[] u) {
		super(img, x, y, width, height, 0);
		//setUpgrades(upgrades);
		vY = 0;
		willSlow = 0;
		this.hp = hp;
		//rof= 0; 
		speed = maxSpeed;
		sj = 0;
		invul = 0;
		dmgTaken = 0;
		twoDirections = 0;
		rapid = 0;
		//maxShield = 0;
		shield = 0;
		maxHp = 100;
		cd1 = 0;
		cd2 = 0;
		cd3 = 0;
		if(u != null) {
			setUpgrades(u);
		}
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
		
		if(cd1 > 0) {
			g.setColor(Color.ORANGE);
			g.fillRect((int) super.x - 30,(int) super.y-31, 100, 1);
			g.setColor(Color.BLUE);
			g.fillRect((int) super.x - 30,(int) super.getY()-31, (int)((double)cd1/1000*100), 1);
		}
		if(cd2 > 0) {
			g.setColor(Color.ORANGE);
			g.fillRect((int) super.x - 30,(int) super.y-29, 100, 1);
			g.setColor(Color.BLUE);
			g.fillRect((int) super.x - 30,(int) super.getY()-29, (int)((double)cd2/1000*100), 1);
		}
		if(cd3 > 0) {
			g.setColor(Color.ORANGE);
			g.fillRect((int) super.x - 30,(int) super.y-27, 100, 1);
			g.setColor(Color.BLUE);
			g.fillRect((int) super.x - 30,(int) super.getY()-27, (int)((double)cd3/1000*100), 1);
		}
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
		if(cd1 > 0) {
			cd1--;
		}
		if(cd2 > 0) {
			cd2--;
			shoot();
		} 
		if(cd3 > 0) {
			cd3--;
			shoot();
		}
		if(dmgTaken == 0 && shield < maxShield) {
			shield += 1;
		}
		if(dmgTaken > 0) {
			dmgTaken--;
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
					dropHp(ship.getDmg());
					//System.out.println(ship.getDmg());
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
		if(twoDirections > 0) {
			twoDirections--;
		}
	}
	
	/**tells the ship to shoot
	 * 
	 */
	public void shoot(){
		if(twoDirections == 0) {
			if(shootClock==0){
				for(int i=0; i<blasts.length; i++){
					if(blasts[i]==null || blasts[i].isFizzled()){
						blasts[i] = new Projectile((int)(getX()), (int)(getY()+getHeight()/2 - 5), super.getDirection(), "resources/bullet.png",getDmg(),20,15);
						shootClock = 20-rof;
						if(rapid > 0) {
							shootClock = 1;
							rapid--;
						}
						break;
					}
				}
			}
		} else {
			if(shootClock==0){
				for(int i=0; i<blasts.length-1; i+=2){
					if(blasts[i]==null || blasts[i].isFizzled()){
						int x1 = (int)(x + width / 2 + Math.cos(getDirection() + Math.PI*26/25) * height);
						int y1 = (int)(y + height / 2 + Math.sin(getDirection() + Math.PI*26/25) * height);
						int x2 = (int)(x + width / 2 + Math.cos(getDirection() + Math.PI*24/25) * height);
						int y2 = (int)(y + height / 2 + Math.sin(getDirection() + Math.PI*24/25) * height);
						blasts[i] = new Projectile(x1, y1, super.getDirection(), "resources/bullet.png",getDmg(),20,15);
						blasts[i+1] = new Projectile(x2, y2, super.getDirection(), "resources/bullet.png",getDmg(),20,15);
						shootClock = 20-rof;
						if(rapid > 0) {
							shootClock = 1;
							rapid--;
						}
						break;
					}
				}
			}
		}
	}
	public void addMoney(int amount) {
		 money += amount;
	}
	
	public int getMoney() {
		return money;
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
		setDmgTaken();
		if(shield <= 0){
			if(invul == 0) {
				hp = hp - amount;
				invul = 10;
			}
		} else {
			dropShield(amount);
		}
	}
	
	
	/**
	 * 
	 * @return the current hp of the ship
	 */
	public int getHp() {
		return hp;
	}
	
	public int getMaxHp() {
		return maxHp;
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
		if(shield <= 0) {
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
	
	public int getInvulnerabilityLength() {
		return invul;
	}
	
	public int getDmg() {
		return dmg + STARTING_DAMAGE;
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
	
	
	public int getMaxShield(){
		return maxShield;
	}
	/**
	 * Sets the upgrades of the ship
	 * @param u
	 */
	public void setUpgrades(int[] u){
		for(int i = 0; i < u.length; i++){
			if(i == 0){
				hp += (u[0]*20);
				maxHp = STARTING_HP + (u[0]*20);
				//System.out.print(hp);
			}
			if(i == 1){
				speed = STARTING_SPEED + (u[1]*2);
				//System.out.print(speed);
			}
			if(i == 2){
				dmg = (u[2]);
				//System.out.print(dmg);
			}
			if(i == 3){
				maxShield = STARTING_SHIELD + (u[3]*5);
				//System.out.print(dmg);
			}
			if(i == 4){
				rof = STARTING_ROF + (u[4]*2);
			}
		}
	}
	/**
	 * Invincibilty 
	 */
	public void abilityOne() {
		if(cd1 == 0) {
			invul = 100;
			cd1 = 1000;
		}
	}
	/**
	 * Double shot
	 */
	public void abilityTwo() {
		if(cd2 == 0) {
			twoDirections = 100;
			cd2 = 1000;
		}
	}
	
	/**
	 * Rapid fire
	 */
	public void abilityThree() {
		if(cd3 == 0) {
			rapid = 30;
			cd3 = 1000;
		}
	}


/**
 * Returns starting cash
 * @return starting money
 */
	public  int getStartingMoney() {
		return STARTING_MONEY;
	}
}
