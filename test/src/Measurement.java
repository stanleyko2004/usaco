import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class Measurement {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("measurement.in"));
		int n = reader.nextInt();
		int g = reader.nextInt();
		measurement[] measurements = new measurement[n];
		TreeMap<Integer,Integer> ids = new TreeMap<Integer,Integer>();
		for (int i=0;i<n;i++){
			int time = reader.nextInt();
			int id = reader.nextInt();
			ids.put(id, g);
//			measurements[i] = new measurement(time,id,reader.nextInt());
		}
		Arrays.sort(measurements);
		for (int i=0;i<n;i++){
			measurement quant = measurements[i];
			ids.put(quant.id,ids.get(quant.id)+quant.diff);
			
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("measurement.out"));
		writer.write(Integer.toString(1));
		writer.close();
	}
	public class measurement implements Comparable<measurement>{
		public int time, id, diff;
		public measurement(int time,int id,int diff){
			this.time = time;
			this.id = id;
			this.diff = diff;
		}
		public int compareTo(measurement other){
			return this.time-other.time;
		}
	}
}
