package Main;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import FileIO.FileIO;
import GUI.GameOverPanel;
import GUI.GamePanel;
import GUI.InstructionPanel;
import GUI.MissionCompletePanel;
import GUI.ModePanel;
import GUI.ShopPanel;
import GUI.HomePanel;
/**
 * Represents the Asteria game
 * @author Morchi
 *
 */
public class Asteria extends JFrame {

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
	    ShopPanel panel4 = new ShopPanel(this);
	    ModePanel panel5 = new ModePanel(this);
	    GameOverPanel panel6 = new GameOverPanel(this);
	    MissionCompletePanel panel7 = new MissionCompletePanel(this);
	    
	    addKeyListener(panel2.getKeyHandler());
	
	    cardPanel.add(panel1,"1");
	    cardPanel.add(panel2,"2");
	    cardPanel.add(panel3, "3");
	    cardPanel.add(panel4, "4");
	    cardPanel.add(panel5, "5");
	    cardPanel.add(panel6, "6");
	    cardPanel.add(panel7, "7");
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
