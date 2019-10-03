import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cowroute {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("cowroute.in"));
		String ABN = reader.readLine();
		String[] abn = ABN.split(" ");
		String a = abn[0];
		String b = abn[1];
		int n = Integer.parseInt(abn[2]);
		boolean found = false;
		String pn;//price and number of points
		String p;//points
		int[] cost = new int[n];
		String[] pts;
		List<List<Integer>> points = new ArrayList<List<Integer>>();
		List<Integer> singlePts = new ArrayList<Integer>();
		for (int i=0;i<n;i++){
			pn = reader.readLine();
			cost[i] = (Integer.parseInt(pn.split(" ")[0]));
			p = reader.readLine();
			pts = p.split(" ");
			for (String j:pts){
				singlePts.add(Integer.parseInt(j));
			}
			points.add(singlePts);
		}
//		int result = cowroute(points, cost);
		BufferedWriter writer = new BufferedWriter(new FileWriter("cowroute.out"));
//		writer.write(Integer.toString(result));
		writer.close();
	}
	public static void cowroute(List<List<Integer>> p, int[] c){
		int min = 2001;
		for (int i=0; i<p.size();i++){
			
		}
	}
}
