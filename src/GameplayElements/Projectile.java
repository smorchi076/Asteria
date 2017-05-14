package GameplayElements;

import GUI.GamePanel;

public class Projectile extends MovingImage {

		private boolean isFizzled;
		private double dir;
		
		public Projectile(int x, int y, double dir) {
			super("resources/bullet.png", x, y, 20, 15);
			// TODO Auto-generated constructor stub
			isFizzled = false;
			this.dir = dir;
		}
		public void act(Ship enemy) {
			// FALL!
			moveByAmount(15*Math.cos(dir-Math.PI),15*Math.sin(dir-Math.PI));
			//if(enemy.isPointInImage(getX()+4, getY()+3)){
				//enemy.dropHp();
			//}
			
			if (getX() < 0 || getX() > GamePanel.DRAWING_WIDTH || getY() < 0 || getY() > GamePanel.DRAWING_HEIGHT)
				fizzle();
		}
		private void fizzle(){
			toggleVisibility();
			isFizzled = true;
		}
		
		public boolean isFizzled(){
			return isFizzled;
		}
}
