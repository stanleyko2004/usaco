import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CF_Books {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(br.readLine());
		for (int i=0; i<q; i++){
			int n = Integer.parseInt(br.readLine());
			int[] kids = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++){
				kids[j] = Integer.parseInt(st.nextToken())-1;
			}
			boolean[] traversed = new boolean[n];
			ArrayList<ArrayList<Integer>> cc = new ArrayList<ArrayList<Integer>>();
			for (int j=0; j<n; j++){
				if (!traversed[j]){
					traversed[j] = true;
					cc.add(new ArrayList<Integer>());
					cc.get(cc.size()-1).add(j);
					int cur = j;
					while(j != kids[cur]){
						cur = kids[cur];
						traversed[cur] = true;
						cc.get(cc.size()-1).add(cur);
					}
				}
			}
			int[] res = new int[n];
			for (ArrayList<Integer> j : cc){
				for (int k : j){
					res[k] = j.size();
				}
			}
			for (int j=0; j<n-1; j++){
				System.out.print(res[j]+" ");
			}
			System.out.println(res[n-1]);
		}
		return;
	}
}
