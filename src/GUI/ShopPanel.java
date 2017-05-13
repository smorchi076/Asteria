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

public class ShopPanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button;

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

	}

	public void paintComponent(Graphics g)
	{
		BufferedImage scaledImage = getScaledImage();
		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, null);
	}

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