import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class MooyoMooyo1 {
	public static boolean changed = true;
	public static int[][] grid;
	public static boolean[][] traversed;
	public static int n,k;
	public static final int[] dx = {-1,1,0,0};
	public static final int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("mooyomooyo.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		grid = new int[n][10];
		traversed = new boolean[n][10];
		for (int i=0;i<n;i++){
			String row = reader.readLine();
			for (int j=0;j<10;j++){
				if (!(row.charAt(j)=='0')){
					grid[i][j] = Character.getNumericValue(row.charAt(j));
				}
			}
		}
		reader.close();
		while (changed){
			changed = false;
			delCon();
			gravity();
			traversed = new boolean[n][10];
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("mooyomooyo.out"));
		for (int i=0;i<n;i++){
			for (int j=0;j<10;j++){
				writer.write(Integer.toString(grid[i][j]));
			}
			writer.write("\n");
		}
		writer.close();
	}
	public static void delCon(){
		for (int i=0;i<n;i++){
			for (int j=0;j<10;j++){
				if (grid[i][j]!=0 && !traversed[i][j]){
					ArrayList<Pair> con = floodfill(i,j,grid[i][j]);
					if (con.size()>=k){
						changed = true;
						for (Pair k:con){
							grid[k.x][k.y]=0;
						}
					}
				}
			}
		}
	}
	public static ArrayList<Pair> floodfill(int x,int y,int find){
		ArrayList<Pair> ans = new ArrayList<Pair>();
		ans.add(new Pair(x,y));
		traversed[x][y] = true;
		ArrayList<Pair> toAdd = new ArrayList<Pair>();
		for (int i=0;i<4;i++){
			int fx = x+dx[i]; int fy = y+dy[i]; 
			if (fx>=0 && fy>=0 && fx<n && fy<10 && !traversed[fx][fy] && grid[fx][fy]==find){
				toAdd = floodfill(fx,fy,find);
			}
			for (Pair j:toAdd){
				ans.add(j);
			}
			toAdd.clear();
		}
		return ans;
	}
	public static void gravity(){
		for (int i=0;i<10;i++){
			int moveDown = 0;
			for (int j=n-1;j>=0;j--){
				if (grid[j][i]==0){
					moveDown++;
				} else if (moveDown!=0) {
					grid[j+moveDown][i] = grid[j][i];
					grid[j][i] = 0;
				}
			}
		}
	}
	public static class Pair{
		public int x,y;
		public Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
}
