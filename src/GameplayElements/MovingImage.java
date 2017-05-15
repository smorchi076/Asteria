package GameplayElements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;



public class MovingImage extends Rectangle2D.Double {
	
private Image image;
private double dir;
private boolean isVisible;

	// CONSTRUCTORS
	public MovingImage(String filename, int x, int y, int w, int h, double dir) {
		this((new ImageIcon(filename)).getImage(),x,y,w,h);
		this.dir = dir;
		isVisible = true;
	}
	
	public MovingImage(Image img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
		isVisible = true;
	}
	
	public void toggleVisibility() {
		isVisible = !isVisible;
	}
	// METHODS	
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	
	public void draw(Graphics g, ImageObserver io) {
		if(isVisible) {
			Graphics2D g2 = (Graphics2D)g;
			AffineTransform at = g2.getTransform();
			g2.translate(x+width/2, y+height/2);
			g2.rotate(dir-Math.PI/2);
			g.drawImage(image,(int)(-width/2),(int)(-height/2),(int)width,(int)height,io);
			g2.setTransform(at);
		}
	}
	
	public boolean isPointInImage(double mouseX, double mouseY) {
		if (mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height)
			return true;
		return false;
	}
	
	public void turn(double dir) {
		this.dir = dir;
	}
	
	public void turnToward(int x, int y) {
		dir = Math.atan(((double)this.y-y)/(this.x-x));
		if (this.x > x)
			dir += Math.PI;
	}
	
	public double getDirection() {
		return dir;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
}


