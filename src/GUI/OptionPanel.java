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
		
		
		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File("myImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    button = new JButton(new ImageIcon(buttonIcon));
	    button.setBorderPainted(false);
	    button.setFocusPainted(false);
	    button.setContentAreaFilled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		game.changePanel("2");
	}
	
}