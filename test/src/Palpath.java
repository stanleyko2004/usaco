import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Palpath {
	static Set<String>[] topLeft,botRight;
	
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("palpath.in"));
		n = Integer.parseInt(reader.readLine());
		char[][] grid = new char[n][n];
		topLeft = new HashSet[n];
		botRight = new HashSet[n];
		for (int i=0;i<n;i++){
			topLeft[i] = new HashSet<String>();
			botRight[i] = new HashSet<String>();
			String line = reader.readLine();
			for (int j=0;j<n;j++){
				grid[i][j] = line.charAt(j);
			}
		}
		solve(grid,0,0,topLeft,"");
		rotate(grid);
		solve(grid,0,0,botRight,"");
		Set<String> res = new HashSet<String>();
		for (int i=0;i<n;i++){
			for (String str:topLeft[i]){
				if (botRight[i].contains(str)){
					res.add(str);
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("palpath.out"));
		writer.write(Integer.toString(res.size()));
		writer.close();
	}
	public static void solve (char[][] grid, int curX, int curY, Set<String>[] res, String currS){
		if (curX+curY == n-1){
			res[curX].add(currS+grid[curX][curY]);
		} else {
			solve(grid,curX+1,curY,res,currS+grid[curX][curY]);
			solve(grid,curX,curY+1,res,currS+grid[curX][curY]);
		}
	}
	public static void rotate (char[][] grid){
		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				if (i+j>=n-1){
					continue;
				}
				char change = grid[i][j];
				grid[i][j] = grid[n-1-j][n-1-i];
				grid[n-1-j][n-1-i] = change;
			}
		}
	}
}