package GUI;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Main.Asteria;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HomePanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button, button2, button3;

	public HomePanel(Asteria game) {
		
		this.game = game;
		Icon instructions = new ImageIcon("resources/instructions.png");
		button = new JButton(instructions);
		button.addActionListener(this);
		setLayout(null);
		button.setBounds(150, 300, 100, 100);

		add(button);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);


		Icon playbutton = new ImageIcon("resources/playbutton.png");
		button2 = new JButton(playbutton);
		button2.addActionListener(this);
		button2.setBounds(300, 250, 200, 200);

		add(button2);
		button2.setContentAreaFilled(false);
		button2.setOpaque(false);
		button2.setBorderPainted(false);
		
		Icon shopbutton = new ImageIcon("resources/shop.png");
		button3 = new JButton(shopbutton);
		button3.addActionListener(this);
		button3.setBounds(540, 290, 150, 150);

		add(button3);
		button3.setContentAreaFilled(false);
		button3.setOpaque(false);
		button3.setBorderPainted(false);
		
		JLabel pic = new JLabel(new ImageIcon("resources/asteriaship.png"));
		add(pic);



		// JLabel pic = new JLabel(new ImageIcon("resources/homeBackground.png"));
		//add(pic);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) game.changePanel("3");
		if(e.getSource() == button2) game.changePanel("2");
		if(e.getSource() == button3) game.changePanel("4");
	}

	public void paintComponent(Graphics g)
	{
		BufferedImage scaledImage = getScaledImage();
		BufferedImage gameLogo = null;
		BufferedImage word = null;
		try {
			 gameLogo = ImageIO.read(new File("resources/afterburner.png"));
			 //gameLogo = resize(gameLogo, 150,150);
			 word = ImageIO.read(new File("resources/asteriaword.png"));
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, null);
		g.drawImage(gameLogo,300,0,null);
		g.drawImage(word,265,100,null);
		
	}

	private BufferedImage getScaledImage(){
		ImageIcon backImage = new ImageIcon("resources/homeBackground.png");
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) image.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(backImage.getImage(), 0, 0,getWidth(),getHeight(), null);

		return image;
	}
	
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  


}