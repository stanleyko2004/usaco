import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Superbull {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new File("superbull.in"));
		int n = reader.nextInt();
		int[] ids = new int[n];
		for (int i=0;i<n;i++){
			ids[i] = reader.nextInt();
		}
		PriorityQueue<edge> edges = new PriorityQueue<edge>();
		for (int i=1;i<n;i++){
			edges.offer(new edge(0,i,ids[0]^ids[i]));
		}
		long ans = 0;
		boolean[] used = new boolean[n];
		used[0] = true; 
		int counter = 0;
		while (counter<n-1){
			edge cur = edges.poll();
			if (used[cur.v1] && used[cur.v2]) continue;
			int newVer = used[cur.v1] ? cur.v2 : cur.v1;
			ans += cur.val;
			used[newVer] = true;
			counter++;
			for (int i=0;i<n;i++){
				if (i==newVer) continue;
				edges.add(new edge(newVer,i,ids[newVer]^ids[i]));
			}
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("superbull.out"));
		writer.write(Long.toString(ans));
		writer.close();
		reader.close();
	}
	public static class edge implements Comparable<edge>{
		public int v1,v2,val;
		public edge(int ver1,int ver2,int value){
			v1 = ver1;
			v2 = ver2;
			val = value;
		}
		public int compareTo(edge other){
			return other.val-this.val;
		}
	}
}
