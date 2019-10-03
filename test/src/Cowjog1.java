import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Cowjog1 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("cowjog.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		long t = Integer.parseInt(st.nextToken());
		long[] cows = new long[n];
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			long pos = Integer.parseInt(st.nextToken());
			long speed = Integer.parseInt(st.nextToken());
			cows[i] = pos+speed*t;
		}
		int ans = 1;
		long slowest = cows[n-1];
		for (int i=n-2;i>=0;i--){
			if (cows[i]<slowest){
				ans++;
			}
			slowest = Math.min(slowest, cows[i]);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("cowjog.out"));
		writer.write(Integer.toString(ans));
		writer.close();
		reader.close();
	}
}
