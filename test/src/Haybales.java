import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class Haybales {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[] list = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pw.println(countLEQ(list, b) - countLEQ(list, a-1));
		}
		pw.close();
	}
	public static int countLEQ(int[] list, int limit) {
		if(list[0] > limit) {
			return 0;
		}
		int min = 0;
		int max = list.length-1;
		while(min != max) {
			int mid = (min+max+1)/2;
			if(list[mid] <= limit) {
				min = mid;
			}
			else {
				max = mid-1;
			}
		}
		return min + 1;
	}
}