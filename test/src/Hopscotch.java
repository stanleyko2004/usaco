import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Hopscotch {
	static char[][] grid;
	static int r;
	static int c;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("hopscotch.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		grid = new char[r][c];
		for (int i=0;i<r;i++){
			String line = reader.readLine();
			for (int j=0;j<c;j++){
				grid[i][j]=line.charAt(j);
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("hopscotch.out"));
		writer.write(Integer.toString(solve(0,0)));
		writer.close();
	}
	public static int solve (int curX, int curY){
		if (curX==r-1 && curY==c-1){
			return 1;
		}
		int ans = 0;
		for (int i=curX+1;i<r;i++){
			for (int j=curY+1;j<c;j++){
				if (grid[i][j]!=grid[curX][curY]){
					ans+=solve(i,j);
				}
			}
		}
		return ans;
	}
}
