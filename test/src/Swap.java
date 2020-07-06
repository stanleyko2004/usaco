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
import java.util.Queue;
import java.util.StringTokenizer;

public class Swap {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("swap.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());  
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] orig = new int[n];
        for (int i=0; i<n; i++){
            orig[i] = i+1;
        }
        //swap is order after 1 set of instructions
		int[] swap = orig.clone();
		for (int i=0; i<m; i++){
			st = new StringTokenizer(reader.readLine());
            swap = reverse(swap, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		}
		reader.close();
		ArrayList<ArrayList<Integer>> cc = new ArrayList<ArrayList<Integer>>();//in reverse order
		boolean[] traversed = new boolean[n];
		int[] component = new int[n];
		int[] position = new int[n];
		int C = 0;
		for (int i=0; i<n; i++){
			if (!traversed[i]){
				traversed[i] = true;
				component[i] = C;
				int p = 0;
				position[i] = p++;
				int cur = i;
				cc.add(new ArrayList<Integer>());
				cc.get(C).add(i+1);
				while (swap[cur]-1 != i){
					traversed[swap[cur]-1] = true;
					component[swap[cur]-1] = C;
					position[swap[cur]-1] = p++;
					cc.get(C).add(swap[cur]);
					cur = swap[cur]-1;
				}
				C++;
			}
		}
		ArrayList<Integer> c;
		int res;
		for (int i=0; i<n; i++){
			c = cc.get(component[i]);
			writer.println(c.get((position[i]+k)%c.size()));//calculating negative modulo
		}
		writer.close();
	}
	public static int[] reverse(int [] arr, int L, int R){
        int[] array2 = arr.clone();
        for(int i=R; i>=L; i--){
            array2[L+R-i]= arr[i];
        }
        return array2;    
	}
	public static class Edge{
		public int from, to;
		public Edge(int from, int to){
			this.from = from;
			this.to = to;
		}
	}
}
