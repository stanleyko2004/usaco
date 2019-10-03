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
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Cowjump {
	public static point[] grid;
	public static ArrayList<point> activeSet;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("cowjump.in"));
		int n = Integer.parseInt(reader.readLine());
		grid = new point[2*n];
		for (int i=0;i<n;i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			if (x1<x2){
				grid[2*i] = new point(i,x1,y1,true);
				grid[2*i+1] = new point(i,x2,y2,false);
			} else {
				grid[2*i] = new point(i,x2,y2,true);
				grid[2*i+1] = new point(i,x1,y1,false);
			}
		}
		Arrays.sort(grid);
		reader.close();
		//sweep line
		activeSet = new ArrayList<point>();
		for (point i:grid){
			if (!i.start){
				int ind = del(i.id);
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("cowjump.out"));
		writer.write(Integer.toString(low));
		writer.close();
	}
	public static class point implements Comparable<point>{
		public int id,x,y;
		public boolean start;
		public point(int id,int x,int y,boolean start){
			this.id = id;
			this.x = x;
			this.y = y;
			this.start = start;
		}
		public int compareTo(point other){
			return other.x-this.x;
		}
	}
	public static int del(int id){
		for (int i=0;i<activeSet.size();i++){
			if (activeSet.get(i).id==id){
				activeSet.remove(i);
				return i;
			}
		}
		return 0;
	}
	public static boolean intersect(int s1,int e1,int s2,int e2){
		if 
	}
}
