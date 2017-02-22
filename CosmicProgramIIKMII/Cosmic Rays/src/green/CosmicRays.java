package green;

import java.io.IOException;

import green.thread.GUIThread;
import green.thread.MainThread;

public class CosmicRays {
	
	public static GUIThread guiThread;
	public static MainThread mainThread;
	
	public static void main(String[] args) throws IOException {
		
		guiThread = new GUIThread();
		mainThread = new MainThread();
		mainThread.start();
		guiThread.start();
	}

}
