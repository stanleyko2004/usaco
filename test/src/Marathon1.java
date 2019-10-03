import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Marathon1 {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("marathon.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int points = Integer.parseInt(st.nextToken());
		int skips = Integer.parseInt(st.nextToken());
		int[] x = new int[points];//parallel arrays
		int[] y = new int[points];
		for (int i=0;i<points;i++){
			st = new StringTokenizer(reader.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[skips+1][points];
		for (int i=0;i<dp.length;i++){//fill with large value
			Arrays.fill(dp[i], 1<<30);
		}
		dp[0][0] = 0;//start
		for (int skip=0;skip<=skips;skip++){
			for (int point=0;point<points;point++){
				for (int nextPt=point+1;nextPt<points && skip+nextPt-point-1<=skips;nextPt++){
					int nextX = skip+nextPt-point-1;
					int nextY = nextPt;
					
					dp[nextX][nextY] = Math.min(dp[nextX][nextY], dp[skip][point]+Math.abs(x[point]-x[nextPt])+Math.abs(y[point]-y[nextPt]));
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		pw.print(dp[skips][points-1]);
		pw.close();
		reader.close();
	}
}

	