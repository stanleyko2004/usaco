import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Perimeter {
	public static boolean[][] traversed,icecream;
	public static int n;
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("perimeter.in"));
		n = reader.nextInt();
		icecream = new boolean[n][n];
		traversed = new boolean[n][n];
		for(int i=0;i<n;i++){
			String row = reader.next();
			for (int j=0;j<n;j++){
				if (row.charAt(j) == '#'){
					icecream[i][j] = true;
				}
			}
		}
		reader.close();
		int maxA = -1;
		int minP = Integer.MAX_VALUE;
		for (int i=0;i<n;i++){
			int[] AP = new int[]{-1,Integer.MAX_VALUE};
			for (int j=0;j<n;j++){
				if (icecream[i][j] && !traversed[i][j]){
					AP = floodfill(i,j);
				}
				if (AP[0]>maxA){
					maxA = AP[0];
					minP = AP[1];
				} else if (AP[0]==maxA){
					minP = Math.min(AP[1], minP);
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("perimeter.out"));
		writer.write(maxA+" "+minP);
		writer.close();
	}
	public static int[] floodfill(int i,int j){
		if (traversed[i][j]){
			return new int[]{0,0};
		}
		traversed[i][j] = true;
		int a = 1;
		int p = 4;
		if (i>0 && icecream[i-1][j]){
			p--;
			int[] AP = floodfill(i-1,j);
			a += AP[0];
			p += AP[1];
		}
		if (i<n-1 && icecream[i+1][j]){
			p--;
			int[] AP = floodfill(i+1,j);
			a += AP[0];
			p += AP[1];
		}
		if (j>0 && icecream[i][j-1]){
			p--;
			int[] AP = floodfill(i,j-1);
			a += AP[0];
			p += AP[1];
		}
		if (j<n-1 && icecream[i][j+1]){
			p--;
			int[] AP = floodfill(i,j+1);
			a += AP[0];
			p += AP[1];
		}
		return new int[]{a,p};
	}
}
