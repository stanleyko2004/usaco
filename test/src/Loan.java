import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Loan {
	public static long n, k, m, extra;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("loan.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		reader.close();
		extra = n-k*m;
		long low = 1;
		long high = (n/m)+1;
		long mid;
		while (high>low+1) {
			mid = (high+low)/2;
			boolean temp = total(mid);
			if (temp){
				low = mid;
			} else {
				high = mid;
			}
		}
		if (total(high)){
			writer.println(high);
		} else {
			writer.println(low);
		}
		writer.close();
	}
	public static boolean total(long a) {
		long give = Math.max(n/a, m);
		long total = 0;
		total += give;
		long stop = k;
		for (int i=1; i<k; i++){
			give = (n-total)/a;
			if (give <= m){
				stop = i;
				break;
			} else if (total-m*i > extra){
				return true;
			} else {
				total += give;
			}
		}
		total += (k-stop)*m;
		if (total >= n){
			return true;
		}
		return false;
	}
}
