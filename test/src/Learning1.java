import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Learning1 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("learning.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		Cow[] cows = new Cow[n];
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			cows[i] = new Cow(st.nextToken(),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(cows);
		int ans = 0;
		for (int i=0;i<n;i++){
			if (!cows[i].hasSpots){
				continue;
			}
			//set up range for spotted cows
			int low = -1;
			int high = 1000000001;
			if (i>0){
				low = (cows[i-1].weight+cows[i].weight)/2 + (cows[i-1].weight+cows[i].weight)%2;
			}
			if (i<n-1){
				high = (cows[i].weight+cows[i+1].weight)/2;
			}
			ans += intersections(low,high,a,b);
		}
		for (int i=1; i<n; i++){
			int mid = (cows[i-1].weight+cows[i].weight)/2;
			if (cows[i-1].hasSpots && cows[i].hasSpots && (cows[i-1].weight+cows[i].weight)%2==0 && a<=mid && mid <=b){
				ans--;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("learning.out"));
		writer.write(Integer.toString(ans));
		writer.close();
	}
	static class Cow implements Comparable<Cow>{
		public int weight;
		public boolean hasSpots;
		public Cow(String spots,int w){
			weight = w;
			if (spots.equals("S")){
				hasSpots = true;
			} else {
				hasSpots = false;
			}
		}
		public int compareTo(Cow other){
			return weight-other.weight;
		}
	}
	public static int intersections(int low1, int high1, int low2, int high2){
		int start = Math.max(low1, low2);
		int end = Math.min(high1, high2);
		return start <= end ? end-start+1 : 0;
	}
}