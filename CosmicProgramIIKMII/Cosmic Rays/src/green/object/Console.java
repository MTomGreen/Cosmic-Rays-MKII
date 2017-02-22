package green.object;

import java.util.ArrayList;

import green.ui.ConsoleWindow;

public class Console {
	
	public static ArrayList<String> combinedLog = new ArrayList<String>();
	public static ArrayList<String> infoLog = new ArrayList<String>();
	public static ArrayList<String> errLog = new ArrayList<String>();
	
	public static void log(Object what){
		combinedLog.add(what.toString());
		errLog.add(what.toString());
		System.out.println(what);
		ConsoleWindow.addLine(what.toString());
	}
	
	public static void logError(Object what){
		combinedLog.add((String)what);
		errLog.add((String)what);
		System.err.println("[WARN]" + what);
		ConsoleWindow.addLine("[WARN]" + what.toString());
	}
	

}
