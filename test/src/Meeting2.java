import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Meeting2 {
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("meeting.in"));
		n = Integer.parseInt(reader.readLine());
		int m = Integer.parseInt(reader.readLine());
		ArrayList[] bessie = new ArrayList[n-1];
		ArrayList[] elsie = new ArrayList[n-1];
		for (int i=0;i<n-1;i++){
			bessie[i] = new ArrayList<Pair>();
			elsie[i] = new ArrayList<Pair>();
		}
		for (int i=0;i<m;i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			bessie[a].add(new Pair(b,c));
			elsie[a].add(new Pair(b,d));
		}
		reader.close();
		HashSet[] bessieCan = solve(bessie);
		HashSet[] elsieCan = solve(elsie);
		BufferedWriter writer = new BufferedWriter(new FileWriter("meeting.out"));
		
		writer.close();
	}
	public static class Pair{
		public int x,y;
		public Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static HashSet[] solve(ArrayList<Pair>[] cow){
		HashSet[] dp = new HashSet[n];
		for (int i=0;i<n;i++){
			dp[i] = new HashSet<Integer>();
		}
		dp[0].add(0);
		for (int i=0;i<n;i++){
			for (int j=0;j<dp[i].size();j++){
				Iterator hs = dp[j].iterator();
				for (int k=0;k<cow[i].size();k++){
					Pair cur = cow[i].get(k);
					//dp[cur.x].add(hs.next()+cur.y);
				}
			}
		}
		return dp;
	}
}
