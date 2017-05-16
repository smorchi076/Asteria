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
import javax.swing.JProgressBar;

import Main.Asteria;

public class ShopPanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button;
	JButton[] upgradeButtons = new JButton[5];
	JProgressBar progressBar;

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
		
		Icon b = new ImageIcon("resources/upgradePlusSign.png");
		upgradeButtons[1] = new JButton(b);
		upgradeButtons[1].addActionListener(this);
		setLayout(null);
		upgradeButtons[1].setBounds(700, 177, 60, 60);
		add(upgradeButtons[1]);
		upgradeButtons[1].setContentAreaFilled(false);
		upgradeButtons[1].setOpaque(false);
		upgradeButtons[1].setBorderPainted(false);

		Icon c = new ImageIcon("resources/upgradePlusSign.png");
		upgradeButtons[2] = new JButton(c);
		upgradeButtons[2].addActionListener(this);
		setLayout(null);
		upgradeButtons[2].setBounds(700, 277, 60, 60);
		add(upgradeButtons[2]);
		upgradeButtons[2].setContentAreaFilled(false);
		upgradeButtons[2].setOpaque(false);
		upgradeButtons[2].setBorderPainted(false);

		Icon d = new ImageIcon("resources/upgradePlusSign.png");
		upgradeButtons[3] = new JButton(d);
		upgradeButtons[3].addActionListener(this);
		setLayout(null);
		upgradeButtons[3].setBounds(700, 377, 60, 60);
		add(upgradeButtons[3]);
		upgradeButtons[3].setContentAreaFilled(false);
		upgradeButtons[3].setOpaque(false);
		upgradeButtons[3].setBorderPainted(false);

		Icon e = new ImageIcon("resources/upgradePlusSign.png");
		upgradeButtons[4] = new JButton(e);
		upgradeButtons[4].addActionListener(this);
		setLayout(null);
		upgradeButtons[4].setBounds(700, 477, 60, 60);
		add(upgradeButtons[4]);
		upgradeButtons[4].setContentAreaFilled(false);
		upgradeButtons[4].setOpaque(false);
		upgradeButtons[4].setBorderPainted(false);


	}

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