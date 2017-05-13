package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Asteria;

public class InstructionPanel extends JPanel implements ActionListener {

	Asteria game;
	JButton button;

	public InstructionPanel(Asteria game) {

		this.game = game;

		Icon r = new ImageIcon("resources/backToHomeButton.png");
		button = new JButton(r);
		button.addActionListener(this);
		setLayout(null);
		button.setBounds(0, -25, 100, 100);

		add(button);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);

	}

	public void paintComponent(Graphics g)
	{
		BufferedImage i = null;
		BufferedImage s = null;
		BufferedImage t = null;
		try {
			i = ImageIO.read(new File("resources/instructionsText.png"));
			s = ImageIO.read(new File("resources/asteriaword.png"));
			t = ImageIO.read(new File("resources/asteriaship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(i, 190, 60, null);
		g.drawImage(t, 300, 380, null);
		g.drawImage(s, 255, -20, null);

	}

	public void actionPerformed(ActionEvent e) {
		game.changePanel("1");
	}


}
