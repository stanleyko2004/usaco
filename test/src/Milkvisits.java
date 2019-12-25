import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Milkvisits {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("milkvisits.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String order = reader.readLine();
		Cow[] cows = new Cow[n];
		for (int i=0; i<n; i++){
			cows[i] = new Cow(order.charAt(i));
		}
		for (int i=0; i<n-1; i++){
			st = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			cows[a].neighbors.push(b);
			cows[b].neighbors.push(a);
		}
		//iterative dfs
		Stack<Integer> cur = new Stack<Integer>();
		cur.push(0);
		cows[0].group = 0;
		int groups = 0;
		for (int i=0;i<n-1;i++){
			//finding next
			while (cows[cur.peek()].neighbors.isEmpty()){
				cur.pop();
			}
			int next = cows[cur.peek()].neighbors.pop();
			while (cows[next].group!=-1){
				while (cows[cur.peek()].neighbors.isEmpty()){
					cur.pop();
				}
				next = cows[cur.peek()].neighbors.pop();
			}
			//give group number
			cows[next].group = cows[cur.peek()].type==cows[next].type ? cows[cur.peek()].group : ++groups;
			//push in next
			cur.push(next);
		}
		for (int i=0;i<m;i++){
			st = new StringTokenizer(reader.readLine());
			int cow1 = Integer.parseInt(st.nextToken())-1;
			int cow2 = Integer.parseInt(st.nextToken())-1;
			String grass = st.nextToken();
			char g = grass.charAt(0);
			if (cows[cow1].group!=cows[cow2].group){
				writer.print(1);
			} else {
				if (cows[cow1].type == g){
					writer.print(1);
				} else {
					writer.print(0);
				}
			}
		}
		writer.close();
		reader.close();
	}
	public static class Cow{
		public char type;
		public Stack<Integer> neighbors;
		public int group;
		public Cow (char type){
			this.type = type;
			neighbors = new Stack<Integer>();
			group = -1;
		}
	}
}
