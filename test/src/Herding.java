import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Herding {
	public static int n;
	public static int[] cows;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("herding.in"));
		n = Integer.parseInt(reader.readLine());
		cows = new int[n];
		for (int i=0;i<n;i++){
			cows[i] = Integer.parseInt(reader.readLine());
		}
		reader.close();
		Arrays.sort(cows);
		int min = findMin();
		int max = Math.max(cows[n-2]-cows[0],cows[n-1]-cows[1]) - (n-2);
		PrintWriter writer = new PrintWriter(new FileWriter("herding.out"));
		writer.write(min+"\n"+max);
		writer.close();
	}
	public static int findMin(){
		if (cows[n-2]-cows[0] == n-2 && cows[n-1]-cows[n-2]>2){
			return 2;
		}
		if (cows[n-1]-cows[1] == n-2 && cows[1]-cows[0]>2){
			return 2;
		}
		int j=0;int best = 0;
		for (int i=0;i<n;i++){
			while(j<n-1 && cows[j+1]-cows[i]<=n-1){
				j++;
			}
			best = Math.max(best, j-i+1);
		}
		return n-best;
	}
}
