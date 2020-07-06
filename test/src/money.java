import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class money {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("money.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int v = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(reader.readLine());
		int[] vals = new int[v];
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) {
            st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                vals[i] = Integer.parseInt(st.nextToken());
                i++;
            }
        }
		reader.close();
		long[][] dp = new long[n+1][v+1];
		for(i = 0; i <= n;i++)
			Arrays.fill(dp[i],-1);	
		
		for(int sum = 0; sum <= n;sum++)
		{
			for(int max = v; max >= 0;max--)
			{
				if(sum == 0) dp[sum][max] = 1;
				else
				{
					long ways = 0;
					for(i = max; i < v;i++)
					{						
						if(sum-vals[i] < 0) continue;
						ways += dp[sum-vals	[i]][i];
					}
					dp[sum][max] = ways;
				}
			}
		}
		
		writer.println(dp[n][0]+'\n');
		writer.close();
	}
}
