import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Meetings1 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("meetings.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		Cow[] cows = new Cow[n];
		int tw = 0; //total weight
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			int weight = Integer.parseInt(st.nextToken());
			tw += weight;
			cows[i] = new Cow(weight,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(cows);
		
		int ct = 0;
		LinkedList<Integer> faceL = new LinkedList<Integer>();
		for (int i=0; i<n; i++){
			if (!cows[i].d){
				cows[i].f = cows[ct].w;
				ct++;
				faceL.add(i);
			}
		}
		LinkedList<Integer> faceR = new LinkedList<Integer>();
		ct = n-1;
		for (int i=n-1; i>=0; i--){
			if (cows[i].d){
				cows[i].f = cows[ct].w;
				ct--;
				faceR.add(i);
			}
		}
		
		float cap = (float) tw/2;
		int t = 0;
		while (cap>0){
			if (faceL.isEmpty()){
				t = cows[faceR.peek()].p;
				cap -= cows[faceR.poll()].f;
			} else if (faceR.isEmpty()){
				t = cows[faceL.peek()].p;
				cap -= cows[faceL.poll()].f;
			} else {
				Cow R = cows[faceR.peek()];
				Cow L = cows[faceL.peek()];
				if (l-R.p<L.p){
					t = l-cows[faceR.peek()].p;
					cap -= cows[faceR.poll()].f;
				} else {
					t = cows[faceL.peek()].p;
					cap -= cows[faceL.poll()].f;
				}
			}
		}
		
		int collisions = 0;
		LinkedList<Integer> cc = new LinkedList<Integer>(); //cow collisions
		cc.push(0);
		ct = 1;
		for (int i=0; i<n; i++){
			if (cows[i].d){
				for (int j=ct; j<n && cows[j].p<=cows[i].p+2*t; j++){
					if (!cows[j].d){
						cc.add(j);
					}
					ct++;
				}
				while (!cc.isEmpty() && cows[cc.peek()].p<=cows[i].p){
					cc.poll();
				}
				collisions += cc.size();
			}
		}
		writer.print(collisions);
		writer.close();
		reader.close();
	}
	public static class Cow implements Comparable<Cow>{
		public int w,p,f;
		public boolean d;
		public Cow(int weight,int position,int direction){
			w = weight;
			p = position;
			d = direction==1 ? true : false;
		}
		public int compareTo(Cow other){
			return p-other.p;
		}
	}
}
