package GameplayElements;

import java.awt.geom.Rectangle2D;

public class SpaceJunk extends MovingImage{
	
	private boolean taken;
	public SpaceJunk(String img, int x, int y) {
		super(img, x, y, 2, 2, 0);
		taken = false;
	}
	
	public void act(Ship ship) {
		if(ship.intersects(this)) {
			ship.addSpaceJunk();
			taken = true;
		}
	}
	
	public boolean isTaken() {
		return taken;
	}
}
