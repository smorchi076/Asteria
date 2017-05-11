package GUI;
import java.awt.*;
import javax.swing.*;

import Main.Asteria;

import java.awt.event.*;


public class OptionPanel extends JPanel implements ActionListener {
	
	Asteria game;
	
	public OptionPanel(Asteria game) {
		this.game = game;
		JButton button = new JButton("Press me!");
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent e) {
		game.changePanel("2");
	}
	
}