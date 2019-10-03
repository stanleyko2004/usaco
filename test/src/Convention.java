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

public class Convention {
	public static int[] cows;
	public static int n,m,c;
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("convention.in"));
		n = reader.nextInt();
		m = reader.nextInt();
		c = reader.nextInt();
		cows  = new int[n];
		for (int i=0;i<n;i++){
			cows[i] = reader.nextInt();
		}
		reader.close();
		Arrays.sort(cows);
		BufferedWriter writer = new BufferedWriter(new FileWriter("convention.out"));
		writer.write(Integer.toString(binSrch(1,1000000000)));
		writer.close();
	}
	public static int binSrch(int low,int high){
		if (low == high){
			return low;
		}
		int mid = (low+high)/2;
		if (pos(mid)){
			return binSrch(low,mid);
		} else {
			return binSrch(mid+1,high);
		}
	}
	public static boolean pos(int time){
		int bus = 1;
		int firstT = cows[0];
		int firstInd = 0;
		for (int i=0;i<n;i++){
			if (cows[i]-firstT>time || i-firstInd+1>c){
				bus++;
				firstT = cows[i];
				firstInd = i;
			}
		}
		return (bus<=m);
	}
}
