package GameplayElements;

import GUI.GamePanel;

public class BossProjectile extends Projectile{
	
	private int timer;
	public BossProjectile(int x, int y, double dir, String img) {
		super(x,y,dir,img,2);
		timer = 200;
	}
	
	public void act(Ship enemy) {
		if(!super.isFizzled()) {
			super.turnToward((int)(enemy.getCenterX()), (int)(enemy.getCenterY()));
			super.turn(getDirection() + Math.PI);
			moveByAmount(2*Math.cos(getDirection()-Math.PI),2*Math.sin(getDirection()-Math.PI));
			if(enemy!=null){
				if(enemy.intersects(this)){
					enemy.dropHp(super.getDmg());
					fizzle();
				}
			}
			timer--;
			if(timer == 0) {
				fizzle();
			}
			if (getX() < 0 || getX() > GamePanel.DRAWING_WIDTH || getY() < 0 || getY() > GamePanel.DRAWING_HEIGHT)
				fizzle();
		}
	}
}
