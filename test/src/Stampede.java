import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Stampede {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("stampede.in"));
		int n = reader.nextInt();
		Event[] events = new Event[2*n];
		Cow[] cows = new Cow[n];
		for (int i=0;i<n;i++){
			int x = reader.nextInt();
			int y = reader.nextInt();
			int r = reader.nextInt();
			events[2*i] = new Event(y, -(x+1)*r, i, true);
			events[2*i+1] = new Event(y, -x*r, i, false);
			cows[i] = new Cow(i,y);
		}
		Arrays.sort(events);
		boolean[] seen = new boolean[n];
		TreeSet<Cow> crossing = new TreeSet<Cow>();//y crossing cows
		int i = 0;
		while (i<2*n){
			int j = i;
			while (j<2*n && events[i].t == events[j].t){//find same starting times
				j++;
			}
			for (int k=i;k<j;k++){
				if (events[k].entering){
					crossing.add(cows[events[k].id]);
				} else {//if cow leaving
					crossing.remove(cows[events[k].id]);
				}
			}
			if (crossing.size()>0){seen[crossing.first().id] = true;}
			i = j;
		}
		int ans = 0;
		for (int a=0;a<n;a++){
			if (seen[a]){
				ans++;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
		pw.print(ans);
		pw.close();
		reader.close();
	}
	public static class Event implements Comparable<Event>{
		public int y,t,id;
		public boolean entering;
		public Event(int y,int time,int cowId,boolean entering){
			this.y = y;
			t = time;
			id = cowId;
			this.entering = entering;
		}
		public int compareTo(Event other){
			if (t!=other.t){return t-other.t;}
			if (!entering && other.entering){return -1;}
			if (entering && !other.entering){return 1;}
			return 0;
		}
	}
	public static class Cow implements Comparable<Cow>{
		public int id,y;
		public Cow(int i,int y){
			id = i;
			this.y = y;
		}
		public int compareTo(Cow other){
			return y-other.y;
		}
	}
}

	