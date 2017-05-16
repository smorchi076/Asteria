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
		BufferedImage hp = null;
		BufferedImage speed = null;
		BufferedImage damage = null;
		BufferedImage shield = null;
		BufferedImage rof = null;
		
		 try {
			hp = ImageIO.read(new File("resources/hpIcon.png"));
			speed = ImageIO.read(new File("resources/speedIcon.png"));
			damage = ImageIO.read(new File("resources/damageIcon.png"));
			shield = ImageIO.read(new File("resources/shieldIcon.png"));
			rof = ImageIO.read(new File("resources/rofIcon.png"));
			
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