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
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Div7 {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("div7.in"));
		int n = reader.nextInt();
		int[] f = new int[7];
		int[] l = new int[7];
		Arrays.fill(f, Integer.MAX_VALUE);
		f[0] = 0;
		int cur = 0;
		for (int i=1;i<n+1;i++){
			cur += reader.nextInt();
			cur %= 7;
			f[cur] = Math.min(f[cur], i);
			l[cur] = i;
		}
		reader.close();
		int ans = 0;
		for (int i=0;i<7;i++){
			if (f[i]<=n){
				ans = Math.max(ans, l[i]-f[i]);
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("div7.out"));
		writer.write(Integer.toString(ans));
		writer.close();
	}
}
