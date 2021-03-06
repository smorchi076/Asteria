package Main;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.GameOverPanel;
import GUI.GamePanel;
import GUI.InstructionPanel;
import GUI.MissionCompletePanel;
import GUI.ModePanel;
import GUI.ShopPanel;
import GUI.VersusPanel;
import GUI.VersusWinnerPanel;
import GUI.WinnerPanel;
import GUI.HomePanel;
/**
 * Represents the Asteria game
 * @author Morchi
 *
 */
public class Asteria extends JFrame {
//ASTERIA
	JPanel cardPanel;
	/**
	 * Creates an instance of the Asteria game
	 * @param title title of the game displayed
	 */
	public Asteria(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);

		HomePanel panel1 = new HomePanel(this);    
		GamePanel panel2 = new GamePanel(this);
		InstructionPanel panel3 = new InstructionPanel(this);
		ShopPanel panel4 = new ShopPanel(this, panel2);
		ModePanel panel5 = new ModePanel(this, panel2);
		
		GameOverPanel panel6 = new GameOverPanel(this,panel2);
		MissionCompletePanel panel7 = new MissionCompletePanel(this, panel2);
		VersusPanel panel10 = new VersusPanel(this);
		VersusWinnerPanel panel8 = new VersusWinnerPanel(this,panel10);
		WinnerPanel panel9 = new WinnerPanel(this);
		


		addKeyListener(panel2.getKeyHandler());

		cardPanel.add(panel1, "1");
		cardPanel.add(panel2, "2");
		cardPanel.add(panel3, "3");
		cardPanel.add(panel4, "4");
		cardPanel.add(panel5, "5");
		cardPanel.add(panel6, "6");
		cardPanel.add(panel7, "7");
		cardPanel.add(panel8, "8");
		cardPanel.add(panel9, "9");
		cardPanel.add(panel10, "10");
		
		
		add(cardPanel);



		setVisible(true);
	}
	/**
	 * Where game is created
	 * 
	 */
	public static void main(String[] args)
	{
		Asteria game = new Asteria("Asteria");


	}
	/**
	 * Changes panel
	 * @param name corresponds to a panel
	 */
	public void changePanel(String name) {
		((CardLayout)cardPanel.getLayout()).show(cardPanel,name);
		requestFocus();
	}

}
