package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.*;

import GameplayElements.Boss1;
import GameplayElements.Boss10;
import GameplayElements.Boss2;
import GameplayElements.Boss3;
import GameplayElements.Boss4;
import GameplayElements.Boss5;
import GameplayElements.Boss6;
import GameplayElements.Boss7;
import GameplayElements.Boss8;
import GameplayElements.Boss9;
import GameplayElements.MovingImage;
import GameplayElements.Ship;
import GameplayElements.Spawner;
import Main.Asteria;

import java.util.*;

/**
 * Represents the game world
 * @author Morchi
 *
 */
public class GamePanel extends JPanel implements Runnable
{
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	public static final int ASSUMED_DRAWING_WIDTH = 400; // These numbers are way too small
	public static final int ASSUMED_DRAWING_HEIGHT = 300; // We are only using them to zoom in on the scene


	Asteria game;
	private Rectangle screenRect;

	private boolean rightKey, leftKey, upKey, downKey;

	private Ship ship;
	private ArrayList<Shape> obstacles;
	private ArrayList<Spawner> enemies;
	private Boss1 boss1;
	private Boss2 boss2;
	private Boss3 boss3;
	private Boss4 boss4;
	private Boss5 boss5;
	private Boss6 boss6;
	private Boss7 boss7;
	private Boss8 boss8;
	private Boss9 boss9;
	private Boss10 boss10;
	private boolean isOver;
	private int level;
	private int[] u;

	//private boolean running;



	private Rectangle2D.Double visibleSpace;
	private Rectangle2D.Double characterSpace;

	private double ratioX;
	private double ratioY;
	private double scale = 0.5;

	private KeyHandler keyControl;

