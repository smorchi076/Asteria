package GameplayElements;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


/**
 * Represents a moving image in the game
 * @author Morchi
 *
 */
public class MovingImage extends Rectangle2D.Double {
	
private Image image;
private double dir;
private boolean isVisible;

	// CONSTRUCTORS
/**Creates an instance of a moving image that takes a filename and direction
 * 
 * @param filename filename of image
 * @param x x coordinate of image
 * @param y y coordinate of image
 * @param w width of image
 * @param h height of image
 * @param dir direction of image
 */
	public MovingImage(String filename, int x, int y, int w, int h, double dir) {
		this((new ImageIcon(filename)).getImage(),x,y,w,h);
		this.dir = dir;
		isVisible = true;
	}
	
	/**
	 * Creates an instance of a moving image that takes in a image and no direction
	 * @param img image of the moving image
	 * @param x x coordinate of a image
	 * @param y y coordinate  of a image
	 * @param w width of image
	 * @param h height of image
	 */
	public MovingImage(Image img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
		isVisible = true;
	}
	
	/**
	 * Changes visibility of the moving image
	 */
	public void toggleVisibility() {
		isVisible = !isVisible;
	}
	// METHODS	
	/**
	 * moves image to specific location
	 * @param x new x coordinate of image
	 * @param y new y coordinate of image
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	/**
	 * moves image by a specific increment
	 * @param x increment in x direction
	 * @param y increment in y direction
	 */
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	/**
	 * Applies Window limits
	 * @param windowWidth window width
	 * @param windowHeight window height
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Draws the moving image
	 * @param g graphics used to draw image
	 * @param io recives information about image as it is being updated
	 */
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
	
	/**
	 * Checks to see if point is within the bound sof the image
	 * @param mouseX x coordinate of point
	 * @param mouseY y coordinate of point
	 * @return true if point is in image, false otherwise
	 */
	public boolean isPointInImage(double mouseX, double mouseY) {
		if (mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height)
			return true;
		return false;
	}
	
	/**
	 * Turns the image to a certain direction
	 * @param dir direction to be turned
	 */
	public void turn(double dir) {
		this.dir = dir;
	}
	
	/**
	 * Turns the image to a certain coordinate point
	 * @param x
	 * @param y
	 */
	public void turnToward(int x, int y) {
		dir = Math.atan(((double)this.y-y)/(this.x-x));
		if (this.x > x)
			dir += Math.PI;
	}
	
	/**
	 * Gets the direction of the image
	 * @return image direction
	 */
	public double getDirection() {
		return dir;
	}
	
	/**Returns if the image is visible or not
	 * 
	 * @return whether the image is visible or not
	 */
	public boolean isVisible() {
		return isVisible;
	}
	
	public void moveInLimits(Rectangle2D.Double limits, double x, double y) {
		double newX = this.x + x;
		double newY = this.y + y;
		if (limits.contains(new Rectangle2D.Double(newX,newY,width,height))) {
			this.x = newX;
			this.y = newY;
		}
	}
	
}


