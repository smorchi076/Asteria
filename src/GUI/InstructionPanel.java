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
 * This class represents the Instructions screen.
 * @author Feng
 *
 */
public class InstructionPanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button;
	
	/**
	 * Creates an instance of the Instructions screen
	 * @param game the game that this panel is corresponding to.
	 */
	public InstructionPanel(Asteria game) {

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

	}

	/**
	 * {@inheritDoc}
	 * Draws the images
	 * @param g the Graphics object used to draw the images.
	 */
	public void paintComponent(Graphics g)
	{
		BufferedImage scaledImage = getScaledImage();
		BufferedImage i = null;
		BufferedImage s = null;
		BufferedImage t = null;
		try {
			i = ImageIO.read(new File("resources/instructionsText.png"));
			s = ImageIO.read(new File("resources/asteriaword.png"));
			t = ImageIO.read(new File("resources/asteriaship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, null);
		g.drawImage(i, 190, 60, null);
		g.drawImage(t, 305, 380, null);
		g.drawImage(s, 255, -20, null);

	}

	/**
	 * {@inheritDoc}
	 * Checks to see if user wants to go back to home screen
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) game.changePanel("1");
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
