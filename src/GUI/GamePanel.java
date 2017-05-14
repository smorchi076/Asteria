package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;

import GameplayElements.Ship;


import java.util.*;


public class GamePanel extends JPanel implements Runnable
{
  public static final int DRAWING_WIDTH = 800;
  public static final int DRAWING_HEIGHT = 600;
  
  private Rectangle screenRect;
	
  private Ship ship;
  private ArrayList<Shape> obstacles;
  
  private KeyHandler keyControl;

  public GamePanel () {
	  super();
	  
	  keyControl = new KeyHandler(); 
	  screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
	  obstacles = new ArrayList<Shape>();
	  //obstacles.add(new Rectangle(200,400,400,50));
	  //obstacles.add(new Rectangle(0,250,100,50));
	  //obstacles.add(new Rectangle(700,250,100,50));
	  //obstacles.add(new Rectangle(375,300,50,100));
	  //obstacles.add(new Rectangle(300,250,200,50));
	  spawnNewship();
	  new Thread(this).start();
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background
    
    
    BufferedImage scaledImage = getScaledImage();
    g.drawImage(scaledImage,0,0,null);
    
	Graphics2D g2 = (Graphics2D) g;
	
    int width = getWidth();
    int height = getHeight();
    
    double ratioX = (double)width/DRAWING_WIDTH;
    double ratioY = (double)height/DRAWING_HEIGHT;
    
    AffineTransform at = g2.getTransform();
    g2.scale(ratioX, ratioY);

    g.setColor(new Color(205,102,29));
    for (Shape s : obstacles) {
    	g2.fill(s);
    }
    ship.draw(g2,this);
    
    
    g2.setTransform(at);
    
   
    
    
    

	// TODO Add any custom drawings here
  }

  
  public void spawnNewship() {
	  ship = new Ship(DRAWING_WIDTH/2-Ship.SHIP_WIDTH/2,50);
  }
  
  public KeyHandler getKeyHandler() {
	  return keyControl;
  }


  public void run() {
	while (true) { // Modify this to allow quitting
		long startTime = System.currentTimeMillis();
		
		if (keyControl.isPressed(KeyEvent.VK_LEFT))
			ship.turn(ship.getDirection()-.1);
		if (keyControl.isPressed(KeyEvent.VK_RIGHT))
			ship.turn(ship.getDirection()+.1);
		if (keyControl.isPressed(KeyEvent.VK_UP))
	  		ship.move(1);
		if(keyControl.isPressed(KeyEvent.VK_DOWN))
			ship.move(-1);
		if(keyControl.isPressed(KeyEvent.VK_SPACE))
			ship.shoot();
	
	  	ship.act(obstacles);
	  	
	  	if (!screenRect.intersects(ship))
	  		spawnNewship();
	  	
	  	repaint();
	  	
	  	long waitTime = 17 - (System.currentTimeMillis()-startTime);
	  	try {
	  		if (waitTime > 0)
	  			Thread.sleep(waitTime);
	  		else
	  			Thread.yield();
	  	} catch (InterruptedException e) {}
	}
  }
  


  public class KeyHandler implements KeyListener {

	  private ArrayList<Integer> keys;

	  public KeyHandler() {
		  keys = new ArrayList<Integer>();
	  }

	  public void keyPressed(KeyEvent e) {
		  keys.add(e.getKeyCode());
	  }

	  public void keyReleased(KeyEvent e) {
		  Integer code = e.getKeyCode();
		  while(keys.contains(code))
			  keys.remove(code);
	  }

	  public void keyTyped(KeyEvent e) {

	  }
	  
	  public boolean isPressed(int code) {
		  return keys.contains(code);
	  }
  }
  
	private BufferedImage getScaledImage(){
		ImageIcon backImage = new ImageIcon("resources/background.png");
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) image.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(backImage.getImage(), 0, 0,getWidth(),getHeight(), null);

		return image;
	}


}
