import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Triangles2 {
	static int mod = (int) 1e9 + 7;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("triangles.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		int n = Integer.parseInt(reader.readLine());
		Point[] points = new Point[n];
		for (int i=0; i<n; i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(points, new XSort());
		ArrayList<Integer> nodePerX = new ArrayList<Integer>();
		for (int i=0; i<n; i++){// first vertical sums
			int prevX = points[i].x;
			int inc = 1;
			while (i+inc < n && points[i+inc].x == prevX){
				points[i].ySum += points[i].y-points[i+inc].y;
				inc++;
			}
			nodePerX.add(inc);
			i += inc-1;
		}
		int onX = 0;
		for (int i=0; i<n; i++){// all vertical sums
			int prevX = points[i].x;
			int inc = 1;
			while (i+inc < n && points[i+inc].x == prevX){
				points[i+inc].ySum = points[i+inc-1].ySum+(2*inc-nodePerX.get(onX))*(points[i+inc-1].y-points[i+inc].y);
				inc++;
			}
			onX++;
			i += inc-1;
		}
		Arrays.sort(points, new YSort());
		ArrayList<Integer> nodePerY = new ArrayList<Integer>();
		for (int i=0; i<n; i++){//first horizontal sums
			int prevY = points[i].y;
			int inc = 1;
			while (i+inc < n && points[i+inc].y == prevY){
				points[i].xSum += points[i].x-points[i+inc].x;
				inc++;
			}
			nodePerY.add(inc);
			i += inc-1;
		}
		int onY = 0;
		for (int i=0; i<n; i++){// all horizontal sums
			int prevY = points[i].y;
			int inc = 1;
			while (i+inc < n && points[i+inc].y == prevY){
				points[i+inc].xSum = points[i+inc-1].xSum+(2*inc-nodePerY.get(onY))*(points[i+inc-1].x-points[i+inc].x);
				inc++;
			}
			onY++;
			i += inc-1;
		}
		long res = 0;
		for (int i=0; i<n; i++){
			res += points[i].xSum*points[i].ySum;
			res %= mod;
		}
		writer.println(res);
		writer.close();
	}
	public static class Point{
		public int x,y;
		public long ySum,xSum;
		public Point (int x, int y){
			this.x = x;
			this.y = y;
			xSum = 0;
			ySum = 0;
		}
	}
	public static class XSort implements Comparator<Point>{
		public int compare(Point f, Point s) {
			return f.x==s.x ? s.y-f.y : f.x-s.x;
		}
	}
	public static class YSort implements Comparator<Point>{
		public int compare(Point f, Point s) {
			return f.y==s.y ? s.x-f.x : f.y-s.y;
		}
	}
}
