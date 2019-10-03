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
import java.util.StringTokenizer;

public class Mountains {
	public static int n;
	public static pair[] peaks;
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("mountains.in"));
		n = Integer.parseInt(reader.readLine());
		peaks = new pair[n];
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(reader.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			peaks[i] = new pair(x, y);
		}
		Arrays.sort(peaks);
		int res = 1;
		int i = 1;
		int maxsum = peaks[0].sum;
		while (i < n) {
			int prevI = i-1;
			while (i < n && peaks[i].diff == peaks[prevI].diff) i++;
			if (i == n) break;
			if (peaks[i].sum > maxsum) {
				res++;
				maxsum = peaks[i].sum;
			}
			i++;
		}
		BufferedWriter out = new BufferedWriter(new FileWriter("mountains.out"));
		out.write(Integer.toString(res));
		out.close();
		reader.close();		
	}
	public static class pair implements Comparable<pair> {
		public int x;
		public int y;
		public int diff;
		public int sum;
		public pair(int myx, int myy) {
			x = myx;
			y = myy;
			diff = x-y;
			sum = x+y;
		}
		public int compareTo(pair other) {
			if (diff != other.diff) return diff-other.diff;
			return other.sum - this.sum;
		}
	}
}
