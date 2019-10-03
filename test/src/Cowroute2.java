import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Cowroute2 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("cowroute.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] routes = new int[n][];
		int[] costs = new int[n];
		int ans = 10000;
		int[] from = new int[10001];
		Arrays.fill(from, 10000);
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			int cost = Integer.parseInt(st.nextToken());
			costs[i] = cost;
			int numCit = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(reader.readLine());
			routes[i] = new int[numCit];
			boolean found = false;
			for (int j=0;j<numCit;j++){
				int city = Integer.parseInt(st.nextToken());
				routes[i][j] = city;
				if (city==a){
					found = true;
				} else if (found){
					from[city] = Math.min(cost, from[city]);
					if (city==b){
						ans = Math.min(ans, cost);
					}
				}
				
			}
		}
		int[] to = new int[10001];
		Arrays.fill(to, 10000);
		for (int i=0;i<n;i++){
			boolean found = false;
			for (int j=routes[i].length-1;j>-1;j--){
				int city = routes[i][j];
				if (found){
					to[city]=Math.min(to[city], costs[i]);
				} else if (city==b){
					found = true;
				}
			}
		}
		for (int i=0;i<10001;i++){
			if (i==a || i==b){
				continue;
			}
			ans = Math.min(ans, from[i]+to[i]);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("cowroute.out"));
		if (ans >= 10000){
			writer.write("-1");
		} else {
			writer.write(Integer.toString(ans));
		}
		writer.close();
	}
}
