import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Teamwork {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("teamwork.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] skills = new int[n];
		for (int i=0;i<n;i++){
			skills[i] = Integer.parseInt(reader.readLine());
		}
		reader.close();
		int[] dp = new int[n];
		dp[0] = skills[0];
		for (int i=1;i<n;i++){
			int max = skills[i];
			for (int j=i;j>=0 && i-j+1<=k;j--){
				max = Math.max(max,skills[j]);
				if (j==0){
					dp[i] = Math.max(dp[i], max*(i-j+1));
				} else {
					dp[i] = Math.max(dp[i], dp[j-1]+max*(i-j+1));
				}
			}
		}
		PrintWriter writer = new PrintWriter(new FileWriter("teamwork.out"));
		writer.write(dp[n-1]+"\n");
		writer.close();
	}	
}