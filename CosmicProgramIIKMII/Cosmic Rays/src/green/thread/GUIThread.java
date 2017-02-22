package green.thread;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import green.object.Console;
import green.ui.ConsoleWindow;
import green.ui.loadingWindow.LoadingWindow;
import green.ui.mainWindow.MainWindow;
import green.util.InfoUtils;

public class GUIThread extends Thread {
	
	public static MainWindow window;
	public static ConsoleWindow con;
	
	public static LoadingWindow loadWindow;
	Dimension preferredSize;
	@Override
	public void run() {
		Console.log("Creating loading window");
		loadWindow = new LoadingWindow();
		loadWindow.setVisible(true);
		
		
		if((boolean)InfoUtils.getConfigValueOf("useConsole", true)){
			Console.log("Creating console");
			con = new ConsoleWindow();
			con.setTitle("Console");
			con.setSize(800, 600);
			con.setVisible(true);
		}
		else{
			Console.log("Console disabled. Will not display.");
			//Am well aware that this won't be seen, unless the program is
			//run through the command line.
		}
		
		try {
			MainThread.latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//Load main window
		int windowMode = (Integer) InfoUtils.getConfigValueOf("windowMode", 2);
		if(windowMode == 1)
			preferredSize = new Dimension(1280, 720);
		if(windowMode == 2)
			preferredSize = (Dimension) InfoUtils.getConfigValueOf("windowPrefSize", new Dimension(1280,720));
		if(windowMode == 3)
			preferredSize = (Dimension) InfoUtils.getConfigValueOf("customSize", new Dimension(1280,720));
		Console.log("Creating main window");
		window = new MainWindow(preferredSize);
		Console.log("Disposing loading window");
		loadWindow.dispose();
		
		
		window.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				InfoUtils.setConfigValue("windowPrefSize", window.getSize());
				InfoUtils.saveConfig();
				Console.log("System shutting down...");
				super.windowClosing(e);
			}
		});
		Console.log("GUI Initialised!");
	}

}
