package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.*;

import GameplayElements.MovingImage;
import GameplayElements.Ship;
import GameplayElements.Spawner;

import java.util.*;


public class GamePanel extends JPanel implements Runnable
{
  public static final int DRAWING_WIDTH = 800;
  public static final int DRAWING_HEIGHT = 600;
  
  
  private Rectangle screenRect;
	
  private Ship ship;
  private ArrayList<Shape> obstacles;
  private ArrayList<Spawner> enemies;
  
  private Rectangle2D.Double visibleSpace;
  private Rectangle2D.Double characterSpace;
  
  private KeyHandler keyControl;

  public GamePanel () {
	  super();
	  
	  keyControl = new KeyHandler(); 
	  screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
	  obstacles = new ArrayList<Shape>();
	  visibleSpace = new Rectangle2D.Double(0,this.getHeight()-DRAWING_HEIGHT,DRAWING_WIDTH,DRAWING_HEIGHT);
	  characterSpace = new Rectangle2D.Double(visibleSpace.getX()+visibleSpace.getWidth()/5,visibleSpace.getY()+visibleSpace.getHeight()/5,visibleSpace.getWidth()*3/5,visibleSpace.getHeight()*3/5);
	  
	  enemies = new ArrayList<Spawner>();
	  //obstacles.add(new Rectangle(200,400,400,50));
	  //obstacles.add(new Rectangle(0,250,100,50));
	  //obstacles.add(new Rectangle(700,250,100,50));
	  //obstacles.add(new Rectangle(375,300,50,100));
	  //obstacles.add(new Rectangle(300,250,200,50));
	  enemies.add(new Spawner(DRAWING_WIDTH/2-20,50, "resources/spacestation.png", 80,80, 10, 200));
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
    g2.translate(-visibleSpace.getX(),-visibleSpace.getY());

    g.setColor(new Color(205,102,29));
    for (Shape s : obstacles) {
    	g2.fill(s);
    }
    ship.draw(g2,this);
    g.setColor(Color.RED);
	g.fillRect((int)ship.getCenterX() - DRAWING_WIDTH/2 + 10, (int)ship.getCenterY() - DRAWING_HEIGHT/2 + 10, 100, 10);
	g.setColor(Color.GREEN);
	g.fillRect((int)ship.getCenterX() - DRAWING_WIDTH/2 + 10, (int)ship.getCenterY() - DRAWING_HEIGHT/2 + 10, ship.getHp(), 10);
    for(Spawner e : enemies) {
		e.draw(g2, this);	
	}
    
    g2.setTransform(at);
    

   
    
    
    

	// TODO Add any custom drawings here
  }

  
  public void slideWorldToImage(MovingImage img) {
	  	Point2D.Double center = new Point2D.Double(img.getCenterX(), img.getCenterY());
		if (!characterSpace.contains(center)) {
			double newX = visibleSpace.getX();
			double newY = visibleSpace.getY();
			
		  	if (center.getX() < characterSpace.getX()) {
		  		newX -= (characterSpace.getX() - center.getX());
		  	} else if (center.getX() > characterSpace.getX() + characterSpace.getWidth()) {
		  		newX += (center.getX() - (characterSpace.getX() + characterSpace.getWidth()));
		  	}
		  	
		  	if (center.getY() < characterSpace.getY()) {
		  		newY -= (characterSpace.getY() - center.getY());
		  	} else if (center.getY() > characterSpace.getY() + characterSpace.getHeight()) {
		  		newY += (center.getY() - characterSpace.getY() - characterSpace.getHeight());
		  	}
		  	newX = Math.max(newX,0);
		  	newY = Math.max(newY,0);
		  	newX = Math.min(newX,this.getWidth()-visibleSpace.getWidth());
		  	newY = Math.min(newY,this.getHeight()-visibleSpace.getHeight());
		  	
		  	visibleSpace.setRect(newX,newY,visibleSpace.getWidth(),visibleSpace.getHeight());
		  	
		  	characterSpace.setRect(visibleSpace.getX()+visibleSpace.getWidth()/5,visibleSpace.getY()+visibleSpace.getHeight()/5,visibleSpace.getWidth()*3/5,visibleSpace.getHeight()*3/5);
		}
	  }
  
  public void spawnNewship() {
	  ship = new Ship(DRAWING_WIDTH/2-20,DRAWING_HEIGHT/2-30, "resources/spaceship.png", 40, 60, 100, 5, 0);
  }
  
  public KeyHandler getKeyHandler() {
	  return keyControl;
  }


  public void run() {
	while (true) { // Modify this to allow quitting
		long startTime = System.currentTimeMillis();
		
		
		if (keyControl.isPressed(KeyEvent.VK_RIGHT))
			ship.turn(ship.getDirection()+.1);
		if (keyControl.isPressed(KeyEvent.VK_LEFT))
			ship.turn(ship.getDirection()-.1);
		if(keyControl.isPressed(KeyEvent.VK_SPACE))
			ship.shoot();
		if (keyControl.isPressed(KeyEvent.VK_UP))
	  		ship.move(1);
		if(keyControl.isPressed(KeyEvent.VK_DOWN))
			ship.move(-1);
		
		
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).act(ship);
			if(enemies.get(i).getHp() == 0) {
				enemies.remove(i);
			}
			else if(enemies.get(i).intersects(ship)) {
				ship.dropHp(enemies.get(i).getHp());
				enemies.remove(i);
			}
		}
	  	ship.act(null);
	  	
	  	if (!screenRect.intersects(ship))
	  		spawnNewship();
	  	
	  	if(ship.getHp() == 0) {
	  		//INSERT ENDING HERE!!!!!!!!!
	  	}
	  	
	  	slideWorldToImage(ship);
	  	
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
		  keys = new ArrayList<Integer>(10);
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
