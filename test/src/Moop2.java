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
import java.util.StringTokenizer;

public class Moop2 {
	public static boolean[] traversed;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("moop.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
		int n = Integer.parseInt(reader.readLine());
		Node[] particles = new Node[n];
		for (int i=0; i<n; i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			particles[i] = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		reader.close();
		Arrays.sort(particles);
		int[] min = new int[n];
		min[0] = particles[0].y;
		for (int i=1; i<n; i++){
			min[i] = Math.min(min[i-1], particles[i].y);
		}
		int[] max = new int[n];
		max[n-1] = particles[n-1].y;
		for (int i=n-2; i>=0; i--){
			max[i] = Math.max(max[i+1], particles[i].y);
		}
		int cc = 1;
		for (int i=0; i<n-1; i++){
			if (min[i] > max[i+1]){
				cc++;
			}
		}
		writer.println(cc);
		writer.close();
	}
	public static class Node implements Comparable<Node>{
		public int x, y;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		public int compareTo(Node other){
			return this.x==other.x ? this.y-other.y : this.x-other.x;
		}
	}
}
