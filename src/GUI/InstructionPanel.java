package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Asteria;

public class InstructionPanel extends JPanel implements ActionListener {

Asteria game;
	
	public InstructionPanel(Asteria game) {
		
		this.game = game;
		
		JLabel label = new JLabel("Instructions" + "\n");

		add(label);

		
		
		
		
		

		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		game.changePanel("3");
	}
	
	
}
