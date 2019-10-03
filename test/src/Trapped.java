import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Trapped {
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("trapped.in"));
		int n = Integer.parseInt(reader.readLine());
		Bale[] grid = new Bale[n];
		for (int i=0;i<n;i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			int size = Integer.parseInt(st.nextToken());
			grid[i] = new Bale(Integer.parseInt(st.nextToken()),size);
		}
		Arrays.sort(grid);
		int res = 0;
		for (int i=0;i<n-1;i++){
			int runSpace = grid[i+1].compareTo(grid[i]);
			int left = i;
			int right = i+1;
			while (left>=0 && right<=n-1){
				boolean broke = false;
				int curRunSpace = grid[right].compareTo(grid[left]);
				if (curRunSpace>grid[left].size){
					left--;
					broke = true;
				}
				if (curRunSpace>grid[right].size){
					right++;
					broke = true;
				}
				if (!broke){
					break;
				}
			}
			if (left>=0 && right<=n-1){
				res += runSpace;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("trapped.out"));
		writer.write(Integer.toString(res));
		writer.close();
	}
	static class Bale implements Comparable<Bale>{
		public int position,size;
		public Bale(int p,int s){
			position = p;
			size = s;
		}
		public int compareTo(Bale otherBale){
			return position-otherBale.position;
		}
	}
}
