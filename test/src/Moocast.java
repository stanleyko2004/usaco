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

public class Moocast {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("moocast.in"));
		int n = Integer.parseInt(reader.readLine());
		int[] x = new int[n];
		int[] y = new int[n];
		int[] p = new int[n];
		for (int i=0;i<n;i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
			p[i] = (int) Math.pow(Integer.parseInt(st.nextToken()),2);
		}
		reader.close();
		boolean[][] transmit = new boolean[n][n];
		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				int dis = ((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]));
				if (dis<=p[i]){
					transmit[i][j]=true;
				}
			}
		}
		int ans = 1;
		for (int i=0;i<n;i++){
			ans = Math.max(ans,dfs(i,new boolean[n],transmit));
		}
		BufferedWriter out = new BufferedWriter(new FileWriter("moocast.out"));
		out.write(Integer.toString(ans));
		out.close();
	}
	public static int dfs(int cur, boolean[] hear,boolean[][] transmit){
		if (hear[cur]){
			return 0;
		}
		hear[cur] = true;
		int ans = 1;
		for (int i=0;i<transmit[cur].length;i++){
			if (transmit[cur][i]){
				ans += dfs(i,hear,transmit);
			}
		}
		return ans;
	}
}
