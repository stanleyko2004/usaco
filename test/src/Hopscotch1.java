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
import java.util.StringTokenizer;

public class Hopscotch1 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("hopscotch.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] grid  = new int[r][c];
		for (int i=0;i<r;i++){
			st = new StringTokenizer(reader.readLine());
			for (int j=0;j<c;j++){
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] ans = new int [r][c];
		ans[0][0] = 1;
		for (int i=0;i<r;i++){
			for (int j=0;j<c;j++){
				for (int a=i+1;a<r;a++){
					for (int b=j+1;b<c;b++){
						if (grid[i][j]!=grid[a][b]){
							ans[a][b] += ans[i][j];
							ans[a][b] %= 1000000007;
						}
					}
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("hopscotch.out"));
		writer.write(Integer.toString(ans[r-1][c-1]));
		writer.close();
	}
}
