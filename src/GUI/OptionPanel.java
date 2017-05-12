package GUI;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Main.Asteria;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

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
	    
	    JLabel pic = new JLabel(new ImageIcon("resources/homeBackground.png"));
		add(pic);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		game.changePanel("2");
	}
	
	/*
	public void paintComponent(Graphics page)
	{
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("resources/homeBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    super.paintComponent(page);

	    int h = img.getHeight(null);
	    int w = img.getWidth(null);
	    
	    // Scale Horizontally:
	    if ( w > this.getWidth() )
	    {
	        img = (BufferedImage) img.getScaledInstance( getWidth(), -1, Image.SCALE_DEFAULT );
	        h = img.getHeight(null);
	    }

	    // Scale Vertically:
	    if ( h > this.getHeight() )
	    {
	        img = (BufferedImage) img.getScaledInstance( -1, getHeight(), Image.SCALE_DEFAULT );
	    }

	    // Center Images
	    int x = (getWidth() - img.getWidth(null)) / 2;
	    int y = (getHeight() - img.getHeight(null)) / 2;

	    // Draw it
	    page.drawImage( img, x, y, null );
	}
	*/
	
	
}