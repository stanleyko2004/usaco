import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Piggyback {
	static int n;
	static ArrayList[] paths;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("piggyback.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int b = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		paths = new ArrayList[n];
		for (int i=0;i<n;i++){
			paths[i] = new ArrayList<Integer>();
		}
		for (int i=0;i<m;i++){
			st = new StringTokenizer(reader.readLine());
			int v1 = Integer.parseInt(st.nextToken())-1;
			int v2 = Integer.parseInt(st.nextToken())-1;
			paths[v1].add(v2);
			paths[v2].add(v1);
		}
		int[] bessie = bfs(0);
		int[] elsie = bfs(1);
		int[] barn = bfs(n-1);
		int res = Integer.MAX_VALUE;
		for (int i=0;i<n;i++){
			int te = b*bessie[i]+e*elsie[i]+p*barn[i];//total energy from given vertex
			if (te<res){
				res = te;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("piggyback.out"));
		writer.write(Integer.toString(res));
		writer.close();
	}
	public static int[] bfs(int start){
		int[] ans = new int[n];
		Arrays.fill(ans,-1);
		ans[start] = 0;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		while(queue.size()>0){
			int curr = queue.poll();
			for (Integer i:(ArrayList<Integer>)paths[curr]){
				if (ans[i]==-1){
					ans[i] = ans[curr]+1;
					queue.offer(i);
				}
			}
		}
		return ans;
	}
}