import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crossword {
	public static void main(String[] args) throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("crosswords.in"));
		String NM = reader.readLine();
		String[] a = NM.split(" ");
		int N = Integer.parseInt(a[0]);
		int M = Integer.parseInt(a[1]);
		boolean[][] puzzle = new boolean[N][M];
		String line;
		for (int i=0;i<N;i++){
			line = reader.readLine();
			for (int j=0;j<M;j++){
				if ('.'==line.charAt(j)){
					puzzle[i][j]=true;
				} else if ('#'==line.charAt(j)){
					puzzle[i][j]=false;
				}
			}
		}
		
		reader.close();
		List<List<Integer>> result = crossword(puzzle,N,M);
		BufferedWriter writer = new BufferedWriter(new FileWriter("crosswords.out"));
		int length = result.size();
		writer.write(Integer.toString(length));
		writer.newLine();
		for (List<Integer> b:result){
			writer.write(Integer.toString(b.get(0)+1));
			for (int c=1;c<b.size();c++){
				writer.write(" "+Integer.toString(b.get(c)+1));
			}
			writer.newLine();
		}
		writer.close();
	}
	public static List<List<Integer>> crossword(boolean[][] p,int N, int M){
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		List<Integer> x;
		for (int i=0;i<N;i++){
			for (int j=0;j<M;j++){
				if (p[i][j]){
					if (((j<M-2) && (j==0 || (!p[i][j-1])) && p[i][j+1] && p[i][j+2])//horizontal
						|| ((i<N-2) && (i==0 || (!p[i-1][j])) && p[i+1][j] && p[i+2][j])){//vertical
						x = Arrays.asList(i,j);
						ans.add(x);
					}
				}
			}
		}
		return ans;
	}
}
		

