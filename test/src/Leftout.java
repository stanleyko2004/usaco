import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Leftout {
	public static int[][] grid;
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("leftout.in"));
		int n = reader.nextInt();
		grid = new int[n][n];
		for (int i=0;i<n;i++){
			String str = reader.next();
			for (int j=0;j<n;j++){
				grid[i][j] = str.charAt(j)=='L' ? 0 : 1;
			}
		}
		for (int i=1;i<n;i++){
			grid[i][0] = grid[0][0]^grid[i][0];
			for (int j=1;j<n;j++){
				grid[i][j] = grid[i][j]^grid[i][0]^grid[0][j];
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("leftout.out"));
		if (count(1,1,n-1,n-1,0) == 0){
			writer.write("1 1\n");
			writer.close();
			return;
		}
		if (count(1,1,n-1,n-1,1) == n-1){
			for (int j=1; j<n; j++){
				if (count(1,j,n-1,j,1)==n-1){
					writer.write("1 "+(j+1)+"\n");
					writer.close();
					return;
					}
			}
		    for (int i=1; i<n; i++){
		    	if (count(i,1,i,n-1,1)==n-1){
		    		writer.write((i+1)+" 1\n");
		    		writer.close();
		    		return; 
		    		} 
		    }
		    writer.write("-1\n");
		    writer.close();
		    return;
		}
		if (count(1,1,n-1,n-1,1)!=1){ 
			writer.write("-1\n");
			writer.close();
			return; 
		}
		for (int i=1;i<n;i++){
			for (int j=1;j<n;j++){
				if (grid[i][j]==1){
					writer.write((i+1)+" "+(j+1)+"\n");
				}
			}
		}
		writer.close();
		reader.close();
	}
	public static int count(int x1,int y1,int x2,int y2,int num){
		int total = 0;
		for (int i=x1;i<=x2;i++){
			for (int j=y1;j<=y2;j++){
				if (grid[i][j] == num){
					total++;
				}
			}
		}
		return total;
	}
}
