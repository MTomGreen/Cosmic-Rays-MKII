package green.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import green.ui.mainWindow.component.JMultiLineLabel;

@SuppressWarnings("serial")
public class ConsoleWindow extends JFrame {
	
	public static JMultiLineLabel label = new JMultiLineLabel ();
	private static JScrollPane scroll;
	
	public ConsoleWindow() {
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		label.darkBG = true;
		scroll = new JScrollPane(label);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scroll, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scroll, 0, SpringLayout.EAST, getContentPane());
		
		getContentPane().add(scroll);
	}
	
	public static void addLine(String line){
		label.addLine(line);
	}
}
