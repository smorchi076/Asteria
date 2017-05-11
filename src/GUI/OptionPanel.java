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
	
	public OptionPanel(Asteria game) {
		
		this.game = game;
		JButton button = new JButton("Press me!");
		button.addActionListener(this);
		add(button);
		
		Icon warnIcon = new ImageIcon("resources/play.png");
	    JButton button2 = new JButton(warnIcon);
	    add(button2);
	    button2.setContentAreaFilled(false);
	    
	 
	   
	   
	    
	}
	
	public void actionPerformed(ActionEvent e) {
		game.changePanel("2");
	}
	
	
	public void paintComponent(Graphics page)
	{
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("resources/background.png"));
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
}