	/**
	 * Creates a default instance of the game panel
	 */
	public GamePanel (Asteria game) {
		super();

		this.game = game;
		setLevel(1);
		keyControl = new KeyHandler(); 
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Shape>();
		visibleSpace = new Rectangle2D.Double(0,DRAWING_HEIGHT-ASSUMED_DRAWING_HEIGHT,ASSUMED_DRAWING_WIDTH,ASSUMED_DRAWING_HEIGHT);
		characterSpace = new Rectangle2D.Double(visibleSpace.getX()+visibleSpace.getWidth()/5,visibleSpace.getY()+visibleSpace.getHeight()/5,visibleSpace.getWidth()*3/5,visibleSpace.getHeight()*3/5);
		//visibleSpace = new Rectangle2D.Double(0,this.getHeight()-DRAWING_HEIGHT,DRAWING_WIDTH,DRAWING_HEIGHT);
		//characterSpace = new Rectangle2D.Double(visibleSpace.getX()+visibleSpace.getWidth()/5,visibleSpace.getY()+visibleSpace.getHeight()/5,visibleSpace.getWidth()*3/5,visibleSpace.getHeight()*3/5);
		ratioX = (double)getWidth()/DRAWING_WIDTH;
		ratioY = (double)getHeight()/DRAWING_HEIGHT;

		spawnNewship();
		enemies = new ArrayList<Spawner>();
		boss1 = new Boss1(DRAWING_WIDTH/2-20,400,"resources/Boss1.png",100, 100);

		isOver = false;
		//obstacles.add(new Rectangle(200,400,400,50));
		//obstacles.add(new Rectangle(0,250,100,50));
		//obstacles.add(new Rectangle(700,250,100,50));
		//obstacles.add(new Rectangle(375,300,50,100));
		//obstacles.add(new Rectangle(300,250,200,50));

		//if(ship== null)


		/*
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {ratioX = (double)e.getComponent().getWidth()/DRAWING_WIDTH;
			ratioY = (double)e.getComponent().getHeight()/DRAWING_HEIGHT;}	  	
		});
		 */

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {ratioX = (double)e.getComponent().getWidth()/ASSUMED_DRAWING_WIDTH;ratioY = (double)e.getComponent().getHeight()/ASSUMED_DRAWING_HEIGHT;}	  	
		});

		new Thread(this).start();
	}


	/**
	 * {@inheritDoc}
	 * Paints the items in the world onto the screen
	 * 
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background


		BufferedImage scaledImage = getScaledImage();
		g.drawImage(scaledImage,0,0,null);

		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();

		g2.translate(width/2, height/2);
		g2.scale(scale, scale);
		g2.translate(-width/2, -height/2);


		AffineTransform at = g2.getTransform();
		g2.scale(ratioX, ratioY);
		g2.translate(-visibleSpace.getX(),-visibleSpace.getY());

		g.setColor(new Color(205,102,29));
		for (Shape s : obstacles) {
			g2.fill(s);
		}
		ship.draw(g2,this);

		g.setColor(Color.RED);
		g.fillRect((int) ship.getX() - 30,(int) ship.getY()-15, 100, 10);
		g.setColor(Color.GREEN);
		if(ship.getInvulnerabilityLength() > 0){
			g.setColor(Color.ORANGE);
		}
		g.fillRect((int) ship.getX()- 30,(int) ship.getY()-15, (int)((double)ship.getHp()/ship.getMaxHp() * 100), 10);
		g.setColor(Color.BLUE);
		g.fillRect((int) ship.getX() - 30,(int) ship.getY()-25, (int)((double)ship.getShield()/ship.getMaxShield()*100), 10);

		if(level == 1 && boss1 != null)
			boss1.draw(g2, this);
		else if(level == 2 && boss2 != null)
			boss2.draw(g2, this);
		else if(level == 3 && boss3 != null)
			boss3.draw(g2, this);
		else if(level == 4 && boss4 != null)
			boss4.draw(g2, this);
		else if(level == 5 && boss5 != null)
			boss5.draw(g2, this);
		else if(level == 6 && boss6 != null)
			boss6.draw(g2, this);
		else if(level == 7 && boss7 != null)
			boss7.draw(g2, this);
		else if(level == 8 && boss8 != null)
			boss8.draw(g2, this);
		else if(level == 9 && boss9 != null)
			boss9.draw(g2, this);
		else if(level == 10 && boss10 != null)
			boss10.draw(g2, this);

		//System.out.println(ship.getShield());
		for(Spawner e : enemies) {
			e.draw(g2, this);	
		}
		g2.setTransform(at);

		// TODO Add any custom drawings here
	}

	/**
	 * Moves the background when the image is near the bounds
	 * @param img moving image
	 */
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

	/**
	 * Creates a new instance of a ship
	 */
	public void spawnNewship() {
		ship = new Ship(DRAWING_WIDTH/2-20,DRAWING_HEIGHT/2-30, "resources/spaceship.png", 40, 60, 100, 5, 0, u);
	}

	public void setUpgrades(int[] u) {
		this.u = u;
		ship.setUpgrades(u);
	}
	/**
	 * Gets the key handler
	 * @return key control
	 */
	public KeyHandler getKeyHandler() {
		return keyControl;
	}

	/**
	 * Runs the thread
	 */
	public void run() {
		while (true) { // Modify this to allow quitting
			long startTime = System.currentTimeMillis();
			if (keyControl.isPressed(KeyEvent.VK_RIGHT))
				ship.turn(ship.getDirection()+.1);
			if (keyControl.isPressed(KeyEvent.VK_LEFT))
				ship.turn(ship.getDirection()-.1);
			if(keyControl.isPressed(KeyEvent.VK_SPACE))
				ship.shoot();
			if(keyControl.isPressed(KeyEvent.VK_8))
				ship.abilityOne();
			if(keyControl.isPressed(KeyEvent.VK_9))
				ship.abilityTwo();
			if(keyControl.isPressed(KeyEvent.VK_0))
				ship.abilityThree();
			if (keyControl.isPressed(KeyEvent.VK_UP))
				ship.move(1);
			if(keyControl.isPressed(KeyEvent.VK_DOWN))
				ship.move(-1);
			if(keyControl.isPressed(KeyEvent.VK_ESCAPE)){
				//running = false;
				game.changePanel("1");
			}
			if(keyControl.isPressed(KeyEvent.VK_BACK_SLASH))
				game.changePanel("9");

			for(int i = 0; i < enemies.size(); i++) {
				enemies.get(i).act(ship);
				if(enemies.get(i).getHp() <= 0) {
					enemies.remove(i);
					ship.addMoney(20);
				}
				else if(enemies.get(i).intersects(ship)) {

					ship.dropHp(1);
					enemies.get(i).dropHp(1);;
				}
			}
			ship.act(null);
			if(level == 1 && boss1 != null) {
				boss1.act(ship);
				boss1.shoot();
				if(boss1.getHp() <= 0 && !isOver){
					ship.addMoney(100);
					game.changePanel("7");
					isOver = true;
					boss1 = null;
				}
			}
			else if(level == 2 && boss2 != null) {
				boss2.act(ship);
				boss2.shoot();
				if(boss2.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					game.changePanel("7");
					isOver = true;
					boss2 = null;
				}
			}else if(level == 3 && boss3 != null) {
				boss3.act(ship);
				boss3.shoot();
				if(boss3.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					game.changePanel("7");
					isOver = true;
					boss3 = null;
				}
			}else if(level == 4 && boss4 != null) {
				boss4.act(ship);
				boss4.shoot();
				if(boss4.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					game.changePanel("7");
					isOver = true;
					boss4 = null;
				}
			}else if(level == 5 && boss5 != null) {
				boss5.act(ship);
				boss5.shoot();
				if(boss5.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					game.changePanel("7");
					isOver = true;
					boss5 = null;
				} 	
			} else if(level == 6 && boss6 != null) {
				boss6.act(ship);
				boss6.shoot();
				if(boss6.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					game.changePanel("7");
					isOver = true;
					boss6 = null;
				}
			} else if(level == 7 && boss7 != null) {
				boss7.act(ship);
				boss7.shoot();
				if(boss7.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					game.changePanel("7");
					isOver = true;
					boss7 = null;
				}
			} else if(level == 8 && boss8 != null) {
				boss8.act(ship);
				boss8.shoot();
				if(boss8.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					game.changePanel("7");
					isOver = true;
					boss8 = null;
				}
			} else if(level == 9 && boss9 != null) {
				boss9.act(ship);
				boss9.shoot();
				if(boss9.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					game.changePanel("7");
					isOver = true;
					boss9 = null;
				}
			} else if(level == 10 && boss10 != null) {
				boss10.act(ship);
				boss10.shoot();
				if(boss10.getHp() <= 0 && !isOver){
					ship.addMoney(10);
					isOver = true;
					game.changePanel("9");
					boss10 = null;
				}
			}

			if(ship.getHp()<=0){
				spawnNewship();
				enemies = new ArrayList<Spawner>();
				if(level == 1)
					boss1 = new Boss1(DRAWING_WIDTH/2-20,50,"resources/Boss1.png",100, 100);
				else if(level == 2)
					boss2 = new Boss2(DRAWING_WIDTH/2-20,50,"resources/Boss2.png",100, 100);
				else if(level == 3)
					boss3 = new Boss3(DRAWING_WIDTH/2-20,50,"resources/Boss2.png",100, 100);
				else if(level == 4)
					boss4 = new Boss4(DRAWING_WIDTH/2-20,50,"resources/Boss4.png",100, 100);
				else if(level == 5)
					boss5 = new Boss5(DRAWING_WIDTH/2-20,50,"resources/Boss5.png",100, 100);
				else if(level == 6)
					boss6 = new Boss6(DRAWING_WIDTH/2-20,50,"resources/Boss6.png",100, 100);
				else if(level == 7)
					boss7 = new Boss7(DRAWING_WIDTH/2-20,50,"resources/Boss7.png",100, 100);
				else if(level == 8)
					boss8 = new Boss8(DRAWING_WIDTH/2-20,50,"resources/Boss8.png",100, 100);
				else if(level == 9)
					boss9 = new Boss9(DRAWING_WIDTH/2-20,50,"resources/Boss9.png",100, 100);
				else if(level == 10)
					boss10 = new Boss10(DRAWING_WIDTH/2-20,50,"resources/Boss10.png",100, 100);

				game.changePanel("6");
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
	/**
	 * Gets the ship
	 * @return ship
	 */
	public Ship getShip() {
		return ship;

	}

	/**
	 * gets the ship's money
	 * @return
	 */
	public int generateMoney(){
		return ship.getMoney();
	}

	/**
	 * adds money to total
	 * @param amount amount to be added
	 */
	public void addMoney(int amount) {
		ship.addMoney(amount);
	}

	/**
	 * Gets the starting cash
	 * @return the starting cash
	 */
	public int getStartingCash(){
		return ship.getStartingMoney();
	}






	/**
	 * Represents the Key Handler
	 * @author Morchi
	 *
	 */
	public class KeyHandler implements KeyListener {

		private ArrayList<Integer> keys;
		/**
		 * Creates default instance of the key handler
		 */
		public KeyHandler() {
			keys = new ArrayList<Integer>(10);
		}
		/**
		 * {@inheritDoc}
		 * Adds key to set if pressed
		 *
		 */
		public void keyPressed(KeyEvent e) {
			keys.add(e.getKeyCode());
		}
		/**
		 * {@inheritDoc}
		 * If key is released it is removed from set
		 * 
		 */
		public void keyReleased(KeyEvent e) {
			Integer code = e.getKeyCode();
			while(keys.contains(code))
				keys.remove(code);
		}
		/**
		 * Adds key if key is typed
		 * 
		 */
		public void keyTyped(KeyEvent e) {

		}
		/**Returns if key has been pressed or not
		 * 
		 * @param code code of key
		 * @return true if key has been pressed, false otherwise
		 */
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

	/**
	 * Gets the current level of the game
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * Sets the level of the game
	 * @param level level of the game
	 */
	public void setLevel(int level) {
		this.level = level;
		if(level == 1) {
			enemies = new ArrayList<Spawner>();
			boss1 = new Boss1(DRAWING_WIDTH/2-20,400,"resources/Boss1.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		else if (level == 2) {
			enemies = new ArrayList<Spawner>();
			boss2 = new Boss2(DRAWING_WIDTH/2-20,400,"resources/Boss2.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		else if (level == 3) {
			enemies = new ArrayList<Spawner>();
			boss3 = new Boss3(DRAWING_WIDTH/2-20,400,"resources/Boss3.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		else if (level == 4) {
			enemies = new ArrayList<Spawner>();
			boss4 = new Boss4(DRAWING_WIDTH/2-20,400,"resources/Boss4.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		else if (level == 5) {
			enemies = new ArrayList<Spawner>();
			boss5 = new Boss5(DRAWING_WIDTH/2-20,400,"resources/Boss5.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		else if (level == 6) {
			enemies = new ArrayList<Spawner>();
			boss6 = new Boss6(DRAWING_WIDTH/2-20,400,"resources/Boss6.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		if (level == 7) {
			enemies = new ArrayList<Spawner>();
			boss7 = new Boss7(DRAWING_WIDTH/2-20,400,"resources/Boss7.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		if (level == 8) {
			enemies = new ArrayList<Spawner>();
			boss8 = new Boss8(DRAWING_WIDTH/2-20,400,"resources/Boss8.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		if (level == 9) {
			enemies = new ArrayList<Spawner>();
			boss9 = new Boss9(DRAWING_WIDTH/2-20,400,"resources/Boss9.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}
		if (level == 10) {
			enemies = new ArrayList<Spawner>();
			boss10 = new Boss10(DRAWING_WIDTH/2-20,400,"resources/Boss10.png",100, 100);
			enemies.add(new Spawner(DRAWING_WIDTH/2-40,50, "resources/spacestation.png", 80,80, 10, 200));
			isOver = false;
		}

	}

}
