package Main;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.GamePanel;
import GUI.OptionPanel;

public class Asteria extends JFrame {

JPanel cardPanel;
	
	public Asteria(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	    
		OptionPanel panel1 = new OptionPanel(this);    
	    GamePanel panel2 = new GamePanel();
	    
	    addKeyListener(panel2.getKeyHandler());
	
	    cardPanel.add(panel1,"1");
	    cardPanel.add(panel2,"2");
	    
	    add(cardPanel);
	
	    setVisible(true);
	}

	public static void main(String[] args)
	{
		Asteria game = new Asteria("Asteria");
	}
  
	public void changePanel(String name) {
		((CardLayout)cardPanel.getLayout()).show(cardPanel,name);
		requestFocus();
	}

}
