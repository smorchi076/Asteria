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

public class ModePanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button, button2;

	public ModePanel(Asteria game) {

		this.game = game;

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
		button2.setBounds(250, 100, 370, 100);

		add(button2);
		button2.setContentAreaFilled(false);
		button2.setOpaque(false);
		button2.setBorderPainted(false);

	}

	public void paintComponent(Graphics g)
	{
		BufferedImage scaledImage = getScaledImage();
		BufferedImage s = null;
		try {
			s = ImageIO.read(new File("resources/mode.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, null);
		g.drawImage(s, 245, -20, null);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) game.changePanel("1");
		if(e.getSource() == button2) game.changePanel("2");
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
