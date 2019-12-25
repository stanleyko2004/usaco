import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Sleepy {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("sleepy.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		int n = Integer.parseInt(reader.readLine());
		int[] cows = new int[n];
		StringTokenizer st = new StringTokenizer(reader.readLine());
		for (int i=0;i<n;i++){
			cows[i] = Integer.parseInt(st.nextToken());
		}
		int ordered = n-2;
		while (cows[ordered]<cows[ordered+1]){
			ordered--;
		}
		ordered++;
		writer.println(ordered);
		ArrayList<Integer> cowList = new ArrayList<Integer>(n);
		for (int i=ordered;i<n;i++){
			cowList.add(cows[i]);
		}
		for (int i=0;i<ordered;i++){//loop through unordered cows
			//binary search for the right index
			int low = 0;
			int high = cowList.size();
			while (low<high){
				int mid = (low+high)/2;
				if (cowList.get(mid)<cows[i]){
					low = mid+1;
				} else {
					high = mid;
				}
			}
			cowList.add(low,cows[i]);
			writer.print((i>0 ? " " : "")+(low+ordered-i-1));
		}
		writer.close();
		reader.close();
	}
}
