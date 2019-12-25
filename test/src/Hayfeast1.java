import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Hayfeast1 {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		long[] f = new long[n];
		long[] s = new long[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			f[i] = Long.parseLong(st.nextToken());
			s[i] = Long.parseLong(st.nextToken());
		}
		int left = 0;
		long min = Long.MAX_VALUE;
		TreeMap<Long, Integer> seen = new TreeMap<Long, Integer>();
		long flavor = 0;
		for(int i = 0; i < n; i++) {//looping through right
			flavor += f[i];
			update(seen, s[i], 1);
			while(flavor - f[left] >= m) {//incrementing left while updating highest spiciness
				update(seen, s[left], -1);
				flavor -= f[left++];
			}
			if(flavor >= m) {
				min = Math.min(min, seen.lastKey());
			}
		}
		writer.println(min);
		writer.close();
		reader.close();
	}
	private static void update(TreeMap<Long, Integer> seen, long index, int v) {
		if(!seen.containsKey(index)) {
			seen.put(index, 0);
		}
		int update = seen.get(index) + v;
		if(update == 0) {
			seen.remove(index);
		}
		else {
			seen.put(index, update);
		}
	}
}
