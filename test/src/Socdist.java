import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Socdist {
	public static long n,m;
	public static interval[] grass;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("socdist.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Long.parseLong(st.nextToken());
		m = Long.parseLong(st.nextToken());
		grass = new interval[(int) m];
		for (int i=0; i<m; i++){
			st = new StringTokenizer(reader.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			grass[i] = new interval(a,b);
		}
		Arrays.sort(grass);
		writer.println(bsearch());
		writer.close();
	}
	public static long bsearch(){
		long low = 1;
		long high = (long) 1e18;
		while (low < high){
			long mid = (low + high + 1) / 2;
			if (test(mid)){
				low = mid;
			} else {
				high = mid-1;
			}
		}
		return low;
	}
	public static boolean test(long d){
		long prev = (long) -1e18;
		long cows = 0;
		for (int i=0; i<m; i++){
			while (Math.max(prev + d, grass[i].a) <= grass[i].b){
				prev = Math.max(prev + d, grass[i].a);
				cows++;
				if (cows >= n) return true;
			}
			if (cows >= n) return true;
		}
		return cows >= n;
	}
	public static class interval implements Comparable<interval>{
		public long a,b;
		public interval(long a, long b){
			this.a = a;
			this.b = b;
		}
		public int compareTo(interval other){
			return other.a < a ? 1 : -1;
		}
	}
}
