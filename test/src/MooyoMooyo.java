import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class MooyoMooyo {
	public static int n,k;
	public static int[] grid;
	final public static int[] dir = new int[]{-10,-1,1,10};
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("mooyomooyo.in"));
		n = reader.nextInt();
		k = reader.nextInt();
		grid = new int[10*n];
		for (int i=0;i<n;i++){
			String row = reader.next();
			for (int j=0;j<10;j++){
				grid[10*i+j] = row.charAt(j);
			}
		}
		while (true){
			boolean a = delCon();
			if (a){
				gravity();
			} else {
				break;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("mooyomooyo.out"));
		writer.write(Integer.toString(1));
		writer.close();
	}
	public static boolean delCon(){
		ArrayList<ArrayList<Integer>>[] trav = new ArrayList[9];
		for (int i=0;i<9;i++){
			trav[i] = new ArrayList<ArrayList<Integer>>();
		}
		for (int i=0;i<10*n;i++){
			boolean found = false;
			if (grid[i]!=0){
				for (ArrayList<Integer> j:trav[grid[i]-1]){
					for (Integer k:j){
						for (int l:dir){
							if (i+l==k){
								j.add(i);
								found = true;
								break;
							}
						}
						if (found){
							break;
						}
					}
					if (found){
						break;
					}
				}
				if (!found){
					trav[grid[i]-1].add(new ArrayList<Integer>(Arrays.asList(i)));
				}
			}
		}
		boolean a = false;
		for (ArrayList<ArrayList<Integer>> i:trav){
			for (ArrayList<Integer> j:i){
				if (j.size()>k){
					a = true;
					for (Integer k:j){
						grid[k] = 0;
					}
				}
			}
		}
		return a;
	}
	public static void gravity(){
		for (int i=10*(n-1);i<10*n;i++){
			int count = 0;
			while(grid[i-count]==0 && count<=i){
				count+=10;
			}
			for (int j=count;j<n;j+=10){
				grid[i-j-count] = grid[i-j];
				grid[i-j] = 0;
			}
		}
	}
}
