package GUI;

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
 * This class represents the mode selection screen.
 * @author Feng
 *
 */
public class ModePanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button, button2,button3;
	GamePanel gp;
	
	
	/**
	 * Creates an instance of the mode selection screen
	 * @param game the game that this panel is corresponding to.
	 */
	public ModePanel(Asteria game, GamePanel g) {

		this.game = game;
		gp = g;
		Icon c = new ImageIcon("resources/backToHomeButton.png");
		button = new JButton(c);
		button.addActionListener(this);
		setLayout(null);
		button.setBounds(0, -5, 100, 50);

		add(button);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);
		
		Icon d = new ImageIcon("resources/campaign.png");
		button2 = new JButton(d);
		button2.addActionListener(this);
		setLayout(null);
		button2.setBounds(225, 100, 370, 100);

		add(button2);
		button2.setContentAreaFilled(false);
		button2.setOpaque(false);
		button2.setBorderPainted(false);
		
		
		Icon versus = new ImageIcon("resources/versus.png");
		button3 = new JButton(versus);
		button3.addActionListener(this);
		setLayout(null);
		button3.setBounds(225, 300, 370, 100);

		add(button3);
		button3.setContentAreaFilled(false);
		button3.setOpaque(false);
		button3.setBorderPainted(false);

	}

	/**
	 * {@inheritDoc}
	 * Draws the images
	 * @param g the Graphics object used to draw the images.
	 */
	public void paintComponent(Graphics g)
	{
		BufferedImage scaledImage = getScaledImage();
		BufferedImage s = null;
		try {
			s = ImageIO.read(new File("resources/selectModeText.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, null);
		g.drawImage(s, 200, -20, null);

	}

	/**
	 * {@inheritDoc}
	 * Checks to see if user wants to go back to home screen, 
	 * or selects a mode
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) game.changePanel("1");
		if(e.getSource() == button2){
			gp.setMode(1);
			gp.setUpGame();
			game.changePanel("2");
			gp.setLevel(1);
			gp.spawnNewship();
		}
		if(e.getSource() == button3){ 
			gp.setMode(2);
			gp.setUpGame();
			game.changePanel("2");
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
