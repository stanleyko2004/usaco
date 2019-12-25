import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Maxcross {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[] lights = new int[n];
		for (int i=0;i<b;i++){
			lights[Integer.parseInt(reader.readLine())-1]=1;
		}
		int cur = 0;
		//start
		for (int i=0;i<k;i++){
			cur += lights[i];
		}
		int best = cur;
		//continue
		for (int i=k;i<n;i++){
			cur += lights[i];
			cur -= lights[i-k];
			best = Math.min(best, cur);
		}
		writer.println(best);
		writer.close();
		reader.close();
	}
}
