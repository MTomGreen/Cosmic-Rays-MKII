package green.ui.loadingWindow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class LoadPanel extends JPanel{
	
	private Image image;
	public static JProgressBar progressBar;
	
	public LoadPanel() {
		this.setOpaque(false);
		InputStream inStream = this.getClass().getResourceAsStream("LoadingScreen.png");
		//InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("LoadingScreen.png");
		try {
			image = ImageIO.read(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//image= ImageIO.read(new File(""));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		springLayout.putConstraint(SpringLayout.WEST, progressBar, 130, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar, -50, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, progressBar, -100, SpringLayout.EAST, this);
		add(progressBar);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		progressBar.setToolTipText(progressBar.getValue()+"/"+progressBar.getMaximum());
		g.drawString(progressBar.getValue()+"%", progressBar.getX(), progressBar.getY());
		super.paint(g);
	}
}
