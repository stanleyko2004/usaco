import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class CF_Monopole {
	public final static int mxN = (int) 1e3;
	public final static int mxM = (int) 1e3;
	public static boolean[][] graph = new boolean[mxN][mxM];
	public static boolean[][] traversed = new boolean[mxN][mxM];
	public static int n,m;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new boolean[n][m];
		for (int i=0; i<n; i++){
			String s = br.readLine();
			for (int j=0; j<m; j++){
				graph[i][j] = s.charAt(j)=='.' ? false : true;
			}
		}
		//make sure not more than one north per row
		for (int i=0; i<n; i++){
			boolean prev = graph[i][0];
			boolean encountered = prev;
			for (int j=1; j<m; j++){
				if (graph[i][j] != prev){//theres a change
					if (graph[i][j]){//white -> black
						if (!encountered){
							encountered = true;
						} else {
							System.out.println("-1");
							return;
						}
					}
				}
				prev = graph[i][j];
			}
		}
		for (int i=0; i<m; i++){
			boolean prev = graph[0][i];
			boolean encountered = prev;
			for (int j=1; j<n; j++){
				if (graph[j][i] != prev){//theres a change
					if (graph[j][i]){//white -> black
						if (!encountered){
							encountered = true;
						} else {
							System.out.println("-1");
							return;
						}
					}
				}
				prev = graph[j][i];
			}
		}
		//make sure blacks have a spot on every row
		boolean[] rows = new boolean[n];
		boolean[] cols = new boolean[m];
		for (int i=0; i<n; i++){
			for (int j=0; j<m; j++){
				if (graph[i][j]){
					rows[i] = true;
					cols[j] = true;
				}
			}
		}
		boolean allRowsB = true;
		boolean allColsB = true;
		for (boolean i : rows){
			if (!i){
				allRowsB = false;
				break;
			}
		}
		for (boolean i : cols){
			if (!i){
				allColsB = false;
			}
		}
		if (allRowsB != allColsB){
			System.out.println("-1");
			return;
		}
		//find cc
		traversed = new boolean[n][m];
		int c = 0;
		for (int i=0; i<n; i++){
			for (int j=0; j<m; j++){
				if (!traversed[i][j] && graph[i][j]){
					floodfill(i,j);
					c++;
				}
			}
		}
		System.out.println(c);;
	}
	public static ArrayList<Pair> open(int x, int y){
		ArrayList<Pair> res = new ArrayList<Pair>();
		int[] dx = new int[]{-1,0,0,1};
		int[] dy = new int[]{0,-1,1,0};
		for (int i=0; i<4; i++){
			if (x+dx[i]>=0 && x+dx[i]<n && y+dy[i]>=0 && y+dy[i]<m){//check in bound
				if (graph[x+dx[i]][y+dy[i]]){
					res.add(new Pair(x+dx[i],y+dy[i]));
				}
			}
		}
		return res;
	}
	public static void floodfill(int x, int y){
		traversed[x][y] = true;
		Stack<Pair> s = new Stack<Pair>();
		s.push(new Pair(x,y));
		while (!s.isEmpty()){
			Pair p = s.pop();
			traversed[p.x][p.y] = true;
			for (Pair i : open(p.x, p.y)){
				if (!traversed[i.x][i.y]){
					s.push(new Pair(i.x, i.y));
				}
			}
		}
	}
	public static class Pair{
		public int x,y;
		public Pair (int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
