import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Revegetate {
	public static boolean possible = true;
	public static ArrayList<Integer>[] same,diff;
	public static int[] traversed;
	public static void main(String[] args) throws Exception {
		Scanner reader = new Scanner(new FileReader("revegetate.in"));
		int n = reader.nextInt();
		int m = reader.nextInt();
		same = new ArrayList[n];
		diff = new ArrayList[n];
		traversed = new int[n];
		for (int i=0;i<n;i++){
			same[i] = new ArrayList<Integer>();
			diff[i] = new ArrayList<Integer>();
		}
		for (int i=0;i<m;i++){
			String sd = reader.next();
			int p1 = reader.nextInt()-1;
			int p2 = reader.nextInt()-1;
			if (sd.equals("S")){
				same[p1].add(p2);
				same[p2].add(p1);
			} else {
				diff[p1].add(p2);
				diff[p2].add(p1);
			}
		}
		int ans = 0;
		for (int i=0;i<n;i++){
			if (traversed[i]==0){
				ans++;
				dfs(i,1);
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("revegetate.out"));
		if (possible){
			writer.write("1");
			for (int i=0;i<ans;i++){
				writer.write("0");
			}
		} else {
			writer.write("0");
		}
		writer.close();
		reader.close();		
	}
	public static void dfs(int cur,int state){
		traversed[cur] = state;
		for (Integer i:same[cur]){
			if (traversed[i]==3-state){
				possible = false;
			} else if (traversed[i]==0){
				dfs(i,state);
			}
		}
		for (Integer i:diff[cur]){
			if (traversed[i]==state){
				possible = false;
			} else if (traversed[i]==0){
				dfs(i,3-state);
			}
		}
	}
}
