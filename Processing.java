import java.util.*;


public class Processing {

	private ArrayList<String> frame, trajectory, x, y, x_raw, y_raw;
	private ArrayList<Double> dx, dy, deflection, nanometers;
	private String [][] data;// newData;
	private Object [][] newData;
	private int rows;
	private double average, standardDeviation;



	public Processing () {	

	}


	/*Getters and Setters*/

	public ArrayList<String> getFrame() {return frame;}
	public void setFrame(ArrayList<String> frame) {this.frame = frame;}
	public ArrayList<String> getTrajectory() {return trajectory;}
	public void setTrajectory(ArrayList<String> trajectory) {this.trajectory = trajectory;}
	public ArrayList<Double> getDx() {return dx;}
	public void setDx(ArrayList<Double> dx) {this.dx = dx;}
	public ArrayList<Double> getDy() {return dy;}
	public void setDy(ArrayList<Double> dy) {this.dy = dy;}
	public ArrayList<Double> getDeflection() {return deflection;}
	public void setDeflection(ArrayList<Double> deflection) {this.deflection = deflection;}
	public ArrayList<Double> getNanometers() {return nanometers;}
	public void setNanometers(ArrayList<Double> nanometers) {this.nanometers = nanometers;}
	
	
	public Processing (ArrayList<String> fileLine) {

		rows = fileLine.size();
		int columns = 8;

		System.out.println("We have found size: " + rows);

		data = new String [rows][columns];

		frame = new ArrayList<String>();
		trajectory = new ArrayList<String>();
		x = new ArrayList<String>();
		y = new ArrayList<String>();
		x_raw = new ArrayList<String>();
		y_raw = new ArrayList<String>();
		dx = new ArrayList<Double>();
		dy = new ArrayList<Double>();


		for (int i = 0; i < rows; i++) {
			data [i] = fileLine.get(i).split(",");
			frame.add (data [i][0]);
			trajectory.add (data [i][1]);
			x.add (data [i][2]);
			y.add (data [i][3]);
			x_raw.add (data [i][4]);
			y_raw.add (data [i][5]);
			dx.add (Double.parseDouble(data [i][6]));
			dy.add (Double.parseDouble(data [i][7]));
		}

		//		for (String s : trajectory) {
		//		System.out.println("tragic " + s);
		//		}

		//System.out.println("frame: " + frame + "\n" + "trajectory: " + trajectory + "\n" + "dx: " + dx + "\n" + "dy: " + dy);
	} 
	

	public ArrayList<Double> pillarDeflection () { // This method should calculate deflection.
			
		deflection = new ArrayList <Double>();
		
		for (int i =0; i< rows; i++) {
			
			double d = Math.sqrt ((Math.pow (dx.get(i), 2)) + (Math.pow (dy.get(i), 2)));
			deflection.add(d);
		}
		//System.out.println(deflection);
		return deflection;
	}

	
	public ArrayList<Double> nanoMeters () { // This method should calculate nanometers

		nanometers = new ArrayList <Double>();
		
		for (int i=0; i<rows;i++) {
			
		double nm = deflection.get(i) * 73;
		nanometers.add(nm);
		}
		//System.out.println(nanometers);
		return nanometers;
	}
	
	public Object [][] newDataArray () {
		
		int columns = 10;
		newData = new Object [rows][columns];
		//newData = new String [rows][columns];
		
		for (int i = 0; i < rows; i++) {
			
			newData [i][0] = frame.get(i);
			newData [i][1] = trajectory.get(i);
			newData [i][2] = x.get(i);
			newData [i][3] = y.get(i);
			newData [i][4] = x_raw.get(i);
			newData [i][5] = y_raw.get(i);
			newData [i][6] = dx.get(i);
			newData [i][7] = dy.get(i);
			newData [i][8] = deflection.get(i);
			newData [i][9] = nanometers.get(i);		
			System.out.println("Here is newData: " + newData[i][8]);
		}
		
		return newData;
	}
	
	
	public void getAverages () {
		
		double something = 0.0;
		average = 0;
		standardDeviation = 0;
		
		for (int i = 0; i < rows; i++) {
			
		if () {
		
			average = something/rows;
			System.out.println("Averages: " + "\n" + String.format("%.4f",average));
			}
		else {
			System.out.println("We're missing something here");
		}
		}
		
	}
	
	public String outputString () { // For the reader.
		
		StringBuilder SB = new StringBuilder();
		for (int i = 0; i<rows; i++) {
			double x = dx.get(i);
			double y = dy.get(i);
			String dxVal = String.format("%.9f", x);
			String dyVal = String.format("%.9f", y);
			SB.append (String.format ("%3s", frame.get(i)) + "\t\t" + String.format("%2.2s", trajectory.get(i)) + "\t" + String.format("%12.23s", dxVal) + "\t" 
			+ String.format("%12.13s", dyVal) + "\t" + String.format(" %1.11s",deflection.get(i)) + "\t" + String.format(" %1.11s",  nanometers.get(i)) + "\n");
		}
		
		String output = SB.toString();
		//System.out.println(output);
		return output;		
	}
	
	
	public String outputText () { //For a pretty text file.
		
		String header = (String.format("%s %15s %9s %15s %19s %15s", "frame", "trajectory", "dx", "dy", "deflection", "nanometers")+"\n");
		String bar = "-------------------------------------------------------------------------------------";
		String finalHeader = header + "\n" + bar + "\n" +" ";
		StringBuilder SB = new StringBuilder();
		SB.append(finalHeader);
		for (int i = 0; i<rows; i++) {
			double x = dx.get(i);
			double y = dy.get(i);
			String dxVal = String.format("%.9f", x);
			String dyVal = String.format("%.9f", y);
			SB.append (String.format ("%3s", frame.get(i)) + "\t\t" + String.format("%2.2s", trajectory.get(i)) + "\t" + String.format("%12.23s", dxVal) + "\t" 
			+ String.format("%12.13s", dyVal) + "\t" + String.format(" %1.11s",deflection.get(i)) + "\t" + String.format(" %1.11s",  nanometers.get(i)) + "\n");
		}
		String outputText = SB.toString();
		return outputText;
	}
	
	
	public String outputFile () { // For a CSV file.
		
		String header = ("frame" + "," + "trajectory" + "," + "dx" + "," + "dy" + "," + "deflection" + "," + "nanometers"+ ",");
		StringBuilder SB = new StringBuilder();
		SB.append(header + "\n");
		for (int i = 0; i<rows; i++) {
			double x = dx.get(i);
			double y = dy.get(i);
			String dxVal = String.format("%.9f", x);
			String dyVal = String.format("%.9f", y);
			SB.append (frame.get(i) + "," + trajectory.get(i) + "," + dxVal + "," + dyVal + "," + deflection.get(i) + "," + nanometers.get(i) + "\n");
		}
		String outputFile = SB.toString();
		return outputFile;
	}
}