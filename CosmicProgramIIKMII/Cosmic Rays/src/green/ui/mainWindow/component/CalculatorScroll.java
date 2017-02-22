package green.ui.mainWindow.component;

import javax.swing.JScrollPane;

import green.ui.mainWindow.Tabs;

@SuppressWarnings("serial")
public class CalculatorScroll extends JScrollPane{
	
	public CalculatorScroll() {
		super(Tabs.calculator, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}

}
