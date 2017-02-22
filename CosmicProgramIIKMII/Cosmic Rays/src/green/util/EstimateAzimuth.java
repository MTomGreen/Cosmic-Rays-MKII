package green.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import green.object.Console;

public class EstimateAzimuth {

	private static File dir = new File("data/stationEvents");
	private static File[] files;
	private static ArrayList<Integer> eventNum = new ArrayList<Integer>();
	private static ArrayList<Double> rotationRads = new ArrayList<Double>();
	private static ArrayList<Double> rotationNum = new ArrayList<Double>();
	private static ArrayList<Double> sidereal = new ArrayList<Double>();
	private static ArrayList<Double> weightingFactor = new ArrayList<Double>();
	private static ArrayList<Double> weightedData = new ArrayList<Double>();
	private static ArrayList<Double> dataX = new ArrayList<Double>();
	private static ArrayList<Double> dataY = new ArrayList<Double>();
	private static ArrayList<Integer> binNum = new ArrayList<Integer>();
	

	public static double calculate() {
		if (!dir.exists())
			dir.mkdirs();
		return calculate(null);
	}

	public static double calculate(File[] filess) {
		if (filess == null) {
			File[] filesInDir = dir.listFiles();
			if (filesInDir == null) {
				Console.logError("No event data files found!");
				return 0;
			}
			files = filesInDir;
		}
		else{
			files = filess;
		}
		
		FileReader r;
		BufferedReader reader;
		
		for(File f : files){
			System.out.print(f.getName()+"\t");
			try{
				r = new FileReader(f);
				reader = new BufferedReader(r);
					
				String line;
				while((line = reader.readLine())!=null){
					if(!line.contains("#") && line.length() > 0){
						eventNum.add(Integer.parseInt(line.split("\t")[1]));
						System.out.print(eventNum.get(eventNum.size()-1) + "\t");
					}
				}
				System.out.print("\n");
				
				r.close();
				reader.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		for(int i = 0; i < eventNum.size(); i ++){
			rotationRads.add((2*Math.PI)*((i)/23.93446959));
			rotationNum.add(Math.toDegrees(rotationRads.get(i))/360);
		    sidereal.add((i/24.0)*23.93446959);
		    weightingFactor.add(0.5*(1-Math.cos(2*Math.PI*i/eventNum.size())));
		    weightedData.add(eventNum.get(i)*weightingFactor.get(i));
		    dataX.add(weightedData.get(i)*(Math.cos(rotationRads.get(i))));
		    dataY.add(weightedData.get(i)*(Math.sin(rotationRads.get(i))));
		}
		
		double totalDataX = 0;
		double totalDataY = 0;
		
		for(double j : dataX)
			totalDataX += j;
		for(double k : dataY)
			totalDataY += k;
		
		dataX.add(totalDataX);
		dataY.add(totalDataY);
		
		for(int i = 0; i < eventNum.size(); i ++)
			binNum.add(i);
		
		double angle = Math.toDegrees(Math.atan(totalDataY/totalDataX));
		Console.log("\n\n");
		
		System.out.print("Bin#\t\tEvents\t\tSideral\t\tAngle Rads\t\tEarth Revs\t\tWeighting Factor\t\tWeighted Data\t\tData X\t\tData Y\n");
		for(int i = 0; i < eventNum.size(); i ++){
			System.out.print(binNum.get(i)+"\t\t");
			System.out.print(eventNum.get(i)+"\t\t");
			System.out.print(sidereal.get(i)+"\t\t");
			System.out.print(rotationRads.get(i)+"\t\t");
			System.out.print(binNum.get(i)+"\t\t");
			System.out.print(rotationNum.get(i)+"\t\t");
			System.out.print(weightingFactor.get(i)+"\t\t");
			System.out.print(weightedData.get(i)+"\t\t");
			System.out.print(dataX.get(i)+"\t\t");
			System.out.print(dataY.get(i)+"\t\t");
			System.out.print("\n");
		}
		Console.log("\nAngle: " + angle);
		
		return angle;
	}

}
