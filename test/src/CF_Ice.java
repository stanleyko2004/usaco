import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CF_Ice {
	public static int n,m,r2,c2;
	public static boolean[][] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new boolean[n][m];
		for (int i=0; i<n; i++){
			String s = br.readLine();
			for (int j=0; j<m; j++){
				graph[i][j] = s.charAt(j)=='X' ? false : true;
			}
		}
		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken())-1;
		int c1 = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		r2 = Integer.parseInt(st.nextToken())-1;
		c2 = Integer.parseInt(st.nextToken())-1;
		//ArrayList<Pair> temp = open(r2,c2);
		if (!(r1==r2 && c1==c2)){
			graph[r1][c1] = true;//if entrance right next to exit
		}
		if (graph[r2][c2] && open(r2,c2).size()<2){//if need to land twice
			System.out.println("NO");
			return;
		}
		if (!graph[r2][c2] && open(r2,c2).size()<1){//if need to land once
			System.out.println("NO");
			return;
		}
		graph[r2][c2] = true;//open can detect
		if (floodfill(r1,c1)){
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	public static ArrayList<Pair> open (int x, int y){//returns all open coords from x,y
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
	public static class Pair{
		public int x,y;
		public Pair (int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static boolean floodfill(int x, int y){
		if (x==r2 && y==c2){//hit destination
			return true;
		}
		boolean res = false;
		graph[x][y] = false;//prevent backtracking
		for (Pair i : open(x,y)){
			if (floodfill(i.x,i.y)){//path leads to destination
				res = true;
			}
		}
		return res;
	}
}
