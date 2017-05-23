package GameplayElements;

import java.awt.geom.Rectangle2D;


/**Represents the stuff that grants $$
 * 
 * @author Garrett Cotter
 *
 */
public class SpaceJunk extends MovingImage{
	
	private boolean taken;
	
	/** Creates a new SpaceJunk object
	 * 
	 * @param img the filename for the image to be used
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public SpaceJunk(String img, int x, int y) {
		super(img, x, y, 2, 2, 0);
		taken = false;
	}
	
	
	/**Acts the SpaceJunk object
	 * 
	 * @param ship the players ship
	 */
	public void act(Ship ship) {
		if(ship.intersects(this)) {
			ship.addSpaceJunk();
			taken = true;
		}
	}
	
	
	/**
	 *	@return returns true if the ship has picked up the object, false if not
	 */
	public boolean isTaken() {
		return taken;
	}
}
