import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Cowroute1 {
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("cowroute.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int a = Integer.parseInt(st.nextToken())-1;
		int b = Integer.parseInt(st.nextToken())-1;
		n = Integer.parseInt(st.nextToken());
		ArrayList[] edges = new ArrayList[1000];
		for (int i=0;i<1000;i++){
			edges[i] = new ArrayList<edge>();
		}
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			long cost = Long.parseLong(st.nextToken());
			int points = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(reader.readLine());
			int[] route = new int[points];
			for (int j=0;j<points;j++){
				route[j] = Integer.parseInt(st.nextToken())-1;
			}
			for (int j=0;j<points;j++){
				for (int k=j+1;k<points;k++){
					edges[route[j]].add(new edge(route[k],1000000L*cost+k-j));
				}
			}
		}
		long ans = dijkstra(edges,a,b);
		long cost = ans>0 ? ans/1000000L : -1;
		long nf = ans>0 ? ans%1000000L : -1;
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));
		writer.write(cost+" "+nf);
		writer.close();
		reader.close();
	}
	static class edge implements Comparable<edge>{
		public int to;
		public long cn;//cost and number of flights
		public edge (int dest,long cosFli){
			to = dest;
			cn = cosFli;
		}
		public int compareTo(edge other){
			if (this.cn>other.cn) return 1;
			if (this.cn<other.cn) return -1;
			return 0;
		}
	}
	public static long dijkstra(ArrayList[] edgeL,int start, int end){
		PriorityQueue<edge> edges = new PriorityQueue<edge>();
		long[] dist = new long[1000];
		boolean[] used = new boolean[1000];
		Arrays.fill(dist, 1000000000000000000L);
		dist[start] = 0;
		edges.offer(new edge(start, 0));
		while (edges.size() > 0) {
			edge cur = edges.poll();
			if (used[cur.to]){
				continue;
			}
			used[cur.to] = true;
			if (cur.to == end){
				return cur.cn;
			}
			for (edge next: (ArrayList<edge>)edgeL[cur.to]) {
				if (dist[cur.to] + next.cn < dist[next.to]) {
					dist[next.to] = dist[cur.to] + next.cn;
					edges.offer(new edge(next.to, dist[next.to]));
				}
			}
		}
		return -1;
	}
}

	