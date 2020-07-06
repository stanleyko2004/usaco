import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Mootube {
	public final static int INF = 1000000001;
	public static int n;
	public static Node[] graph;
	public static boolean[] traversed;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("mootube.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		graph = new Node[n];
		traversed = new boolean[n];
		for (int i=0; i<n; i++){
			graph[i] = new Node();
		}
		for (int i=0; i<n-1; i++){
			st = new StringTokenizer(reader.readLine());
			int p = Integer.parseInt(st.nextToken())-1;
			int q = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken());
			graph[p].neighbors.add(new Edge(q,r));
			graph[q].neighbors.add(new Edge(p,r));
		}
		for (int i=0; i<Q; i++){
			st = new StringTokenizer(reader.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken())-1;
			writer.println(s(v,k));
		}
		writer.close();
	}
	public static class Node{
		public ArrayList<Edge> neighbors;
		public Node(){
			neighbors = new ArrayList<Edge>();
		}
	}
	public static class Edge implements Comparable<Edge>{//Comparable Edge: lightest edge has higher priority
		public int y,w;
		public Edge(int y, int w){
			this.y = y;
			this.w = w;
		}
		public int compareTo(Edge other){
			return other.w-w;
		}
	}
	public static int s(int v, int k){
		PriorityQueue<Edge> next = new PriorityQueue<Edge>();
		next.add(new Edge(v,INF));
		traversed = new boolean[n];
		traversed[v] = true;
		int ct = 0;
		while (!next.isEmpty() && next.peek().w >= k){
			ct++;
			traversed[next.peek().y] = true;
			for (Edge i : graph[next.poll().y].neighbors){
				if (!traversed[i.y]){
					next.add(i);
				}
			}
		}
		return ct-1;
	}
}
