import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Barnpainting {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Barn[] barns = new Barn[n];
		Arrays.fill(barns, new Barn());
		for (int i=0;i<n-1;i++){
			st = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			barns[a].neighbors.add(b);
			barns[b].neighbors.add(a);
		}
		writer.close();
		reader.close();
	}
	public static class Barn{
		public ArrayList<Integer> neighbors;
		public Barn(){
			neighbors = new ArrayList<Integer>();
		}
	}
}
