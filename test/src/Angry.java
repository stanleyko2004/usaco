import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Angry {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("angry.in"));
		int n = reader.nextInt();
		int k = reader.nextInt();
		int[] bales = new int[n];
		for (int i=0;i<n;i++){
			bales[i] = reader.nextInt();
		}
		reader.close();
		Arrays.sort(bales);
		//start binary search
		int low = 0;
		int high = 500000000;
		while (low!=high){
			int mid = (low+high)/2;//test radius
			int cows = 0;
			int last = 0;
			while(last<n){
				cows++;
				int cur = last+1;
				while (cur<n && bales[cur]-bales[last]<=2*mid){
					cur++;
				}
				last = cur;
			}
			if (cows>k){
				low = mid+1;
			} else {
				high = mid;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("angry.out"));
		writer.write(Integer.toString(low));
		writer.close();
	}
}
