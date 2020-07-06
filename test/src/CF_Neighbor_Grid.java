import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CF_Neighbor_Grid {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i=0; i<t; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] grid = new int[n][m];
			for (int j=0; j<n; j++){
				st = new StringTokenizer(br.readLine());
				for (int k=0; k<m; k++){
					grid[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			int[] cornersX = new int[]{0,n-1};
			int[] cornersY = new int[]{0,m-1};
			boolean flag = false;
			for (int j=0; j<2; j++){
				for (int k=0; k<2; k++){
					if (grid[cornersX[j]][cornersY[k]] > 2){
						flag = true;
					}
				}
			}
			for (int j=0; j<2; j++){
				for (int k=1; k<m-1; k++){
					if (grid[cornersX[j]][k] > 3){
						flag = true;
					}
				}
			}
			for (int k=0; k<2; k++){
				for (int j=1; j<n-1; j++){
					if (grid[j][cornersY[k]] > 3){
						flag = true;
					}
				}
			}
			for (int j=1; j<n-1; j++){
				for (int k=1; k<n-1; k++){
					if (grid[j][k] > 4){
						flag = true;
					}
				}
			}
			if (flag){
				System.out.println("NO");
			} else {
				System.out.println("YES");
				int[][] ansgrid = new int[n][m];
				for (int j=0; j<2; j++){
					for (int k=0; k<2; k++){
						ansgrid[cornersX[j]][cornersY[k]] = 2;
					}
				}
				for (int j=0; j<2; j++){
					for (int k=1; k<m-1; k++){
						ansgrid[cornersX[j]][k] = 3;
					}
				}
				for (int j=1; j<n-1; j++){
					for (int k=0; k<2; k++){
						ansgrid[j][cornersY[k]] = 3;
					}
				}
				for (int j=1; j<n-1; j++){
					for (int k=1; k<m-1; k++){
						ansgrid[j][k] = 4;
					}
				}
				for (int j=0; j<n; j++){
					System.out.print(ansgrid[j][0]);
					for (int k=1; k<m; k++){
						System.out.print(" "+ansgrid[j][k]);
					}
					System.out.println();
				}
			}
		}
	}
}
