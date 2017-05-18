package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
/**Models a spawner object that creates other ships
 * 
 * @author Garrett Cotter
 *
 */
public class Spawner extends MovingImage {
	
	private int hp;
	private int spawnRate;
	private int spawnTimer;
	private ArrayList<Ship> ships;
	private int invul;
	
	/**Creates a new spawner object
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param img the filename for the image to be used
	 * @param width the width of the spawner
	 * @param height the height of the spawner
	 * @param hp the hp of the spawner
	 * @param rateOfSpawn the speed at which enemies are spawned
	 */
	public Spawner(int x, int y, String img, int width, int height, int hp, int rateOfSpawn) {
		super(img, x, y, width, height, 0);
		this.hp = hp;
		spawnRate = rateOfSpawn;
		spawnTimer = 0;
		ships = new ArrayList<Ship>();
		invul = 0;
	}
	
	/**Draws the spawner
	 * @param g graphics used to draw image
	 * @param io recives information about image as it is being updated
	 */
	public void draw(Graphics g, ImageObserver io) {
		
		super.draw(g, io);
		g.setColor(Color.RED);
		g.fillRect((int)getX(), (int)getY(), (int)getWidth(), 2);
		g.setColor(Color.GREEN);
		g.fillRect((int)getX(), (int)getY(), (int)(hp/10.0 * getWidth()), 2);
		
		for(int i = 0; i<ships.size(); i++){
			ships.get(i).draw(g, io);
		}
		
	}
	
	/**acts the spawner and all of the ships it has spawned
	 * 
	 * @param ship the players ship
	 */
	public void act(Ship ship) {
		if(invul > 0)
			invul--;
		if(spawnTimer == 0){
			ships.add(new Ship((int)getX(), (int)getY(), "resources/spacestation.png", 40, 40, 1, 3, 0));
			ships.get(ships.size()-1).turnToward((int)(ship.getX()), (int)(ship.getY()));
			ships.get(ships.size()-1).turn(ships.get(ships.size()-1).getDirection() + Math.PI);
			spawnTimer = spawnRate;
		}
		for(int j = 0; j < ships.size(); j++){
			ships.get(j).shoot();
			ships.get(j).turnToward((int)(ship.getX()), (int)(ship.getY()));
			ships.get(j).turn(ships.get(j).getDirection() + Math.PI);
			ships.get(j).move(1);
			if(ships.get(j).getHp() == 0) {
				ships.remove(j);
			}
			else if(ships.get(j).intersects(ship)) {
				ship.dropHp(ships.get(j).getHp());
				ships.remove(j);
				
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
		spawnTimer--;
		for(Ship s : ships) {
			s.act(ship);
		}
	}
	/**reduces the spawner's hp by that amount
	 * 
	 * @param amount the amount of hp to be lost
	 */
	public void dropHp(int amount)
	{
		if(invul == 0) {
			hp = hp - amount;
			invul = 10;
		}
	}
	
	
	/**
	 * 
	 * @return the spawner's hp
	 */
	public int getHp() {
		return hp;
	}
	/**sets the spawners hp
	 * 
	 * @param amount the amount to be set to
	 */
	public void setHp(int amount) {
		hp = amount;
	}
	/**
	 * 
	 * @return all of the ships this spawner has spawned
	 */
	public ArrayList<Ship> getShips() {
		return ships;
	}
}
