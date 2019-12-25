import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Pump {
	public static int n,m;
	public static Node[] points;
	public static final int max = 1001;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("pump.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		points = new Node[n];
		int maxF = -1;
		for (int i=0; i<m; i++){
			st = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int f = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			points[a].neighbours.add(new Edge(b,f,c));
			points[b].neighbours.add(new Edge(a,f,c));
			maxF = Math.max(maxF, f);
		}
		for (int i=0; i<maxF; i++){
			
		}
		writer.close();
		reader.close();
	}
	public static class Node{
		public ArrayList<Edge> neighbours;
		public Node(){
			neighbours = new ArrayList<Edge>();
		}
	}
	public static class Edge implements Comparable<Edge>{
		public int y,f,c;
		public Edge(int y,int f, int c){
			this.y = y;
			this.f = f;
			this.c = c;
		}
		public int compareTo(Edge other){
			return this.y-other.y;
		}
	}
	public static void dijkstra(){
		PriorityQueue<Edge> neighbors = new PriorityQueue<Edge>();
		
	}
}
