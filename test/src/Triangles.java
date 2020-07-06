import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Triangles {
	public final static int mod = (int)(1e9+7);
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("triangles.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		int n = Integer.parseInt(reader.readLine());
		StringTokenizer st;
		ArrayList<Integer>[] x = new ArrayList[(int) (2e4+1)];
		ArrayList<Integer>[] y = new ArrayList[(int) (2e4+1)];
		for (int i=0; i<(int)(2e4+1); i++){
			x[i] = new ArrayList<Integer>();
			y[i] = new ArrayList<Integer>();
		}
		Point[] points = new Point[n];
		for (int i=0; i<n; i++){
			st = new StringTokenizer(reader.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			x[x1+(int)1e4].add(y1);
			y[y1+(int)1e4].add(x1);
			points[i] = new Point(x1, y1);
		}
		reader.close();
		
		//gather other points
		for (int i=0; i<n; i++){
			for (int j: x[points[i].x+(int)1e4]){
				if (points[i].y != j){points[i].xSum += Math.abs(j-points[i].y);}
			}
			for (int j: y[points[i].y+(int)1e4]){
				if (points[i].x != j){points[i].ySum += Math.abs(j-points[i].x);}
			}
		}
		//count area of triangles
		long ans = 0;
		for (int i=0; i<n; i++){
			ans += (points[i].xSum * points[i].ySum) % mod;
			ans %= mod;
		}
		writer.println(ans);
		writer.close();
	}
	public static class Point {
		public int x,y;
		public long ySum,xSum;
		public ArrayList<Integer> xNeighbor, yNeighbor;
		public Point (int x, int y){
			this.x = x;
			this.y = y;
			xNeighbor = new ArrayList<Integer>();
			xSum = 0;
			ySum = 0;
		}
	}
}
