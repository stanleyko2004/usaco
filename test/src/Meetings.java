import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Meetings {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("meetings.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		ArrayList<Cow> cows = new ArrayList<Cow>(n);
		int total = 0;
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			int weight = Integer.parseInt(st.nextToken());
			total += weight;
			cows.add(new Cow(weight,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		Collections.sort(cows);
		double cap = total/2;
		int collisions = 0;
		ArrayList<Integer> mc = new ArrayList<Integer>();//moving cows
		int ct = 0;
		for (int i=0;i<n;i++){
			if (!cows.get(i).d){
				cows.get(i).f = cows.get(ct).p;
				ct++;
			}
		}
		ct = n-1;
		for (int i=n-1;i>=0;i--){
			if (cows.get(i).d){
				cows.get(i).f = cows.get(ct).p;
				ct--;
			}
		}
		int left = 0; int right = n-1;
		while (cap > 0){
			while (cows.get(left).d && left<=cows.size()-1){
				left++;	
			}
			while (!cows.get(right).d && right>=0){
				right--;
			}
			int lPos = left==cows.size()-1 && cows.get(left).d ? l+1 : cows.get(left).p;
			int rPos = right==0 && !cows.get(right).d ? -1 : cows.get(right).p;
			if (lPos < l-rPos){
				cap -= cows.get(left).f;
				mc.add(left);
				left++;
			} else {
				cap -= cows.get(right).f;
				mc.add(right);
				right--;
			}
		}
		ct = 0;
		for (int i=0;i<left;i++){
			if (!cows.get(i).d){
				collisions += ct;
			} else {
				ct++;
			}
		}
		ct = 0;
		for (int i=n-1;i>right;i--){
			if (cows.get(i).d){
				collisions += ct;
			} else {
				ct++;
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
