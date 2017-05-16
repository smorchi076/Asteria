package GameplayElements;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import GUI.GamePanel;

public class Projectile extends MovingImage {

		private boolean isFizzled;
		
		public Projectile(int x, int y, double dir) {
			super("resources/bullet.png", x, y, 20, 15, dir);
			// TODO Auto-generated constructor stub
			isFizzled = false;
		}
		public void act(Ship enemy) {
			// FALL!
			moveByAmount(15*Math.cos(getDirection()-Math.PI),15*Math.sin(getDirection()-Math.PI));
			if(enemy!=null){
				if(enemy.intersects(this)){
					enemy.dropHp(1);
					fizzle();
				}
			}
			if (getX() < 0 || getX() > GamePanel.DRAWING_WIDTH || getY() < 0 || getY() > GamePanel.DRAWING_HEIGHT)
				fizzle();
		}
		public void fizzle(){
			toggleVisibility();
			isFizzled = true;
		}
		
		public boolean isFizzled(){
			return isFizzled;
		}
		
		public void draw(Graphics g, ImageObserver io) {
			super.draw(g, io);
		}
}
