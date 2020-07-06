import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Dirtraverse {
	public static f[] dirs;
	public static boolean[] traversed;
	public static int[] under;
	public static int files;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File("dirtraverse.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
		int n = Integer.parseInt(br.readLine());
		dirs = new f[n];
		for (int i=0; i<n; i++){
			dirs[i] = new f();
		}
		files = 0;
		for (int i=0; i<n; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			dirs[i].l = st.nextToken().length();
			int m = Integer.parseInt(st.nextToken());
			if (m==0){
				files++;
			}
			dirs[i].down = new int[m];
			for (int j=0; j<m; j++){
				int bottom = Integer.parseInt(st.nextToken())-1;
				dirs[i].down[j] = bottom;
				dirs[bottom].parent = i;
			}
		}
		br.close();
		//calculate how many files "under" node n
		traversed = new boolean[n];
		under = new int[n];
		for (int i=0; i<n; i++){
			if (!traversed[i]){
				dfsUnder(i);
			}
		}
		//dfs down first node
		dirs[0].weight = 0;
		dfs(0,-1);
		//dfs to all nodes
		dfs2(0, -1);
		long res = dirs[0].weight;
		for (int i=1; i<n; i++){
			res = Math.min(res, dirs[i].weight);
		}
		writer.println(res);
		writer.close();
	}
	public static class f{
		public int l, parent;
		public int[] down;
		public long weight;
		public f(){
			parent = -1;
		}
	}
	public static void dfs(int node, int prev){
		for (int i : dirs[node].down){
			if (prev != i){
				if (prev != -1){
					dirs[0].weight += under[i]*(1+dirs[i].l);//+1 for slash
				} else {
					dirs[0].weight += under[i]*dirs[i].l;
				}
				dfs(i, node);
			}
		}
		if (dirs[node].parent != prev){
			dirs[0].weight += 3*(under[dirs[node].parent]);
			dfs(dirs[node].parent, node);
		}
	}
	public static void dfs2(int node, int prev){
		for (int i : dirs[node].down){
			if (prev != i){
				dirs[i].weight = dirs[node].weight + 3*(files-under[i]) - (dirs[i].l+1)*under[i];
				dfs2(i, node);
			}
		}
		int par = dirs[node].parent;
		if (par != prev){
			dirs[par].weight = dirs[node].weight - 3*(files-under[node]) + (dirs[node].l+1)*under[node];
			dfs2(par, node);
		}
	}
	public static void dfsUnder(int node){
		traversed[node] = true;
		if (dirs[node].down.length==0){
			under[node] = 1;
			return;
		}
		for (int i : dirs[node].down){
			dfsUnder(i);
			under[node] += under[i];
		}
	}
}
