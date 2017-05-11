package GameplayElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class MovingImage extends Rectangle2D.Double {
	
private Image image;
	
	// CONSTRUCTORS
	public MovingImage(String filename, int x, int y, int w, int h) {
		this((new ImageIcon(filename)).getImage(),x,y,w,h);
	}
	
	public MovingImage(Image img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
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
		g.drawImage(image,(int)x,(int)y,(int)width,(int)height,io);
	}
	
	
}


