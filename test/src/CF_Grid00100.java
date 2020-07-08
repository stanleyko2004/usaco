import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CF_Grid00100 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i=0; i<t; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			System.out.println(k%n==0 ? 0 : 2);
			int[][] ans = new int[n][n];
			for (int inc=0; inc<n && k>0; inc++){
				for (int j=0; j<n && k>0; j++){
					ans[(j+inc)%n][j] = 1;
					k--;
				}
			}
			for (int j=0; j<n; j++){
				for (int l=0; l<n; l++){
					System.out.print(ans[j][l]);
				}
				System.out.println();
			}
		}
	}
}
