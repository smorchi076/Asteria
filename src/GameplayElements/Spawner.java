package GameplayElements;

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
	
	public void act(Ship ship) {
		if(spawnTimer == 0){
			ships.add(new Ship((int)getX(), (int)getY(), "resources/spacestation.png", 40, 40, 1, 3));
			spawnTimer = spawnRate;
		}
		if(ship != null) {
			for(Projectile p : ship.getBullets()) {
				if(p!=null && p.intersects(this)) {
					dropHp(1);
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
