import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Paintbarn1 {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("paintbarn.in"));
		int n = reader.nextInt();
		int k = reader.nextInt();
		int[][] grid = new int[1001][1001];
		for (int i=0;i<n;i++){
			int x1 = reader.nextInt();
			int y1 = reader.nextInt();
			int x2 = reader.nextInt();
			int y2 = reader.nextInt();
			grid[x1][y1]++;
			grid[x1][y2]--;
			grid[x2][y1]--;
			grid[x2][y2]++;
		}
		int ans = 0;
		for (int i=0;i<1000;i++){
			for (int j=0;j<1000;j++){
				if (i>0){
					grid[i][j]+=grid[i-1][j];
				}
				if (j>0){
					grid[i][j]+=grid[i][j-1];
				}
				if (i>0 && j>0){
					grid[i][j]-=grid[i-1][j-1];
				}
				if (grid[i][j]==k){
					ans++;
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("paintbarn.out"));
		writer.write(Integer.toString(ans));
		writer.close();
		reader.close();
	}
}