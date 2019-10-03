import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LightsOn {
	static ArrayList<Pair>[][] grid;
	static boolean[][] lights, visited;
	final static int[] rl = new int[]{-1,1,0,0};
	final static int[] ud = new int[]{0,0,1,-1};
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("lightson.in"));
		int n = reader.nextInt();
		int m = reader.nextInt();
		grid = new ArrayList[n+2][n+2];
		lights = new boolean[n+2][n+2];
		visited = new boolean[n+2][n+2];
		for (int i=0;i<n+2;i++){
			for (int j=0;j<n+2;j++){
				grid[i][j] = new ArrayList<Pair>();
			}
		}
		for (int i=0;i<m;i++){
			int x = reader.nextInt();
			int y = reader.nextInt();
			int a = reader.nextInt();
			int b = reader.nextInt();
			grid[x][y].add(new Pair(a,b));
		}
		lights[1][1] = true;
		expand(1,1);
		int ans = 0;
		for (boolean[] arr:lights){
			for (boolean light:arr){
				if (light){
					ans++;
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("lightson.out"));
		writer.write(Integer.toString(ans));
		writer.close();
	}
	public static void expand(int x,int y){
		if (visited[x][y]) {return;}
		visited[x][y] = true;
		for (Pair i:grid[x][y]){
			if (!lights[i.x][i.y]){
				lights[i.x][i.y] = true;
				if (hasVisN(i.x,i.y)){
					expand(i.x,i.y);
				}
			}	
		}
		for (int i=0;i<4;i++){
			int newX = x+rl[i];
			int newY = y+ud[i];
			if (lights[newX][newY]){
				expand(newX,newY);
			}
		}
	}
	public static boolean hasVisN (int x,int y){
		for (int i=0;i<4;i++){
			if (visited[x+rl[i]][y+ud[i]] && lights[x+rl[i]][y+ud[i]]){
				return true;
			}
		}
		return false;
	}
	public static class Pair {
		public int x,y;
		public Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
}
