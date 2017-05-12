package GUI;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Main.Asteria;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class OptionPanel extends JPanel implements ActionListener {
	
	Asteria game;
	JButton button, button2;
	
	public OptionPanel(Asteria game) {
		
		//JLabel pic = new JLabel(new ImageIcon("resources/homeBackground.png"));
		//add(pic);
		
		this.game = game;
		Icon instructions = new ImageIcon("resources/instructions.png");
	    button = new JButton(instructions);
	    button.addActionListener(this);
	    setLayout(null);
	   
	    add(button);
	    button.setContentAreaFilled(false);
	    button.setOpaque(false);
	    button.setBorderPainted(false);
	    
		
		
		

		Icon playbutton = new ImageIcon("resources/playbutton.png");
	    button2 = new JButton(playbutton);
	    button2.addActionListener(this);
	    
	    add(button2);
	    button2.setContentAreaFilled(false);
	    button2.setOpaque(false);
	    button2.setBorderPainted(false);
	   
	    
	   // JLabel pic = new JLabel(new ImageIcon("resources/homeBackground.png"));
		//add(pic);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		game.changePanel("2");
	}
	
	 public void paintComponent(Graphics g)
	    {
		 	BufferedImage scaledImage = getScaledImage();
		    super.paintComponent(g);
		    g.drawImage(scaledImage, 0, 0, null);
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