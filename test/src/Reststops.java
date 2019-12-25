import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Reststops {
	public static int[] x = new int[1000000];
	public static int[] c = new int[1000000];
	public static boolean[] max = new boolean[1000000];
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("reststops.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int l = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int rf = Integer.parseInt(st.nextToken());
		int rb = Integer.parseInt(st.nextToken());
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			x[i] = Integer.parseInt(st.nextToken());			
			c[i] = Integer.parseInt(st.nextToken());
		}
		reader.close();
		int curMax = 0;
		for (int i=n-1;i>=0;i--){
			if(c[i]>curMax){
				max[i] = true;
				curMax = c[i];
			}
		}
		long ans = 0L;
		long f = 0L;
		long b = 0L;
		int last = 0;
		for (int i=0;i<n;i++){
			if (max[i]){
				f += (x[i] - last)*((long)rf);
				b += (x[i] - last)*((long)rb);
				ans += (f-b)*((long)c[i]);
				b=f;
				last = x[i];
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("reststops.out"));
		writer.write(Long.toString(ans));
		writer.close();
	}
}
