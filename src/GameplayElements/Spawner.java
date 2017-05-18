package GameplayElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Spawner extends MovingImage {
	
	private int hp;
	private int spawnRate;
	private int spawnTimer;
	private ArrayList<Ship> ships;
	
	public Spawner(int x, int y, String img, int width, int height, int hp, int rateOfSpawn) {
		super(img, x, y, width, height, 0);
		this.hp = hp;
		spawnRate = rateOfSpawn;
		spawnTimer = 0;
		ships = new ArrayList<Ship>();
	}
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
	
	
	public void act(Ship ship) {
		
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

	public void dropHp(int amount)
	{
		hp = hp - amount;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int amount) {
		hp = amount;
	}
	
	public ArrayList<Ship> getShips() {
		return ships;
	}
	
	public void remove(int index) {
		ships.remove(index);
	}
}
