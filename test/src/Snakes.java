import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Snakes {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("snakes.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] dp = new int[k][n+1];
		int[] vals = new int[n];
		int sub = 0;
		st = new StringTokenizer(reader.readLine());
		int max = 0;
		for (int i=1;i<n+1;i++){
			vals[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, vals[i]);
			dp[i][0] = max*i;
			for (int j=0;j<=k;j++){
				dp[i][j] = Integer.MAX_VALUE;
				int temp = vals[i];
				for (int x=j;x>=0;x++){
					dp[i][j] = Math.max(dp[i][j], dp[x][j]+(i-x)*(temp));
					temp = Math.max(temp, vals[x]);
				}
			}
			sub += vals[i];
		}
		writer.print(dp[n][k]-sub);
		writer.close();
		reader.close();
	}
}
