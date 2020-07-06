import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Loan1 {
	public static long n, k, m, extra;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("loan.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Long.parseLong(st.nextToken());
		k = Long.parseLong(st.nextToken());
		m = Long.parseLong(st.nextToken());
		reader.close();
		long l, h, mid;
		l = 0;
		h = (n / m) + 1;
		while (l < h) {
			mid = (l + h + 1) / 2;
			//System.out.println("-----"+mid+"-----");
			if (valid(k, mid)) {
				l = mid;
			} else {
				h = mid - 1;
			}
		}
		writer.println(l);
		writer.close();
	}

	public static boolean valid (long k, long x){ 
		long g = 0;
		while (k > 0 && g < n){
			long y = (n - g) / x;
			if (y < m) {
				long leftover = (n-g + m-1) / m;
				return leftover <= k;
			}
			long maxmatch = n - x*y;
			long numdays = (maxmatch - g) / y + 1;
			if(numdays > k){
				numdays = k;
			}
			g += y * numdays;
			k -= numdays;
		}
		return g >= n;
	}
}
