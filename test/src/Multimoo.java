import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Multimoo {
	public static int n;
	public static boolean[][] traversed,vis;
	public static int[][] grid;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("multimoo.in"));
		n = Integer.parseInt(reader.readLine());
		traversed = new boolean[n][n];
		vis = new boolean[n][n];
		grid = new int[n][n];
		for (int i=0;i<n;i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			for (int j=0;j<n;j++){
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		reader.close();
		int SMax = 0;
		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				if (!traversed[i][j]){
					int cur = grid[i][j];
					ArrayList<Pair> full = floodfill(cur,i,j);
					SMax = Math.max(full.size(),SMax);
				}
			}
		}
		int groupmax = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				int groupsize = bfs(i, j, 1, 1, grid[i][j], -1);
				vis = new boolean[n][n];
				groupmax = Math.max(groupsize, groupmax);
				vis = new boolean[n][n];
			}
		}
		PrintWriter writer = new PrintWriter(new FileWriter("multimoo.out"));
		writer.write(SMax+"\n"+groupmax);
		writer.close();
	}
	public static boolean inb(int x,int y){
		return x>=0 && y>=0 && x<n && y<n;
	}
	public static int bfs(int x, int y, int dist, int numregions, int firstid, int secondid){
		vis[x][y] = true;
		for(int i = 0; i < 4; i++){
			int sx = x+dx[i];
			int sy = y+dy[i];
			
			if(inb(sx, sy) && !vis[sx][sy]){
				if(secondid==-1){
					secondid = grid[sx][sy];
					vis[sx][sy] = true;
					dist = Math.max(dist, bfs(sx, sy, dist+1, numregions+1, firstid, grid[sx][sy]));
				}
				else if(grid[sx][sy] == firstid || grid[sx][sy] == secondid){
					vis[sx][sy] = true;
					dist=Math.max(dist, bfs(sx, sy, dist+1, numregions, firstid, secondid));
				}
			}
		}
		return dist;
	}
	public static ArrayList<Pair> floodfill(int target,int x,int y){
		traversed[x][y] = true;
		ArrayList<Pair> total = new ArrayList<Pair>();
		total.add(new Pair(x,y));
		if (x!=0 && !(traversed[x-1][y]) && grid[x-1][y]==target){
			total.addAll(floodfill(target,x-1,y));
		}
		if (x!=n-1 && !(traversed[x+1][y]) && grid[x+1][y]==target){
			total.addAll(floodfill(target,x+1,y));
		}
		if (y!=0 && !(traversed[x][y-1]) && grid[x][y-1]==target){
			total.addAll(floodfill(target,x,y-1));
		}
		if (y!=n-1 && !(traversed[x][y+1]) && grid[x][y+1]==target){
			total.addAll(floodfill(target,x,y+1));
		}
		return total;
	}
	public static class Pair{
		public int x,y;
		public Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
}