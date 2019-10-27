import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Cowjump {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("cowjump.in"));
		int n = Integer.parseInt(reader.readLine());
		point[] points = new point[2*n];
		line[] lines = new line[n];
		for (int i=0;i<n;i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			if (x1>x2){
				int temp = x1;
				x1 = x2;
				x2 = temp;
				temp = y1;
				y1 = y2;
				y2 = temp;
			}
			points[2*i] = new point(x1,y1,true,i);
			points[2*i+1] = new point(x2,y2,false,i);
			lines[i] = new line(x1,y1,x2,y2,i);
		}
		reader.close();
		Arrays.sort(points);
		ArrayList<line> active = new ArrayList<line>();
		for (int i=0;i<2*n;i++){
			point cur = points[i];
			if (cur.start){
				//find index to insert
				
			} else {
				active.remove(lines[cur.id]);
			}
		}
	}
	public static class line{
		public int x1,y1,x2,y2,lineId;
		public line(int x1,int y1,int x2,int y2,int id){
			lineId = id;
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	public static class point implements Comparable<point>{
		public int x,y,id;
		boolean start;
		public point(int x,int y,boolean start,int id){
			this.x = x;
			this.y = y;
			this.start = start;
			this.id = id;
		}
		public int compareTo(point other){
			return this.x-other.x;
		}
	}
}
