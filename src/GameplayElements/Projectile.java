package GameplayElements;


public class Projectile extends MovingImage {

		private boolean isFizzled;
		private int dir;
		
		public Projectile(int x, int y, int dir) {
			super("lightingbolt.png", x, y, 40, 30);
			// TODO Auto-generated constructor stub
			isFizzled = false;
			this.dir = dir;
		}
		public void act(Ship enemy) {
			// FALL!
			moveByAmount((int)12*dir, 0);
			if(enemy.isPointInImage(getX()+4, getY()+3)){
				//enemy.dropHp();
			}
			//if (getX() < 0 || getX() > GamePanel.DRAWING_WIDTH || getY() < 0 || getY() > Asteria.DRAWING_HEIGHT)
				fizzle();
		}
		private void fizzle(){
			//this = null;
		}
		
		public boolean isFizzled(){
			return isFizzled;
		}
}
