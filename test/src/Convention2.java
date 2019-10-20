import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Convention2 {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("convention2.in"));
		int n = reader.nextInt();
		cow[] cows = new cow[n];
		for (int i=0;i<n;i++){
			cows[i] = new cow(i,reader.nextInt(),reader.nextInt());
		}
		reader.close();
		Arrays.sort(cows,new byLoc());
		PriorityQueue<cow> waiting = new PriorityQueue<cow>(new bySen());
		int time = cows[0].l;
		int cur = 0;
		int maxW = 0;
		while (cur<n){
			if (cows[cur].l<time){
				waiting.offer(cows[cur]);
				cur++;
				continue;
			} else if (waiting.isEmpty()){
				waiting.offer(cows[cur]);
				time = cows[cur].l;
				cur++;
				while(cows[cur].l<=time){
					waiting.offer(cows[cur]);
					cur++;
				}
			}
			cow curCow = waiting.poll();
			maxW = Math.max(maxW, time-curCow.l);
			time += curCow.t;
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("convention2.out"));
		writer.write(Integer.toString(maxW));
		writer.close();
	}
	public static class cow{
		public int s,l,t;
		public cow(int seniority,int location,int time){
			s = seniority;
			l = location;
			t = time;
		}
	}
	public static class bySen implements Comparator<cow>{
		public int compare(cow c1, cow c2){
			return c1.s-c2.s;
		}
	}
	public static class byLoc implements Comparator<cow>{
		public int compare(cow c1, cow c2){
			return c1.l-c2.l;
		}
	}
}
