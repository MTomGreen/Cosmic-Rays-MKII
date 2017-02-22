package green.ui.mainWindow;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

import green.util.InfoUtils;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JTabbedPane tabbedPane;
	
	public MainWindow(Dimension size) {
		setMinimumSize(new Dimension(450,300));
		setTitle("Cosmic Rays");
		setSize(size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 0, SpringLayout.WEST, getContentPane());
		tabbedPane.add("Overview", Tabs.overview);
		tabbedPane.add("Settings", Tabs.settings);
		tabbedPane.add("Calculator", Tabs.calculatorScroll);
		getContentPane().add(tabbedPane);
		
		
		setVisible(true);
	}
	
	public MainWindow() {
		this((Dimension)InfoUtils.getConfigValueOf("windowPrefSize", new Dimension(1280,720)));
	}
	
}
