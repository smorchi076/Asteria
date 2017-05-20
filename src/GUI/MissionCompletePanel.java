package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Main.Asteria;

public class MissionCompletePanel extends JPanel implements ActionListener {
	
	private int moneyEarned;
	
	Asteria game;
	JButton button,button2,button3;
	
	/**
	 * Creates an instance of the Instructions screen
	 * @param game the game that this panel is corresponding to.
	 */
	public MissionCompletePanel(Asteria game) {

		this.game = game;
		moneyEarned = GamePanel.generateMoney();
		Icon r = new ImageIcon("resources/backToHomeButton.png");
		button = new JButton(r);
		button.addActionListener(this);
		button.setBounds(0, -5, 100, 50);
		setLayout(null);
		add(button);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(false);
		
		Icon continueIcon = new ImageIcon("resources/continue.png");
		button2 = new JButton(continueIcon);
		button2.addActionListener(this);
		button2.setBounds(400,350, 130, 130);
		setLayout(null);
		add(button2);
		button2.setContentAreaFilled(false);
		button2.setOpaque(false);
		button2.setBorderPainted(false);
		
		Icon home = new ImageIcon("resources/house.png");
		button3 = new JButton(home);
		button3.addActionListener(this);
		button3.setBounds(230,350, 130, 130);
		setLayout(null);
		add(button3);
		button3.setContentAreaFilled(false);
		button3.setOpaque(false);
		button3.setBorderPainted(false);
		
		

	}

	/**
	 * {@inheritDoc}
	 * Draws the images
	 * @param g the Graphics object used to draw the images.
	 */
	public void paintComponent(Graphics g)
	{
		BufferedImage scaledImage = getScaledImage();
		BufferedImage completed = null;
		BufferedImage s = null;
		BufferedImage t = null;
		try {
			completed = ImageIO.read(new File("resources/missionComplete.png"));
			s = ImageIO.read(new File("resources/asteriaword.png"));
			t = ImageIO.read(new File("resources/moneyIcon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(g);
		g.drawImage(scaledImage, 0, 0, null);
		g.drawImage(completed, 100, 100, null);
		g.drawImage(t, 305, 225, null);
		g.drawImage(s, 255, -20, null);
		g.setColor(new Color(0,255,0));
		Font myFont = new Font("Impact", Font.BOLD, 48);
		g.setFont(myFont);
		g.drawString("+" + moneyEarned, 400, 275);

	}

	/**
	 * {@inheritDoc}
	 * Checks to see if user wants to go back to home screen
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			int result = okcancel("Are you sure? All progress will be lost");
			if(result == 0){
			game.changePanel("1");
			}
		}
		if(e.getSource() == button2) game.changePanel("2");
		if(e.getSource() == button3){
			int result = okcancel("Are you sure? All progress will be lost");
			if(result == 0){
			game.changePanel("1");
			}
			
		}
	}
	
	public int okcancel(String theMessage) {
	    int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
	        "alert", JOptionPane.OK_CANCEL_OPTION);
	    return result;
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
