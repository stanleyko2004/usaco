import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Atlarge {
	public final static int INF = 1000000001;
	public static int n, k;
	public static Node[] graph;
	public static boolean[] traversed;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("atlarge.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken())-1;
		graph = new Node[n];
		for (int i=0; i<n; i++){
			graph[i] = new Node();
		}
		for (int i=0; i<n-1; i++){
			st = new StringTokenizer(reader.readLine());
			int p = Integer.parseInt(st.nextToken())-1;
			int q = Integer.parseInt(st.nextToken())-1;
			graph[p].neighbors.add(q);
			graph[q].neighbors.add(p);
		}
		reader.close();
		//bfs from the leaves
		Queue<Edge> next = new LinkedList<Edge>();
		traversed = new boolean[n];
		for (int i=0; i<n; i++){
			if (graph[i].neighbors.size()==1){
				next.add(new Edge(0,i));
				graph[i].fromL = 0;
				traversed[i] = true;
			}
		}
		while (!next.isEmpty()){
			Edge e = next.poll();
			graph[e.y].fromL = e.x;
			for (int i : graph[e.y].neighbors){
				if (!traversed[i]){
					next.add(new Edge(e.x+1, i));
					traversed[i] = true;
				}
			}
		}
		//bfs from cow
		next = new LinkedList<Edge>();
		traversed = new boolean[n];
		next.add(new Edge(0,k));
		traversed[k] = true;
		while (!next.isEmpty()){
			Edge e = next.poll();
			graph[e.y].fromC = e.x;
			for (int i : graph[e.y].neighbors){
				if (!traversed[i]){
					next.add(new Edge(e.x+1, i));
					traversed[i] = true;
				}
			}
		}
		//dfs from cow
		traversed = new boolean[n];
		int ans = dfs(k);
		writer.println(ans);
		writer.close();
	}
	public static class Edge{
		public int x,y;
		public Edge(int x, int y){
			this.x = x;//weight
			this.y = y;//node
		}
	}
	public static class Node{
		public ArrayList<Integer> neighbors;
		public int fromL, fromC;
		public Node(){
			neighbors = new ArrayList<Integer>();
		}
	}
	public static int dfs(int source){
		if (graph[source].fromL <= graph[source].fromC){
			return 1;
		}
		traversed[source] = true;
		int ans = 0;
		for (int i : graph[source].neighbors){
			if (!traversed[i]){
				ans += dfs(i);
			}
		}
		return ans;
	}
}
