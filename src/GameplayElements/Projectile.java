package GameplayElements;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import GUI.GamePanel;
/**a class representing a projectile
 * 
 * @author Garrett Cotter
 *
 */
public class Projectile extends MovingImage {

		private boolean isFizzled;
		private int dmg;
		
		
		/**Creates a new Projectile object
		 * 
		 * @param x
		 * @param y
		 * @param dir
		 */
		public Projectile(int x, int y, double dir, String img, int dmg) {
			super(img, x, y, 20, 15, dir);
			// TODO Auto-generated constructor stub
			isFizzled = false;
		}
		
		/**Acts the projectile
		 * 
		 * @param enemy the player's ship
		 */
		public void act(Ship enemy) {
			// FALL!
			if(!isFizzled) {
				moveByAmount(15*Math.cos(getDirection()-Math.PI),15*Math.sin(getDirection()-Math.PI));
				if(enemy!=null){
					if(enemy.intersects(this)){
						enemy.dropHp(dmg);
						fizzle();
					}
				}
				if (getX() < 0 || getX() > GamePanel.DRAWING_WIDTH || getY() < 0 || getY() > GamePanel.DRAWING_HEIGHT)
					fizzle(); 
			}
		}
		
		/**removes the projectile
		 * 
		 */
		public void fizzle(){
			toggleVisibility();
			isFizzled = true;
		}
		
		/**tells whether or not the projectile has been removed
		 * 
		 * @return true if the object has been removed, false if otherwise
		 */
		public boolean isFizzled(){
			return isFizzled;
		}
		
		
		/**
		 * Draws the moving image
		 * @param g graphics used to draw image
		 * @param io recives information about image as it is being updated
		 */
		public void draw(Graphics g, ImageObserver io) {
			super.draw(g, io);
		}
		
		public int getDmg(){
			return dmg;
		}
}
