import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Shortcut {
	public static Node[] fields;
	public static boolean[] hit;
	public static int n,m,t;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("shortcut.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		fields = new Node[n];
		hit = new boolean[n];
		st = new StringTokenizer(reader.readLine());
		for (int i=0;i<n;i++){
			fields[i] = new Node(Integer.parseInt(st.nextToken()));
		}
		for (int i=0;i<m;i++){
			st = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			fields[a-1].neighbours.add(new Pair(b-1,T));
			if (a!=1){
				fields[b-1].neighbours.add(new Pair(a-1,T));
			}
		}
		dijkstra();
		fields[0].to.remove(0);
		affected(0);
		int maxInd = 0;
		long maxTime = 0;
		for (int i=1;i<n;i++){
			long totalTime = fields[i].affected*fields[i].sp;
			if (totalTime>maxTime){
				maxTime = totalTime;
				maxInd = i;
			}
		}
		long ans = fields[maxInd].affected*(fields[maxInd].sp-t);
		ans = ans>=0 ? ans : 0;
		BufferedWriter writer = new BufferedWriter(new FileWriter("shortcut.out"));
		writer.write(Long.toString(ans));
		writer.close();
		reader.close();
	}
	public static class Node{
		public long quant,sp,affected;
		public ArrayList<Pair> neighbours;
		public ArrayList<Integer> to;
		public Node(int quant){
			this.quant = quant;
			neighbours = new ArrayList<Pair>();
			to = new ArrayList<Integer>();
			affected = -1;
		}
		public void addSP(int shortestPath){
			sp = shortestPath;
		}
	}
	public static class Pair implements Comparable<Pair>{
		public int x,y,from;
		public Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
		public int compareTo(Pair other){
			if (this.y!=other.y){
				return this.y-other.y;
			}
			return this.from-other.from;
		}
	}
	public static void dijkstra(){
		PriorityQueue<Pair> neighbors = new PriorityQueue<Pair>();
		neighbors.add(new Pair(0,0));
		fields[0].addSP(0);
		for (int i=0;i<n;i++){
			Pair next = neighbors.poll();
			while (hit[next.x]){
				next = neighbors.poll();
			}
			hit[next.x] = true;
			fields[next.x].addSP(next.y);
			fields[next.from].to.add(next.x);
			for (Pair j:fields[next.x].neighbours){
				Pair a = new Pair(j.x,j.y+next.y);
				a.from = next.x;
				neighbors.add(a);
			}
		}
	}
	public static long affected(int cur){
		fields[cur].affected = fields[cur].quant;
		for (int i:fields[cur].to){
			fields[cur].affected += affected(i);
		}
		return fields[cur].affected;
	}
}
