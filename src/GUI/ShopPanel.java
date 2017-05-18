package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Main.Asteria;

/**
 * This class represents the Shop screen.
 *
 */
public class ShopPanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button;
	JButton[] upgradeButtons = new JButton[5];
	int[] upgrades = {0, 0, 0, 0, 0};

	/**
	 * Creates an instance of the Shop screen with purchasable upgrades
	 * @param game the game that this panel is corresponding to.
	 */
	public ShopPanel(Asteria game) {

		this.game = game;

		Icon r = new ImageIcon("resources/backToHomeButton.png");
		button = new JButton(r);
		button.addActionListener(this);
		setLayout(null);
		button.setBounds(0, -5, 100, 50);
		add(button);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);

		Icon a = new ImageIcon("resources/upgradePlusSign.png");
		upgradeButtons[0] = new JButton(a);
		upgradeButtons[0].addActionListener(this);
		setLayout(null);
		upgradeButtons[0].setBounds(700, 77, 60, 60);
		add(upgradeButtons[0]);
		upgradeButtons[0].setContentAreaFilled(false);
		upgradeButtons[0].setOpaque(false);
		upgradeButtons[0].setBorderPainted(false);

		upgradeButtons[1] = new JButton(a);
		upgradeButtons[1].addActionListener(this);
		setLayout(null);
		upgradeButtons[1].setBounds(700, 177, 60, 60);
		add(upgradeButtons[1]);
		upgradeButtons[1].setContentAreaFilled(false);
		upgradeButtons[1].setOpaque(false);
		upgradeButtons[1].setBorderPainted(false);

		upgradeButtons[2] = new JButton(a);
		upgradeButtons[2].addActionListener(this);
		setLayout(null);
		upgradeButtons[2].setBounds(700, 277, 60, 60);
		add(upgradeButtons[2]);
		upgradeButtons[2].setContentAreaFilled(false);
		upgradeButtons[2].setOpaque(false);
		upgradeButtons[2].setBorderPainted(false);

		upgradeButtons[3] = new JButton(a);
		upgradeButtons[3].addActionListener(this);
		setLayout(null);
		upgradeButtons[3].setBounds(700, 377, 60, 60);
		add(upgradeButtons[3]);
		upgradeButtons[3].setContentAreaFilled(false);
		upgradeButtons[3].setOpaque(false);
		upgradeButtons[3].setBorderPainted(false);

		upgradeButtons[4] = new JButton(a);
		upgradeButtons[4].addActionListener(this);
		setLayout(null);
		upgradeButtons[4].setBounds(700, 477, 60, 60);
		add(upgradeButtons[4]);
		upgradeButtons[4].setContentAreaFilled(false);
		upgradeButtons[4].setOpaque(false);
		upgradeButtons[4].setBorderPainted(false);


	}

	/**
	 * {@inheritDoc}
	 * Draws the images and repaints continuously 
	 * @param g the Graphics object used to draw the images.
	 */
	public void paintComponent(Graphics g)
	{
		BufferedImage scaledImage = getScaledImage();
		BufferedImage hp = null;
		BufferedImage speed = null;
		BufferedImage damage = null;
		BufferedImage shield = null;
		BufferedImage rof = null;
		BufferedImage upgradesWord = null;

		try {
			hp = ImageIO.read(new File("resources/hpIcon.png"));
			speed = ImageIO.read(new File("resources/speedIcon.png"));
			damage = ImageIO.read(new File("resources/damageIcon.png"));
			shield = ImageIO.read(new File("resources/shieldIcon.png"));
			rof = ImageIO.read(new File("resources/rofIcon.png"));
			upgradesWord = ImageIO.read(new File("resources/upgradesWord.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, null);
		g.drawImage(hp, 50, 70, null);
		g.drawImage(speed, 50, 170, null);
		g.drawImage(damage, 50, 270, null);
		g.drawImage(shield, 50, 370, null);
		g.drawImage(rof, 50, 470, null);
		g.drawImage(upgradesWord, 240, -20, null);

		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(upgrades[i]>j){
					g.setColor(Color.green);
					g.fillRect(135 + j*110, 70 + i*100, 110, 75);
				}
				else{
					g.setColor(Color.DARK_GRAY);
					g.drawRect(135 + j*110, 70 + i*100, 110, 75);
				}
			}
		}
		repaint();
	}

	/**
	 * {@inheritDoc}
	 * Checks to see if user wants to go back to home screen, 
	 * or wants to upgrade his ship
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) game.changePanel("1");
		for(int i=0; i<upgradeButtons.length; i++){
			if(e.getSource() == upgradeButtons[i] && upgrades[i]<5) upgrades[i]++;
		}
	}

	private BufferedImage getScaledImage(){
		ImageIcon backImage = new ImageIcon("resources/homeBackground.png");
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) image.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(backImage.getImage(), 0, 0,getWidth(),getHeight(), null);

		return image;
	}

}