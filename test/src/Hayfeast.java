import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Hayfeast {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		PriorityQueue<Dish> feast = new PriorityQueue<Dish>(n);
		for (int i=0;i<n;i++){
			st = new StringTokenizer(reader.readLine());
			feast.add(new Dish(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		int max = 0;
		int sum = 0;
		while (sum<m){
			Dish cur = feast.poll();
			sum += cur.f;
			max = Math.max(max, cur.s);
		}
		writer.write(max+"\n");
		writer.close();
		reader.close();
	}
	public static class Dish implements Comparable<Dish>{
		public int s,f;
		public Dish(int flavor,int spicyness){
			s = spicyness;
			f = flavor;
		}
		public int compareTo(Dish other){
			return other.s==this.s?other.f-this.f:this.s-other.s;
		}
	}
}
