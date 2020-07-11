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

public class MilkOrder {
	public static int n, m;
	public static boolean[] traversed;
	public static int[][] obv;
	public static Node[] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File("milkorder.in")));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		obv = new int[m][];
		for (int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int mi = Integer.parseInt(st.nextToken());
			obv[i] = new int[mi];
			for (int j=0; j<mi; j++){
				obv[i][j] = Integer.parseInt(st.nextToken())-1;
			}
		}
		br.close();
		int l = 0;
		int h = m+1;
		int x;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while (l<h-1){
			x = (l+h)/2;
			ArrayList<Integer> tempAns = topsort(x);
			if (tempAns.isEmpty()){
				h = x-1;
			} else {
				ans = tempAns;
				l = x;
			}
		}
		if (topsort(h).isEmpty()){
			ans = topsort(l);
		} else {
			ans = topsort(h);
		}
		pw.print(ans.get(0)+1);
		for (int i=1; i<n; i++){
			pw.print(" "+(ans.get(i)+1));
		}
		pw.close();
	}
	public static ArrayList<Integer> topsort(int x){
		graph = new Node[n];
		for (int i=0; i<n; i++){
			graph[i] = new Node();
		}
		for (int i=0; i<x; i++){
			for (int j=0; j<obv[i].length-1; j++){
				graph[obv[i][j]].out.add(obv[i][j+1]);
				graph[obv[i][j+1]].inDeg += 1;
			}
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i=0; i<n; i++){
			if (graph[i].inDeg == 0){
				pq.add(i);
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int ct = 0;
		while (!pq.isEmpty()){
			int next = pq.poll();
			ans.add(next);
			for (int i : graph[next].out){
				if (--graph[i].inDeg == 0){
					pq.add(i);
				}
			}
			ct++;
		}
		if (ct != n){
			ans.clear();
		}
		return ans;
	}
	public static class Node{
		public ArrayList<Integer> out;
		public int inDeg;
		public Node(){
			out = new ArrayList<Integer>();
			inDeg = 0;
		}
	}
}
