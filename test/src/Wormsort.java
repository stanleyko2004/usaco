import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Wormsort {
	static int[] loc, lhs, rhs, weight;
	static LinkedList<Edge>[] edges;
	static int[] component;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		loc = new int[n];
		component = new int[n];
		edges = new LinkedList[n];
		for(int i = 0; i < n; i++){
			edges[i] = new LinkedList<>();
		}
		lhs = new int[m];
		rhs = new int[m];
		weight = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++){
			loc[i] = Integer.parseInt(st.nextToken())-1;
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, w));
			edges[b].add(new Edge(a, w));
		}
		br.close();
		int minW = 0;
		int maxW = 1000000001;
		while(minW != maxW) {
			int mid = (minW + maxW + 1) / 2;
			if(valid(mid)) minW = mid;
			else maxW = mid-1;
		}
		if(minW > 1e9) minW = -1;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
		pw.println(minW);
		pw.close();
	}
	private static void dfs(int curr, int label, int minW) {
		if(component[curr] == label){
			return;
		}
		component[curr] = label;
		for(Edge child: edges[curr]){
			if(child.w >= minW){
				dfs(child.d, label, minW);
			}
		}
	}
	private static boolean valid(int minW) {
		Arrays.fill(component, -1);
		int numcomps = 0;
		for(int i = 0; i < component.length; i++) {
			if(component[i] < 0) {
				dfs(i, numcomps++, minW);
			}
		}
		for(int i = 0; i < loc.length; i++) {
			if(component[i] != component[loc[i]]){
				return false;
			}
		}
		return true;
  }
	static class Edge {
	  int d, w;
	  public Edge(int d, int w) {
		  this.d = d;
		  this.w = w;
	  }
	}
}