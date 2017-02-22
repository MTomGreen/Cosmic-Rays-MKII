package green.ui.mainWindow.component;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextArea;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class JMultiLineLabel extends JTextArea {
	
	public boolean darkBG = false;
	
    public JMultiLineLabel(){
        setEditable(false);  
        setCursor(null);  
        setOpaque(false);  
        setFocusable(false);  
        setFont(UIManager.getFont("Label.font"));    
        setWrapStyleWord(true);  
        setLineWrap(true);
    }
    
    public void addLine(String line){
    	this.setText(getText()+line+"\n");
    }
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	if(darkBG){
    		g.setColor(Color.BLACK);
    		g.fillRect(0, 0, getWidth(), getHeight());
    		setForeground(Color.WHITE);
    		super.paint(g);
    	}
    }
}
