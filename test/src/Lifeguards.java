import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Lifeguards {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("lifeguards.in"));
		int n = reader.nextInt();
		point[] points = new point[2*n];
		interval[] intervals = new interval[n];
		for (int i=0;i<n;i++){
			int s = reader.nextInt();
			int e = reader.nextInt();
			intervals[i] = new interval(s,e);
			points[2*i] = new point(s,true,i);
			points[2*i+1] = new point(e,false,i);
		}
		reader.close();
		Arrays.sort(points);
		ArrayList<point> active = new ArrayList<point>();
		int cur = 0;
		int total = 0;
		int prev = points[0].loc;
		for (int i=0;i<2*n;i++){
			if (cur>0){
				total += points[i].loc-prev;
				prev = points[i].loc;
			}
			if (points[i].beg){
				if (cur==1){
					intervals[active.get(0).id].utility=points[i].loc-intervals[active.get(0).id].utilStart;
				}
				cur++;
				active.add(points[i]);
			} else {
				cur--;
				int searchID = points[i].id;
				for (int j=0;j<active.size();j++){
					if (searchID == active.get(j).id){
						if (cur==0){
							intervals[active.get(0).id].utility=prev-intervals[active.get(0).id].utilStart;
						}
						active.remove(j);
						break;
					}
				}
			}
			if (cur==1){
				intervals[active.get(0).id].utilStart=points[i].loc;
			}
		}
		int minUtil = Integer.MAX_VALUE;
		for (int i=0;i<n;i++){
			minUtil = Math.min(intervals[i].utility,minUtil);
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("lifeguards.out"));
		writer.write(Integer.toString(total-minUtil));
		writer.close();
	}
	public static class interval{
		public int s,e,utility,utilStart;
		public interval(int start,int end){
			s = start;
			e = end;
			utility = 0;
		}
	}
	public static class point implements Comparable<point>{
		public int loc, id;
		public boolean beg;
		public point(int location,boolean beginning,int Id){
			loc = location;
			beg = beginning;
			id = Id;
		}
		public int compareTo(point other){
			return this.loc-other.loc;
		}
	}
}
