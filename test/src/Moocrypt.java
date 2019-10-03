import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Moocrypt {
	static char[][] grid;
	
	static int n,m;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("moocrypt.in"));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		grid = new char[n][m];
		for (int i=0;i<n;i++){
			String line = reader.readLine();
			for (int j=0;j<m;j++){
				grid[i][j]=line.charAt(j);
			}
		}
		List<String> xyy = new ArrayList<String>();
		for (int i=0;i<n;i++){
			for (int j=0;j<m;j++){
				xyy.addAll(VH(i,j));
				xyy.addAll(D(i,j));
			}
		}
		Map<String,Integer> dict = new HashMap<String,Integer>();
		List<String> keys = new ArrayList<String>();
		for (String i:xyy){
			if (dict.containsKey(i)){
				dict.replace(i, dict.get(i)+1);
			} else {
				dict.put(i, 1);
			}
			keys.add(i);
		}
		int ans = 0;
		for (String i:keys){
			if (dict.get(i)>ans){
				ans = dict.get(i);
			}
		}		
		BufferedWriter writer = new BufferedWriter(new FileWriter("moocrypt.out"));
		writer.write(Integer.toString(ans));
		writer.close();
	}
	public static List<String> VH(int i,int j){
		List<String> xyy = new ArrayList<String>();
		if (i<=n-3 && grid[i+1][j]==grid[i+2][j] && grid[i+1][j]!=grid[i][j] && grid[i+1][j]!='O'){//down
			xyy.add(String.valueOf(new char[]{grid[i][j],grid[i+1][j],grid[i+2][j]}));
		}
		if (i>=2 && grid[i-1][j]==grid[i-2][j] && grid[i-1][j]!=grid[i][j] && grid[i-1][j]!='O'){//up
			xyy.add(String.valueOf(new char[]{grid[i][j],grid[i-1][j],grid[i-2][j]}));
		}
		if (j<=m-3 && grid[i][j+1]==grid[i][j+2] && grid[i][j+1]!=grid[i][j] && grid[i][j+1]!='O'){//right
			xyy.add(String.valueOf(new char[]{grid[i][j],grid[i][j+1],grid[i][j+2]}));
		}
		if (j>=2 && grid[i][j-1]==grid[i][j-2] && grid[i][j-1]!=grid[i][j] && grid[i][j-1]!='O'){//left
			xyy.add(String.valueOf(new char[]{grid[i][j],grid[i][j-1],grid[i][j-2]}));
		}
		return xyy;
	}
	public static List<String> D(int i,int j){
		List<String> xyy = new ArrayList<String>();
		if (i<=n-3 && j<=m-3 && grid[i+1][j+1]==grid[i+2][j+2] && grid[i+1][j+1]!=grid[i][j] && grid[i+1][j+1]!='O'){//rd
			xyy.add(String.valueOf(new char[]{grid[i][j],grid[i+1][j+1],grid[i+2][j+2]}));
		}
		if (i>=2 && j<=m-3 && grid[i-1][j+1]==grid[i-2][j+2] && grid[i-1][j+1]!=grid[i][j] && grid[i-1][j+1]!='O'){//ru
			xyy.add(String.valueOf(new char[]{grid[i][j],grid[i-1][j+1],grid[i-2][j+2]}));
		}
		if (j>=2 && i>=2 && grid[i-1][j-1]==grid[i-2][j-2] && grid[i-1][j-1]!=grid[i][j] && grid[i-1][j-1]!='O'){//lu
			xyy.add(String.valueOf(new char[]{grid[i][j],grid[i-1][j-1],grid[i-2][j-2]}));
		}
		if (j>=2 && i<=n-3 && grid[i+1][j-1]==grid[i+2][j-2] && grid[i+1][j-1]!=grid[i][j] && grid[i+1][j-1]!='O'){//ld
			xyy.add(String.valueOf(new char[]{grid[i][j],grid[i+1][j-1],grid[i+2][j-2]}));
		}
		return xyy;
	}
}