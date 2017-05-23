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
 * This class represents the Winner screen.
 * @author Feng
 *
 */
public class WinnerPanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button, button2;
	
	/**
	 * Creates an instance of the screen after the player defeats the final boss
	 * @param game the game that this panel is corresponding to.
	 */
	public WinnerPanel(Asteria game) {

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
		try {
			i = ImageIO.read(new File("resources/WinnerText.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, null);
		g.drawImage(i, 200, 60, null);

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
